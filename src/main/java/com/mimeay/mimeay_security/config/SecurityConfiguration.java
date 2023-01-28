package com.mimeay.mimeay_security.config;

import com.mimeay.mimeay_security.helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 16:36
 */
@Configuration
public class SecurityConfiguration {

    @Autowired
    UserHelper userHelper;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/public/**")
                .antMatchers("/login");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(
                auth -> auth.anyRequest().authenticated()
        ).addFilterBefore(
                new WebFilter(userHelper),
                UsernamePasswordAuthenticationFilter.class
        );
        return httpSecurity.build();
    }
}
