package com.cg.system.service;

import com.cg.entity.bo.system.UserBO;
import com.cg.entity.pojo.system.Role;
import com.cg.entity.pojo.system.User;
import com.cg.entity.pojo.system.UserRole;
import com.cg.utils.CurrentUserUtils;
import com.cg.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CurrentUserUtils currentUserUtils;

    public String createToken(UserBO userBO) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        User user = userService.findByUsername(username);

        if (!userService.checkPassword(user, password)) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }

        user.setSalt("").setPassword("");
        Long userId = user.getId();

        String token = TokenUtils.createToken(username, userId.toString(), getRoleNames(userId), false);

        stringRedisTemplate.opsForValue().set(username, token);
        return token;
    }

    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getUsername());
    }

    private List<String> getRoleNames(Long userId) {

        List<String> roleNames = new LinkedList<>();

        List<UserRole> userRoles = userRoleService.findByUserId(userId);

        if (userRoles != null && userRoles.size() > 0) {

            List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());

            List<Role> roleList = roleService.findByIdList(roleIds);

            if (roleList != null && roleList.size() > 0) {
                roleNames.addAll(roleList.stream().map(Role::getRoleName).collect(Collectors.toList()));
            }
        }

        return roleNames;
    }
}
