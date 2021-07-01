package com.cg.system.controller;


import com.cg.common.Result;
import com.cg.entity.bo.system.RolePermissionBO;
import com.cg.entity.pojo.system.RolePermission;
import com.cg.enums.DeletedStatus;
import com.cg.system.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role_permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/getByRoleId")
    public Result getByRoleId(@RequestParam Long roleId) {
        return rolePermissionService.getByRoleId(roleId);
    }

    @GetMapping("/findByRoleId")
    public Result findByRoleId(@RequestParam Long roleId) {

        List<RolePermission> rolePermissions = rolePermissionService.findByRoleId(roleId);

        Long[] permissionIds = (rolePermissions == null || rolePermissions.size() == 0) ?
                new Long[0] :
                rolePermissions
                        .stream()
                        .filter(e -> DeletedStatus.NON_DELETED.getCode().equals(e.getDeleted()))
                        .map(RolePermission::getPermissionId).toArray(Long[]::new);

        return Result.ok(permissionIds);
    }

    @PostMapping("/update")
    public Result update(@RequestBody RolePermissionBO rolePermissionBO) {
        rolePermissionService.update(rolePermissionBO);

        return Result.ok();
    }
}
