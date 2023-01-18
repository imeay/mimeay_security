package com.example.mimeay_security;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 17:58
 */
public class MimeayDetail {

    Long id;
    String nickname;

    public MimeayDetail(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
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
}
