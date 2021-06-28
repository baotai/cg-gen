package com.cg.system.controller;

import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.Permission;
import com.cg.entity.pojo.system.Role;
import com.cg.system.service.PermissionService;
import com.cg.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/findByKeyword")
    public Result findByKeyword(@RequestParam String keyword,
                                @RequestParam Integer currentPage,
                                @RequestParam Integer pageSize) {

        currentPage = (currentPage == null || currentPage == 0) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize == 0) ? 10 : pageSize;

        PagedResult result = permissionService.findByKeyword(keyword, currentPage, pageSize);
        return Result.ok(result);
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody Permission permission) {
        return permissionService.updateById(permission);
    }

    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody Permission permission) {
        return permissionService.deleteById(permission);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }
}
