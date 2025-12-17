package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int id;
    private String fullName;
    private String displayName;
    private Date dOB;
    private String gender;
    private String phone_number;
    private String email;
    private String password_Username;
    private int avatar_id;
    private String status;
    private String role;
    private String confirmationToken;

    public User(int id, String fullName, String displayName, Date dOB, String gender, String phone_number, String email, String password_Username, int avatar_id, String status, String role, String confirmationToken) {
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
        this.confirmationToken = confirmationToken;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getdOB() {
        return dOB;
    }

    public void setdOB(Date dOB) {
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

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
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

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
