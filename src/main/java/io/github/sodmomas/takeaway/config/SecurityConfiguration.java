package io.github.sodmomas.takeaway.config;

import io.github.sodmomas.takeaway.common.constant.SecurityConstants;
import io.github.sodmomas.takeaway.filter.JwtAuthenticationFilter;
import io.github.sodmomas.takeaway.filter.VerifyCodeFilter;
import io.github.sodmomas.takeaway.security.JwtTokenManager;
import io.github.sodmomas.takeaway.security.exception.MyAccessDeniedHandler;
import io.github.sodmomas.takeaway.security.exception.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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

    /*

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.formLogin(req -> {
                // 登录页面
                req.loginPage("/login");
                // 登录处理接口
                req.loginProcessingUrl("/login");
                req.successHandler(successHandler());
            });
            http.logout(req -> {
            });
            http.csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   MyAuthenticationEntryPoint authenticationEntryPoint,
                                                   MyAccessDeniedHandler accessDeniedHandler,
                                                   JwtTokenManager jwtTokenManager
    ) throws Exception {
        http.authorizeHttpRequests(req -> req
                .requestMatchers(SecurityConstants.LOGIN_PATH).permitAll()
                .anyRequest().authenticated()
        );
        http.sessionManagement(cfg -> cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(cfg -> cfg
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        http.csrf(AbstractHttpConfigurer::disable);
        // 验证码校验过滤器
        http.addFilterBefore(new VerifyCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        // JWT 校验过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    /**
//     * @return 每个角色不一样跳到不同首页
//     * @see WebConfig#addViewControllers(ViewControllerRegistry)·
//     */
//    private AuthenticationSuccessHandler successHandler() {
//        return (request, response, authentication) -> {
//            final String usertype = request.getParameter("usertype");
//            if ("patient".equals(usertype)) {
//                response.sendRedirect("/patient");
////                request.getRequestDispatcher("/patient").forward(request, response);
//            } else if ("doctor".equals(usertype)) {
////                request.getRequestDispatcher("/doctor").forward(request, response);
//                response.sendRedirect("/doctor");
//            } else if ("employee".equals(usertype)) {
////                request.getRequestDispatcher("/employee").forward(request, response);
//                response.sendRedirect("/employee");
//            } else {
////                request.getRequestDispatcher("/index").forward(request, response);
//                response.sendRedirect("/index");
//            }
//        };
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 无法直接注入 AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}