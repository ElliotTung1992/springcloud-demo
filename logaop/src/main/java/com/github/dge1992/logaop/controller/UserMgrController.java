package com.github.dge1992.logaop.controller;

import com.github.dge1992.logaop.annotion.BussinessLog;
import com.github.dge1992.logaop.bean.UserDto;
import com.github.dge1992.logaop.core.LogObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/mgr")
public class UserMgrController {

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员")
    @ResponseBody
    public Object edit(@Valid UserDto user, BindingResult result, HttpServletRequest request) throws NoPermissionException {
        String id = request.getParameter("id");
        user.setName("dong");
        LogObjectHolder.me().set(user);
        return null;
    }
}
