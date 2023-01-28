package com.mimeay.mimeay_security.controller;


import com.google.gson.Gson;
import com.mimeay.mimeay_security.annotations.MimeayUserDetail;
import com.mimeay.mimeay_security.domian.business.MimeayDetail;
import com.mimeay.mimeay_security.domian.inbound.UserAuthInbound;
import com.mimeay.mimeay_security.helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 16:43
 */
@RestController
public class IndexController {

    @Autowired
    UserHelper userHelper;

    @PostMapping("/login")
    String login(
            @RequestBody UserAuthInbound userAuthInbound
    ) {
        Boolean hit = userHelper.authLogin(userAuthInbound);
        if (hit) {
            return userHelper.setLoginStatus(userAuthInbound);
        }
        return "干嘛呢，账号或者密码不对";
    }


    /**
     * 获取用户信息接口，无权限判断
     *
     * @return
     */
    @GetMapping("/no-public/user-info")
    MimeayDetail getUserInfo(
    ) {
        MimeayDetail mimeayDetail = (MimeayDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mimeayDetail;
    }

    /**
     * 业务接口，需登录才能访问
     *
     * @return
     */
    @GetMapping("/no-public/world")
    String world() {
        return "hello world";
    }

    /**
     * 公有接口，无需登录
     *
     * @return
     */
    @GetMapping("/public/hello")
    String hello() {
        return "success";
    }


    /**
     * 权限为 USER 的才能访问
     *
     * @param mimeayDetail
     * @return
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping("/no-public/user-role")
    String role(
            @AuthenticationPrincipal MimeayDetail mimeayDetail
    ) {
        return "hello user:" + mimeayDetail.getId() + ", nickname:" + mimeayDetail.getNickname();
    }

    /**
     * @param mimeayDetail
     * @return
     */
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/no-public/admin-role")
    String adminRole(
            @AuthenticationPrincipal MimeayDetail mimeayDetail
    ) {
        return "hello adminRole, id: " + mimeayDetail.getId() + ", nickname:" + mimeayDetail.getNickname() + ", 权限列表: " + new Gson().toJson(mimeayDetail.getRoles());
    }

    /**
     * 以下为各种获取用户信息的方式
     */

    @GetMapping("/no-public/user-info-0")
    String getUserInfo0(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ) {
        return authentication.getName();
    }

    @GetMapping("/no-public/user-info-1")
    MimeayDetail getUserInfo1(
            @MimeayUserDetail MimeayDetail mimeayDetail
    ) {
        return mimeayDetail;
    }

    @GetMapping("/no-public/user-info-2")
    String getUserInfo2(
            @AuthenticationPrincipal(expression = "nickname") String nickname
    ) {
        return nickname;
    }

    @GetMapping("/no-public/user-info-3")
    String getUserInfo3(
            Principal principal
    ) {
        return principal.getName();
    }

    @GetMapping("/no-public/user-info-4")
    MimeayDetail getUserInfo4(
            Authentication authentication
    ) {
        MimeayDetail mimeayDetail = (MimeayDetail) authentication.getPrincipal();
        return mimeayDetail;
    }
}
