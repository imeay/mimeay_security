package com.example.mimeay_security.controller;

import com.example.mimeay_security.MimeayDetail;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

/**
 * @author: chenxiaochi
 * @date: 2023/1/18 16:43
 */
@RestController
public class IndexController {

    @GetMapping("/public/hello")
    String hello() {
        return "success";
    }


    @GetMapping("/no-public/world")
    String world() {
        return "hello world";
    }

    @Secured("ROLE_USER")
    @GetMapping("/no-public/world-role")
    String role(
            @AuthenticationPrincipal MimeayDetail mimeayDetail
    ) {

        return "hello user:" + mimeayDetail.getId() + ", nickname:" + mimeayDetail.getNickname();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/no-public/admin-role")
    String adminRole() {
        return "hello adminRole";
    }
}
