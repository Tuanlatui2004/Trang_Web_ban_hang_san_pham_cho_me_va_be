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
    String phoneNumber;
    String email;
    String passwordUsername;
    Integer avatarId;
    String status;
    String role;
    String salt;
    String avatarUrl;

    // xem


    @JdbiConstructor
    public User(@ColumnName("id") Integer id,
                @ColumnName("fullName") @Nullable String fullName,
                @ColumnName("displayName") @Nullable String displayName,
                @ColumnName("dOB") @Nullable LocalDate dOB,
                @ColumnName("gender") @Nullable String gender,
                @ColumnName("phoneNumber") @Nullable String phoneNumber,
                @ColumnName("email") @Nullable String email,
                @ColumnName("passwordUsername") @Nullable String passwordUsername,
                @ColumnName("avatarId") @Nullable Integer avatarId,
                @ColumnName("status") @Nullable String status,
                @ColumnName("role") @Nullable String role,
                @ColumnName("salt") @Nullable String salt,
                @ColumnName("avatarUrl") @Nullable String avatarUrl

    ) {
        this.id = id;
        this.fullName = fullName;
        this.displayName = displayName;
        this.dOB = dOB;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordUsername = passwordUsername;
        this.avatarId = avatarId;
        this.status = status;
        this.role = role;
        this.salt = salt;
        this.avatarUrl = avatarUrl;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUsername() {
        return passwordUsername;
    }

    public void setPasswordUsername(String passwordUsername) {
        this.passwordUsername = passwordUsername;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dOB=" + dOB +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passwordUsername='" + passwordUsername + '\'' +
                ", avatarId=" + avatarId +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", salt='" + salt + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}