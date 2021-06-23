package com.cg.entity.pojo.system;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class Permission extends BaseDO {

    private String name;
    private String desc;
    private String url;

    public Permission() {
    }

    public Permission(String name, String desc, String url) {
        this.name = name;
        this.desc = desc;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    public Permission setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Permission setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Permission that = (Permission) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, desc, url);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
