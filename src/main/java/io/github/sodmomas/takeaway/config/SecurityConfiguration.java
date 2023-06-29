package io.github.sodmomas.takeaway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


/**
 * @author Sod-Momas
 * @since 2023/6/27
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico", "/static/**", "/public/**");
    }

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

    /**
     * @return 每个角色不一样跳到不同首页
     * @see WebConfig#addViewControllers(ViewControllerRegistry)·
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            final String usertype = request.getParameter("usertype");
            if ("patient".equals(usertype)) {
                response.sendRedirect("/patient");
//                request.getRequestDispatcher("/patient").forward(request, response);
            } else if ("doctor".equals(usertype)) {
//                request.getRequestDispatcher("/doctor").forward(request, response);
                response.sendRedirect("/doctor");
            } else if ("employee".equals(usertype)) {
//                request.getRequestDispatcher("/employee").forward(request, response);
                response.sendRedirect("/employee");
            } else {
//                request.getRequestDispatcher("/index").forward(request, response);
                response.sendRedirect("/index");
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}