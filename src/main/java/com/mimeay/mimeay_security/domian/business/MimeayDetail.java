package com.mimeay.mimeay_security.domian.business;

import java.util.List;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 17:58
 */
public class MimeayDetail {

    Long id;
    String nickname;
    List<String> roles;

    public MimeayDetail(Long id, String nickname, List<String> roles) {
        this.id = id;
        this.nickname = nickname;
        this.roles = roles;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
