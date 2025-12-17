package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private Date create_at;
    private String payment_status;
    private String order_status;
    private int user_id;
    private int address_id;
    private int cart_id;
    private int isCOD;

    public Order(int id, Date create_at, String payment_status, String order_status, int user_id, int address_id, int cart_id, int isCOD) {
        this.id = id;
        this.create_at = create_at;
        this.payment_status = payment_status;
        this.order_status = order_status;
        this.user_id = user_id;
        this.address_id = address_id;
        this.cart_id = cart_id;
        this.isCOD = isCOD;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getIsCOD() {
        return isCOD;
    }

    public void setIsCOD(int isCOD) {
        this.isCOD = isCOD;
    }
}
