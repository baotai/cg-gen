package com.cg.entity.bo.system;

import java.util.List;
import java.util.Objects;

public class RolePermissionBO {

    private Long roleId;
    private List<Long> permissionIds;

    public RolePermissionBO() {
    }

    public RolePermissionBO(Long roleId, List<Long> permissionIds) {
        this.roleId = roleId;
        this.permissionIds = permissionIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RolePermissionBO setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public RolePermissionBO setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionBO that = (RolePermissionBO) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissionIds, that.permissionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionIds);
    }

    @Override
    public String toString() {
        return "RolePermissionBO{" +
                "roleId=" + roleId +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
