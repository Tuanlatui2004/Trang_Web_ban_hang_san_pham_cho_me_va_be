package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.time.LocalDate;

public class Card {
    Integer id;
    Integer user_id;
    Integer last4;
    LocalDate duration;
    String type;
    Boolean isDefault;


    @JdbiConstructor
    public Card(
            @ColumnName("id") @Nullable Integer id,
            @ColumnName("last4") @Nullable Integer last4,
            @ColumnName("user_id")  @Nullable Integer user_id,
            @ColumnName("duration")  @Nullable LocalDate duration,
            @ColumnName("type")  @Nullable String type,
            @ColumnName("isDefault")  @Nullable Boolean isDefault) {

        this.id = id;
        this.user_id = user_id;
        this.duration = duration;
        this.type = type;
        this.isDefault = isDefault;
        this.last4 = last4;
    }


    public Card( ) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = this.user_id;
    }

    public Integer getLast4() {
        return last4;
    }

    public void setLast4(Integer last4) {
        this.last4 = last4;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

}
