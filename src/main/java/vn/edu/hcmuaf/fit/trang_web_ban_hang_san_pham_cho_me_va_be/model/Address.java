package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private int user_id;
    private String address_type; // shipping | billing
    private String full_name;
    private String phone_number;
    private String street;
    private String city;
    private String state;
    private String country;
    private int is_default;

    public Address(int id, int user_id, String address_type, String full_name, String phone_number, String street, String city, String state, String country, int is_default) {
        this.id = id;
        this.user_id = user_id;
        this.address_type = address_type;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.is_default = is_default;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}
