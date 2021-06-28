package com.cg.entity.bo.system;

import java.util.List;
import java.util.Objects;

public class UserRoleBO {

    private Long userId;
    private List<Long> roleIds;

    public UserRoleBO() {
    }

    public UserRoleBO(Long userId, List<Long> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public UserRoleBO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public UserRoleBO setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleBO that = (UserRoleBO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleIds, that.roleIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleIds);
    }

    @Override
    public String toString() {
        return "UserRoleRequest{" +
                "userId=" + userId +
                ", roleIds=" + roleIds +
                '}';
    }
}
