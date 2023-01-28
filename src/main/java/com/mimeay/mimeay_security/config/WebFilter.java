package com.mimeay.mimeay_security.config;

import com.mimeay.mimeay_security.domian.business.MimeayDetail;
import com.mimeay.mimeay_security.domian.business.MimeayToken;
import com.mimeay.mimeay_security.helpers.UserHelper;
import com.mimeay.mimeay_security.domian.business.User;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 17:53
 */
public class WebFilter extends OncePerRequestFilter {

    private final UserHelper userHelper;

    public WebFilter(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = userHelper.getToken(request);
        if (token != null) {
            User currentUser = userHelper.getCurrentUser(token);
            if (currentUser == null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                filterChain.doFilter(request, response);
                return;
            }
            List<GrantedAuthority> simpleGrantedAuthorities = currentUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            Authentication authentication = new MimeayToken(
                    new MimeayDetail(currentUser.getId(), currentUser.getNickname(), currentUser.getRoles()),
                    simpleGrantedAuthorities

            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
