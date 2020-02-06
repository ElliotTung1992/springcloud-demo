package com.github.dge1992.common.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/14
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private List<String> permissionServices;
}
