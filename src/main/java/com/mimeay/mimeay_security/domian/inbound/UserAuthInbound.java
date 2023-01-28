package com.mimeay.mimeay_security.domian.inbound;

/**
 * @author: chenxiaochi
 * @date: 2023/1/19 14:37
 */
public class UserAuthInbound {
    private String username;
    private String password;

    public UserAuthInbound(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
