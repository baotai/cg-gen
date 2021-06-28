package com.cg.system.controller;

import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.User;
import com.cg.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByKeyword")
    public Result findByKeyword(@RequestParam String keyword,
                                @RequestParam Integer currentPage,
                                @RequestParam Integer pageSize) {

        currentPage = (currentPage == null || currentPage == 0) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize == 0) ? 10 : pageSize;

        PagedResult result = userService.findByKeyword(keyword, currentPage, pageSize);
        return Result.ok(result);
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody User user) {
        return userService.deleteById(user);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user);
    }
}
