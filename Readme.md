# 项目数据源读取

为方便运行演示，数据源放在内存里，见 UserHelper

# 项目细节

## 移除 Spring security ROLE_ 前缀

> 通过注入 Bean 的方式

```
    /**
     * 调整角色前缀，不注入默认会加 ROLE_
     * @return
     */
    @Bean
    public static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
```

使用方式 只需要加上 `@RolesAllowed("ADMIN")` 注解即可

* 生效的注解: `@RolesAllowed("ADMIN")` ✅
* 不再起作用的注解: `@RolesAllowed("ROLE_ADMIN")`

```
    /**
     * @param mimeayDetail
     * @return
     */
    @RolesAllowed("ADMIN")
    @GetMapping("/no-public/admin-role")
    String adminRole(
            @AuthenticationPrincipal MimeayDetail mimeayDetail
    ) {
        return "hello adminRole, id: " + mimeayDetail.getId() + ", nickname:" + mimeayDetail.getNickname() + ", 权限列表: " + new Gson().toJson(mimeayDetail.getRoles());
    }
```

## 汇总了多个获取当前鉴权信息的方法
> 详细见 IndexController
> 
相关代码
``` 
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
    ```

