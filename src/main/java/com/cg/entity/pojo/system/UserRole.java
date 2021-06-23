package com.cg.entity.pojo.system;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class UserRole extends BaseDO {

    private Long userId;
    private Long roleId;

    public UserRole() {
    }

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public UserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userId, userRole.userId) &&
                Objects.equals(roleId, userRole.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, roleId);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
