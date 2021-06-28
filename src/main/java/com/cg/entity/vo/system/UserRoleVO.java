package com.cg.entity.vo.system;

import com.cg.entity.pojo.system.Role;

import java.util.List;
import java.util.Objects;

public class UserRoleVO {

    private Long userId;
    private List<Role> roleList;

    public UserRoleVO() {
    }

    public UserRoleVO(Long userId, List<Role> roleList) {
        this.userId = userId;
        this.roleList = roleList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public Long getUserId() {
        return userId;
    }

    public UserRoleVO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserRoleVO setRoleList(List<Role> roleList) {
        this.roleList = roleList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleVO userRole = (UserRoleVO) o;
        return Objects.equals(userId, userRole.userId) &&
                Objects.equals(roleList, userRole.roleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleList);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleList=" + roleList +
                '}';
    }
}
