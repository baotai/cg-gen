package com.cg.entity.pojo.system;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class Role extends BaseDO {

    private String roleName;
    private String desc;

    public Role() {
    }

    public Role(String roleName, String desc) {
        this.roleName = roleName;
        this.desc = desc;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDesc() {
        return desc;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public Role setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName) &&
                Objects.equals(desc, role.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName, desc);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
