package com.cg.system.controller;

import com.cg.common.Result;
import com.cg.entity.bo.system.UserBO;
import com.cg.system.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  认证授权
 **/
@RestController
@RequestMapping("/system/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody UserBO userBO) {
        if (userBO == null || StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            return Result.errorMsg("用户名和密码不能为空！");
        }
        String token = authService.createToken(userBO);
        return Result.ok(token);
    }

    @PostMapping("/logout")
    public Result logout() {
        authService.removeToken();
        return Result.ok("success");
    }
}
