package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;
import java.sql.Date;

public class Banner implements Serializable {
    private int id;
    private int image_id;
    private String status;
    private Date start_date;
    private Date end_date;

    public Banner(int id, int image_id, String status, Date start_date, Date end_date) {
        this.id = id;
        this.image_id = image_id;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Banner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
