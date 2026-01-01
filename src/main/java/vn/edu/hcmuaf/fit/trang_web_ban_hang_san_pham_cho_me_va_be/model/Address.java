package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    Integer id;
    Integer user_id;
    String address_type; // shipping | billing
    String full_name;
    String phone_number;
    String street;
    String city;
    String state;
    String country;
    Integer is_default;

    @JdbiConstructor
    public Address(@ColumnName("id")  Integer id,
                   @ColumnName("user_id") @Nullable Integer user_id,
                   @ColumnName("address_type") @Nullable String address_type,
                   @ColumnName("full_name") @Nullable String full_name,
                   @ColumnName("phone_number") @Nullable String phone_number,
                   @ColumnName("street") @Nullable String street,
                   @ColumnName("city") @Nullable String city,
                   @ColumnName("state") @Nullable String state,
                   @ColumnName("country") @Nullable String country,
                   @ColumnName("is_default") @Nullable Integer is_default
    ) {
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
    public Address() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", address_type='" + address_type + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", is_default=" + is_default +
                '}';
    }
}
