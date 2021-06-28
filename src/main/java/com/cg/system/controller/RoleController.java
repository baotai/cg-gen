package com.cg.system.controller;

import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.Role;
import com.cg.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/findByKeyword")
    public Result findByKeyword(@RequestParam String keyword,
                                @RequestParam Integer currentPage,
                                @RequestParam Integer pageSize) {

        currentPage = (currentPage == null || currentPage == 0) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize == 0) ? 100 : pageSize;

        PagedResult result = roleService.findByKeyword(keyword, currentPage, pageSize);
        return Result.ok(result);
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody Role role) {
        return roleService.updateById(role);
    }

    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody Role role) {
        return roleService.deleteById(role);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        return roleService.save(role);
    }
}
