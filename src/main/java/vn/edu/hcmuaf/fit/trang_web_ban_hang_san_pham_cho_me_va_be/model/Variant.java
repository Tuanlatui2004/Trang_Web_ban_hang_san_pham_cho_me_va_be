package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Variant {
    Integer id;
    Integer category_id;
    String name;
    String value;
    Integer option_id;

    @JdbiConstructor
    public Variant(@ColumnName("id") @Nullable Integer id,
                   @ColumnName("category_id") @Nullable Integer category_id,
                   @ColumnName("name") @Nullable String name,
                   @ColumnName("value") @Nullable String value,
                   @ColumnName("option_id") @Nullable Integer option_id
                   ){
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.value = value;
        this.option_id = option_id;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }
}
