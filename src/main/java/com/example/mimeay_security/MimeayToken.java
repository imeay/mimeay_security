package com.example.mimeay_security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 17:56
 */
public class MimeayToken implements Authentication {

    private final List<SimpleGrantedAuthority> authorities;
    private MimeayDetail mimeayDetail;

    public MimeayToken(MimeayDetail mimeayDetail,List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
        this.mimeayDetail = mimeayDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public MimeayDetail getDetails() {
        return mimeayDetail;
    }

    @Override
    public Object getPrincipal() {
        return mimeayDetail;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
