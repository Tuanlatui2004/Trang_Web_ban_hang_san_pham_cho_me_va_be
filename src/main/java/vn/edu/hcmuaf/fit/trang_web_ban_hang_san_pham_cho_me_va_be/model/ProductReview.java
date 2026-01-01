package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class ProductReview{
    Integer id;
    Integer user_id;
    Integer product_id;
    Integer rating;
    String description;
    Integer order_id;


    @JdbiConstructor
    public ProductReview(@ColumnName("id") @Nullable Integer id,
                  @ColumnName("user_id") @Nullable Integer user_id,
                  @ColumnName("product_id") @Nullable Integer product_id,
                  @ColumnName("rating")@Nullable Integer rating,
                  @ColumnName("description") @Nullable String description,
                  @ColumnName("order_id") @Nullable Integer order_id)
    {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.rating = rating;
        this.description = description;
        this.order_id = order_id;
    }

    public ProductReview() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", order_id=" + order_id +
                '}';
    }
}
