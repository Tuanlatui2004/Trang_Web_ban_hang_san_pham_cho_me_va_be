package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;

public class ProductReview implements Serializable {
    private int id;
    private int user_id;
    private int product_id;
    private int rating;
    private String description;

    public ProductReview(int id, int user_id, int product_id, int rating, String description) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.rating = rating;
        this.description = description;
    }

    public ProductReview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
