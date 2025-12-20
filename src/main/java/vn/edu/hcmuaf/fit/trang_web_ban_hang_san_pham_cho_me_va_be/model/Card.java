package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;
import java.sql.Date;

public class Card implements Serializable {
    private int id;
    private int user_id;
    private Date duration;
    private String type;
    private int is_default;

    public Card(int id, int user_id, Date duration, String type, int is_default) {
        this.id = id;
        this.user_id = user_id;
        this.duration = duration;
        this.type = type;
        this.is_default = is_default;
    }

    public Card() {
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

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}
