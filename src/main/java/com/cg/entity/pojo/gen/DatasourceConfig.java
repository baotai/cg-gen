package com.cg.entity.pojo.gen;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class DatasourceConfig extends BaseDO {

    private Long userId;
    private Integer dbType;
    private String driverClass;
    private String dbName;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String packageName;
    private String delPrefix;
    private Long groupId;

    public DatasourceConfig() {
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getDbType() {
        return dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getDbName() {
        return dbName;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDelPrefix() {
        return delPrefix;
    }

    public Long getGroupId() {
        return groupId;
    }

    public DatasourceConfig setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public DatasourceConfig setDbType(Integer dbType) {
        this.dbType = dbType;
        return this;
    }

    public DatasourceConfig setDriverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public DatasourceConfig setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public DatasourceConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public DatasourceConfig setPort(Integer port) {
        this.port = port;
        return this;
    }

    public DatasourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public DatasourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public DatasourceConfig setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public DatasourceConfig setDelPrefix(String delPrefix) {
        this.delPrefix = delPrefix;
        return this;
    }

    public DatasourceConfig setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DatasourceConfig that = (DatasourceConfig) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(dbType, that.dbType) &&
                Objects.equals(driverClass, that.driverClass) &&
                Objects.equals(dbName, that.dbName) &&
                Objects.equals(host, that.host) &&
                Objects.equals(port, that.port) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(delPrefix, that.delPrefix) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, dbType, driverClass, dbName, host, port, username, password, packageName, delPrefix, groupId);
    }

    @Override
    public String toString() {
        return "DatasourceConfig{" +
                "userId=" + userId +
                ", dbType=" + dbType +
                ", driverClass='" + driverClass + '\'' +
                ", dbName='" + dbName + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", packageName='" + packageName + '\'' +
                ", delPrefix='" + delPrefix + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
