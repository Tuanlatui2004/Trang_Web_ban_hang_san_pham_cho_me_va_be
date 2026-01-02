package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.time.LocalDate;

public class User {
    Integer id;
    String fullName;
    String displayName;
    LocalDate dOB;
    String gender;
    String phone_number;
    String email;
    String password_Username;
    Integer avatar_id;
    String status;
    String role;
    String salt;
    String avatar_url;

    // xem


    @JdbiConstructor
    public User(@ColumnName("id") Integer id,
                @ColumnName("fullName") @Nullable String fullName,
                @ColumnName("displayName") @Nullable String displayName,
                @ColumnName("dOB") @Nullable LocalDate dOB,
                @ColumnName("gender") @Nullable String gender,
                @ColumnName("phone") @Nullable String phone_number,
                @ColumnName("email") @Nullable String email,
                @ColumnName("password") @Nullable String password_Username,
                @ColumnName("avatarId") @Nullable Integer avatar_id,
                @ColumnName("status") @Nullable String status,
                @ColumnName("role") @Nullable String role,
                @ColumnName("salt") @Nullable String salt,
                @ColumnName("avatar_url") @Nullable String avatar_url

    ) {
        this.id = id;
        this.fullName = fullName;
        this.displayName = displayName;
        this.dOB = dOB;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.password_Username = password_Username;
        this.avatar_id = avatar_id;
        this.status = status;
        this.role = role;
        this.salt = salt;
        this.avatar_url = avatar_url;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public LocalDate getdOB() {
        return dOB;
    }

    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_Username() {
        return password_Username;
    }

    public void setPassword_Username(String password_Username) {
        this.password_Username = password_Username;
    }

    public Integer getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(Integer avatar_id) {
        this.avatar_id = avatar_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dOB=" + dOB +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password_Username='" + password_Username + '\'' +
                ", avatar_id=" + avatar_id +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", salt='" + salt + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}