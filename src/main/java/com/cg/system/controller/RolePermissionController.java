package com.cg.system.controller;


import com.cg.common.Result;
import com.cg.entity.bo.system.RolePermissionBO;
import com.cg.system.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user_permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/getByRoleId")
    public Result getByRoleId(@RequestParam Long roleId) {
        return rolePermissionService.getByRoleId(roleId);
    }

    @PostMapping("/update")
    public Result update(@RequestBody RolePermissionBO rolePermissionBO) {
        rolePermissionService.update(rolePermissionBO);

        return Result.ok();
    }
}
