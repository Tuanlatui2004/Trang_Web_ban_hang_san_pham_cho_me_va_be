package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Brand {
    Integer id;
    String name;
    Boolean is_active;


    @JdbiConstructor
    public Brand(@ColumnName("id") Integer id, @ColumnName("name") String name, @ColumnName("is_active") Boolean is_active) {
        this.id = id;
        this.name = name;
        this.is_active = is_active;
    }

    public Brand() {
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}