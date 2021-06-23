package com.cg.entity.pojo.gen;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class TemplateGroup extends BaseDO {

    private Long userId;
    private String name;

    public TemplateGroup() {
    }

    public TemplateGroup(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public TemplateGroup setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TemplateGroup setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TemplateGroup that = (TemplateGroup) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, name);
    }

    @Override
    public String toString() {
        return "TemplateGroup{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
