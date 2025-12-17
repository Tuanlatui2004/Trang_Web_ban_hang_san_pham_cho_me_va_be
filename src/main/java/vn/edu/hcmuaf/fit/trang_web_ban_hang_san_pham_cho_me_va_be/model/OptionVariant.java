package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;

public class OptionVariant implements Serializable {
    private int id;
    private int product_id;
    private int price;
    private int stock;

    public OptionVariant(int id, int product_id, int price, int stock) {
        this.id = id;
        this.product_id = product_id;
        this.price = price;
        this.stock = stock;
    }

    public OptionVariant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
