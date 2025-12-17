package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import  com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.beans.ConstructorProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    Integer id;
    String name;
    String sku;
    String description;
    Boolean isActive;
    Integer categoryId;
    Integer brandId;
    Integer noOfViews;
    Integer noOfSold;


    public Product() {
    }

    public Integer getId() {
        return id;
    }
}
