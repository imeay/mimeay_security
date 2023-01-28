package com.mimeay.mimeay_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@SpringBootApplication
public class MimeaySecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MimeaySecurityApplication.class, args);
    }

}
