package com.mimeay.mimeay_security.domian.business;

import java.util.List;

/**
 * @author: chenxiaochi
 * @date: 2023/1/19 14:32
 */
public class User {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private List<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User(Long id, String nickname, String username, String password, List<String> roles) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
