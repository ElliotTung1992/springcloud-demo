package com.github.dge1992.consumer.controller;

import com.github.dge1992.common.domain.User;
import com.github.dge1992.commonserver.jwt.JwtTokenHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 董感恩
 * @date 2020-02-06 13:55
 * @desc 认证控制器
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    /**
     * @author 董感恩
     * @date 2020-02-06 14:25:38
     * @desc 用户认证
     **/
    @PostMapping("${jwt.auth-path}")
    public Object auth(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && "admin".equals(username) && "admin".equals(password)){
            Map<String, Object> claims = new HashMap<>();
            List<String> permissionServices = new ArrayList<>();
            permissionServices.add("consumer-server");
            claims.put("permissionServices", permissionServices);
            String token = jwtTokenHandler.generateToken(username, claims);
            return token;
        }
        return null;
    }
}
