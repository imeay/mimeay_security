package com.mimeay.mimeay_security.helpers;


import com.mimeay.mimeay_security.domian.business.User;
import com.mimeay.mimeay_security.domian.inbound.UserAuthInbound;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户查询类
 * 为方便测试功能，数据源直接写在代码内，实际业务开发应调整为读取数据库
 *
 * @author: chenxiaochi
 * @date: 2023/1/19 14:52
 */
@Component
public class UserHelper {

    private final List<User> users = Lists.newArrayList(
            new User(1L, "陈", "mimeay", "123456", Lists.newArrayList("ROLE_USER")),
            new User(2L, "晓池", "ithankzc", "123456", Lists.newArrayList("ROLE_ADMIN"))
    );

    private User getUserById(Long id) {
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2));
        return userMap.get(id);
    }

    private User getUserByUsername(String username) {
        if (username == null) {
            return null;
        }
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getUsername, Function.identity(), (key1, key2) -> key2));
        return userMap.get(username);
    }

    public Boolean authLogin(UserAuthInbound userAuthInbound) {
        String username = userAuthInbound.getUsername();
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getUsername, Function.identity(), (key1, key2) -> key2));
        User user = userMap.get(username);
        return user != null && user.getPassword().equals(userAuthInbound.getPassword());
    }

    public String setLoginStatus(UserAuthInbound userAuthInbound) {
        return new Base64().encodeAsString((userAuthInbound.getUsername() + ":" + userAuthInbound.getPassword()).getBytes());
    }

    public User getCurrentUser(String token) {
        if (token == null) {
            return null;
        }
        try {
            String sourceData = new String(new Base64().decode(token.getBytes()));
            List<String> userData = Arrays.stream(sourceData.split(":")).collect(Collectors.toList());
            return this.getUserByUsername(userData.get(0));
        } catch (Exception e) {
            return null;
        }
    }

    public String getToken(HttpServletRequest httpRequest) {
        return httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
