package com.cg.system.controller;

import com.cg.common.Result;
import com.cg.entity.bo.system.UserRoleBO;
import com.cg.entity.pojo.system.UserRole;
import com.cg.enums.DeletedStatus;
import com.cg.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user_role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/getByUserId")
    public Result getByUserId(@RequestParam Long userId) {
        return userRoleService.getByUserId(userId);
    }

    @GetMapping("/findByUserId")
    public Result findByUserId(@RequestParam Long userId) {
        List<UserRole> userRoles = userRoleService.findByUserId(userId);

        Long[] roleIds = (userRoles == null || userRoles.size() == 0) ?
                new Long[0] :
                userRoles
                        .stream()
                        .filter(e -> DeletedStatus.NON_DELETED.getCode().equals(e.getDeleted()))
                        .map(UserRole::getRoleId).toArray(Long[]::new);

        return Result.ok(roleIds);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserRoleBO userRoleBO) {
        userRoleService.update(userRoleBO);

        return Result.ok();
    }
}
