package com.cg.entity.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class BaseDO implements Serializable {

    protected static final long serialVersionUID = 1L;

    private Long id;
    private Byte deleted;
    private String cgCreatedBy;
    private Date cgCreated;
    private String cgModifiedBy;
    private Date cgModified;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getCgCreatedBy() {
        return cgCreatedBy;
    }

    public void setCgCreatedBy(String cgCreatedBy) {
        this.cgCreatedBy = cgCreatedBy;
    }

    public Date getCgCreated() {
        return cgCreated;
    }

    public void setCgCreated(Date cgCreated) {
        this.cgCreated = cgCreated;
    }

    public String getCgModifiedBy() {
        return cgModifiedBy;
    }

    public void setCgModifiedBy(String cgModifiedBy) {
        this.cgModifiedBy = cgModifiedBy;
    }

    public Date getCgModified() {
        return cgModified;
    }

    public void setCgModified(Date cgModified) {
        this.cgModified = cgModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDO baseDO = (BaseDO) o;
        return Objects.equals(id, baseDO.id) &&
                Objects.equals(deleted, baseDO.deleted) &&
                Objects.equals(cgCreatedBy, baseDO.cgCreatedBy) &&
                Objects.equals(cgCreated, baseDO.cgCreated) &&
                Objects.equals(cgModifiedBy, baseDO.cgModifiedBy) &&
                Objects.equals(cgModified, baseDO.cgModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cgCreatedBy, cgCreated, cgModifiedBy, cgModified);
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cgCreatedBy='" + cgCreatedBy + '\'' +
                ", cgCreated=" + cgCreated +
                ", cgModifiedBy='" + cgModifiedBy + '\'' +
                ", cgModified=" + cgModified +
                '}';
    }
}
