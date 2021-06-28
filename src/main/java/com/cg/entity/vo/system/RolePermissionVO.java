package com.cg.entity.vo.system;

import com.cg.entity.pojo.system.Permission;

import java.util.List;
import java.util.Objects;

public class RolePermissionVO {

    private Long roleId;
    private List<Permission> permissions;

    public RolePermissionVO() {
    }

    public RolePermissionVO(Long roleId, List<Permission> permissions) {
        this.roleId = roleId;
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RolePermissionVO setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public RolePermissionVO setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionVO that = (RolePermissionVO) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissions);
    }

    @Override
    public String toString() {
        return "RolePermissionVO{" +
                "roleId=" + roleId +
                ", permissions=" + permissions +
                '}';
    }
}
