package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import java.time.LocalDate;

public class Card {
    Integer id;
    Integer user_id;
    LocalDate duration;
    Integer last4;
    String type;
    Boolean is_default;


    @JdbiConstructor
    public Card(
            @ColumnName("id") @Nullable Integer id,
            @ColumnName("user_id")  @Nullable Integer user_id,
            @ColumnName("duration")  @Nullable LocalDate duration,
            @ColumnName("last4")  @Nullable Integer last4,
            @ColumnName("type")  @Nullable String type,
            @ColumnName("is_default")  @Nullable Boolean is_default) {

        this.id = id;
        this.user_id = user_id;
        this.duration = duration;
        this.last4 = last4;
        this.type = type;
        this.is_default = is_default;
    }


    public Card( ) {
    }

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

    public Boolean getIs_default() {
        return is_default;
    }

    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    public Integer getLast4() {
        return last4;
    }

    public void setLast4(Integer last4) {
        this.last4 = last4;
    }
}
