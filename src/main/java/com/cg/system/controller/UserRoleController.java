package com.cg.system.controller;

import com.cg.common.Result;
import com.cg.entity.bo.system.UserRoleBO;
import com.cg.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user_role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/getByUserId")
    public Result getByUserId(@RequestParam Long userId) {
        return userRoleService.getByUserId(userId);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserRoleBO userRoleBO) {
        userRoleService.update(userRoleBO);

        return Result.ok();
    }
}
