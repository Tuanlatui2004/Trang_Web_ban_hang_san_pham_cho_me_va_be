package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double total;

    public OrderDetail(int id, int order_id, int product_id, int quantity, double total) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
