package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class CategoryWithStock {
    private Integer id;
    private String name;
    private Integer total_stock;
    private Boolean is_active;

    public CategoryWithStock(@ColumnName("id") @Nullable Integer id, @ColumnName("name") @Nullable String name, @ColumnName("total_stock") @Nullable Integer total_stock, @ColumnName("is_active") @Nullable Boolean is_active) {
        this.id = id;
        this.name = name;
        this.total_stock = total_stock;
        this.is_active = is_active;
    }
    public CategoryWithStock() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(Integer total_stock) {
        this.total_stock = total_stock;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "CategoryWithStock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total_stock=" + total_stock +
                ", is_active=" + is_active +
                '}';
    }
}
