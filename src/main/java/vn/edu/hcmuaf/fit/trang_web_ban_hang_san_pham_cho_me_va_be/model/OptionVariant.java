package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OptionVariant {
    Integer id;
    Integer product_id;
    Integer price;
    Integer stock;
    Integer variant_id;
    String variant_name;
    String variant_value;
    public OptionVariant(@ColumnName("id") @Nullable Integer id,
                   @ColumnName("product_id") @Nullable Integer product_id,
                   @ColumnName("price") @Nullable Integer price,
                   @ColumnName("stock") @Nullable  Integer stock ,
                   @ColumnName("variant_id") @Nullable Integer variant_id,
                         @ColumnName("variant_name") @Nullable Integer variant_name,
                         @ColumnName("variant_value") @Nullable Integer variant_value
    ){
        this.id = id;
        this.product_id = product_id;
        this.price = price;
        this.stock = stock;
        this.variant_id = variant_id;
        this.variant_name = variant_name;
        this.variant_value = variant_value;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(Integer variant_id) {
        this.variant_id = variant_id;
    }

    public String getVariant_name() {
        return variant_name;
    }

    public void setVariant_name(String variant_name) {
        this.variant_name = variant_name;
    }

    public String getVariant_value() {
        return variant_value;
    }

    public void setVariant_value(String variant_value) {
        this.variant_value = variant_value;
    }

    @Override
    public String toString() {
        return "OptionVariant{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", price=" + price +
                ", stock=" + stock +
                ", variant_id=" + variant_id +
                ", variant_name='" + variant_name + '\'' +
                ", variant_value='" + variant_value + '\'' +
                '}';
    }
}
