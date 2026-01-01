package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class OrderDetail{
    Integer id;
    Integer order_id;
    Integer product_id;
    Integer quantity;
    Integer total;
    Integer option_id;
    String product_name;
    String image_url;

    @JdbiConstructor
    public OrderDetail(@ColumnName("id") @Nullable Integer id,
                       @ColumnName("order_id") @Nullable Integer order_id,
                       @ColumnName("product_id") @Nullable Integer product_id,
                       @ColumnName("quantity") @Nullable Integer quantity,
                       @ColumnName("total") @Nullable Integer total,
                       @ColumnName("option_id") @Nullable Integer option_id,
                       @ColumnName("product_name") @Nullable String product_name,
                       @ColumnName("image_url") @Nullable String image_url
    ) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
        this.option_id = option_id;
        this.product_name = product_name;
        this.image_url = image_url;
    }

    public OrderDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", total=" + total +
                ", option_id=" + option_id +
                ", product_name='" + product_name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
