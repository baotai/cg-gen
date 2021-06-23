package com.cg.entity.pojo.system;

import com.cg.entity.pojo.BaseDO;

import java.util.Objects;

public class User extends BaseDO {

    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String email;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(mobile, user.mobile) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, salt, mobile, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
