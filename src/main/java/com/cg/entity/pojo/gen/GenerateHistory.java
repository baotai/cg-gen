package com.cg.entity.pojo.gen;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class GenerateHistory extends BaseDO {

    private Long userId;
    private String content;
    private String md5;

    public GenerateHistory() {
    }

    public GenerateHistory(Long userId, String content, String md5) {
        this.userId = userId;
        this.content = content;
        this.md5 = md5;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getMd5() {
        return md5;
    }

    public GenerateHistory setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public GenerateHistory setContent(String content) {
        this.content = content;
        return this;
    }

    public GenerateHistory setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenerateHistory that = (GenerateHistory) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(md5, that.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, content, md5);
    }

    @Override
    public String toString() {
        return "GenerateHistory{" +
                "userId=" + userId +
                ", content='" + content + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
