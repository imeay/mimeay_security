package com.example.mimeay_security.config;

import com.example.mimeay_security.MimeayDetail;
import com.example.mimeay_security.MimeayToken;
import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 17:53
 */
public class WebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        List<String> roles = Lists.newArrayList("ROLE_USER");
//        roles.add("ADMIN");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        Authentication authentication = new MimeayToken(
                new MimeayDetail(1L, "chenxiaochi"),
                simpleGrantedAuthorities

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
