package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
    public class Address {
    Integer id;
    Integer user_id;
    String address_type;
    String full_name;
    String phone_number;
    String street;
    String city;
    String state;
    String country;
    Integer is_default;

    @JdbiConstructor
    public Address(@ColumnName("id") Integer id,
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
        if (is_default==1) {
            this.is_default = 1;
        }
        else this.is_default = is_default;

    }
    public Address() {

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {}

}
