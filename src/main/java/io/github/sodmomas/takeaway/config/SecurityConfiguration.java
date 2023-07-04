package io.github.sodmomas.takeaway.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.common.result.Result;
import io.github.sodmomas.takeaway.common.util.RequestUtils;
import io.github.sodmomas.takeaway.filter.JwtAuthenticationFilter;
import io.github.sodmomas.takeaway.model.dto.LoginResult;
import io.github.sodmomas.takeaway.security.JwtTokenManager;
import io.github.sodmomas.takeaway.security.exception.MyAccessDeniedHandler;
import io.github.sodmomas.takeaway.security.exception.MyAuthenticationEntryPoint;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author Sod-Momas
 * @since 2023/6/27
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/favicon.ico",
                "/static/**",
                "/public/**",
                "/api/v1/auth/captcha",
                "/webjars/**",
                "/doc.html",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/ws/**"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   MyAuthenticationEntryPoint authenticationEntryPoint,
                                                   MyAccessDeniedHandler accessDeniedHandler,
                                                   JwtTokenManager jwtTokenManager,
                                                   ObjectMapper objectMapper,
                                                   RedisTemplate redisTemplate
    ) throws Exception {
        http.sessionManagement(cfg -> cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(cfg -> cfg
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        http.authorizeHttpRequests(req -> {
            req.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll();
            req.anyRequest().authenticated();
        });
        http.formLogin(req -> {
            req.loginProcessingUrl(SecurityConstants.LOGIN_PATH);
            req.successHandler(new LoginHandler(jwtTokenManager, objectMapper, true));
            req.failureHandler(new LoginHandler(jwtTokenManager, objectMapper, false));
        });
        http.logout(req -> {
            req.logoutUrl(SecurityConstants.LOGOUT_PATH);
            req.logoutSuccessHandler(new MyLogoutSuccessHandler(jwtTokenManager, objectMapper, redisTemplate));
        });
        http.csrf(AbstractHttpConfigurer::disable);
        // 验证码校验过滤器
//        http.addFilterBefore(new VerifyCodeFilter(),UsernamePasswordAuthenticationFilter.class);
        // JWT 校验过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理登录的逻辑
     */
    static class LoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
        private final JwtTokenManager jwtTokenManager;
        private final ObjectMapper objectMapper;
        private final boolean loginSuccess;

        LoginHandler(JwtTokenManager jwtTokenManager, ObjectMapper objectMapper, boolean loginSuccess) {
            this.jwtTokenManager = jwtTokenManager;
            this.objectMapper = objectMapper;
            this.loginSuccess = loginSuccess;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            // 生成token
            String accessToken = jwtTokenManager.createToken(authentication);
            LoginResult loginResult = LoginResult.builder()
                    .tokenType("Bearer")
                    .accessToken(accessToken)
                    .build();
            final String json = objectMapper.writeValueAsString(Result.success(loginResult));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentLength(json.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(json);
        }

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            final String json = objectMapper.writeValueAsString(Result.failed());
            response.getWriter().write(json);
        }
    }

    static class MyLogoutSuccessHandler implements LogoutSuccessHandler {
        private final JwtTokenManager jwtTokenManager;
        private final ObjectMapper objectMapper;
        private final RedisTemplate redisTemplate;

        MyLogoutSuccessHandler(JwtTokenManager jwtTokenManager, ObjectMapper objectMapper, RedisTemplate redisTemplate) {
            this.jwtTokenManager = jwtTokenManager;
            this.objectMapper = objectMapper;
            this.redisTemplate = redisTemplate;
        }

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            String token = RequestUtils.resolveToken(request);
            if (StrUtil.isNotBlank(token)) {
                Claims claims = jwtTokenManager.getTokenClaims(token);
                String jti = claims.get("jti", String.class);

                Date expiration = claims.getExpiration();
                if (expiration != null) {
                    // 有过期时间，在token有效时间内存入黑名单，超出时间移除黑名单节省内存占用
                    long ttl = (expiration.getTime() - System.currentTimeMillis());
                    redisTemplate.opsForValue().set(SecurityConstants.BLACK_TOKEN_CACHE_PREFIX + jti, null, ttl, TimeUnit.MILLISECONDS);
                } else {
                    // 无过期时间，永久加入黑名单
                    redisTemplate.opsForValue().set(SecurityConstants.BLACK_TOKEN_CACHE_PREFIX + jti, null);
                }
            }
            final String json = objectMapper.writeValueAsString(Result.success());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentLength(json.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(json);
            SecurityContextHolder.clearContext();
        }
    }
}