package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Attribute {
    Integer id;
    String category_id;
    String name;

    public Attribute(@ColumnName("id") Integer id,
                     @ColumnName("category_id") String category_id,
                     @ColumnName("name") String name) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
    }


}
