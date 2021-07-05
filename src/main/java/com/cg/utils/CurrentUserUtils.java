package com.cg.utils;

import com.cg.entity.pojo.system.User;
import com.cg.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 *  获取当前请求的用户
 */
@Component
public class CurrentUserUtils {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        return userService.findByUsername(getCurrentUsername());
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return String.valueOf(principal);
    }
}
