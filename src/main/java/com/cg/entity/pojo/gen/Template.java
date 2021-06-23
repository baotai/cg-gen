package com.cg.entity.pojo.gen;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class Template extends BaseDO {

    private Long userId;
    private Long groupId;
    private String name;
    private String fileName;
    private String content;

    public Template() {
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public Template setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Template setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Template setName(String name) {
        this.name = name;
        return this;
    }

    public Template setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Template setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Template template = (Template) o;
        return Objects.equals(userId, template.userId) &&
                Objects.equals(groupId, template.groupId) &&
                Objects.equals(name, template.name) &&
                Objects.equals(fileName, template.fileName) &&
                Objects.equals(content, template.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, groupId, name, fileName, content);
    }

    @Override
    public String toString() {
        return "Template{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
