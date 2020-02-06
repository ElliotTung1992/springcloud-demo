package com.github.dge1992.commonserver;

import com.github.dge1992.commonserver.jwt.JwtProperties;
import com.github.dge1992.commonserver.jwt.JwtTokenHandler;
import com.github.dge1992.commonserver.common.security.impl.Base64SecurityAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 董感恩
 * @date 2020-02-05 21:22
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtTokenHandler jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    public void testJwt(){
//        String jwt = jwtTokenUtil.generateToken("dge", jwtProperties.getMd5Key());
//        System.out.println(jwt);
    }

    public static void main(String[] args) {
        String aa = new Base64SecurityAction().unlock("eyJyYW5kb21LZXkiOiJyYW5kb21LZXkiLCJzdWIiOiJkZ2UiLCJleHAiOjE1ODE1MTM4MzQsImlhdCI6MTU4MDkwOTAzNH0");
        System.out.println(aa);
    }
}
