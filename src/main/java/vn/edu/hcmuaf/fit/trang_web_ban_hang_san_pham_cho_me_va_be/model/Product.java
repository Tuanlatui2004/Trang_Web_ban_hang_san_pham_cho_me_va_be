package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String sku;
    private String description;
    private int is_active;
    private int brand_id;
    private int category_id;
    private int no_of_views;
    private int no_of_sold;
    private int image_id;

    public Product(int id, String name, String sku, String description, int is_active, int brand_id, int category_id, int no_of_views, int no_of_sold, int image_id) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.is_active = is_active;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.no_of_views = no_of_views;
        this.no_of_sold = no_of_sold;
        this.image_id = image_id;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getNo_of_views() {
        return no_of_views;
    }

    public void setNo_of_views(int no_of_views) {
        this.no_of_views = no_of_views;
    }

    public int getNo_of_sold() {
        return no_of_sold;
    }

    public void setNo_of_sold(int no_of_sold) {
        this.no_of_sold = no_of_sold;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
