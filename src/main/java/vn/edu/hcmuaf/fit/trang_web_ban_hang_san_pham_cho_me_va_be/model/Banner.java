package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.time.LocalDate;

public class Banner  {
    Integer id;
    String image_id;
    LocalDate start_date;
    LocalDate end_date;
    String status;

    public Banner(@ColumnName("id") Integer id,
                  @ColumnName("image_id") String image_id,
                  @ColumnName("start_date") LocalDate start_date,
                  @ColumnName("end_date") LocalDate end_date,
                  @ColumnName("status") String status
    ) {
        this.id = id;
        this.image_id = image_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
