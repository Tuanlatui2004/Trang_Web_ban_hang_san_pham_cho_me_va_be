package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.PaymentStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import java.time.LocalDate;

public class Order  {
    Integer id;
    LocalDate create_at;
    PaymentStatus payment_status;
    OrderStatus order_status;
    Integer user_id;
    Integer card_id;
    Integer address_id;
    Boolean isCOD;


    // Su dung cho đại diẹn order
    Integer quantity;
    Integer total;
    String product_name;
    String product_image;
    String user_name;

    //    Vận chuyển
    Integer shipping_fee;
    String shipping_id;

    //    Review
    Boolean is_reviewed;


    @JdbiConstructor
    public Order(@ColumnName("id") @Nullable Integer id,
                 @ColumnName("create_at") @Nullable LocalDate create_at,
                 @ColumnName("payment_status") @Nullable PaymentStatus payment_status,
                 @ColumnName("order_status") @Nullable OrderStatus order_status,
                 @ColumnName("user_id") @Nullable Integer user_id,
                 @ColumnName("card_id") @Nullable Integer card_id,
                 @ColumnName("address_id") @Nullable Integer address_id,
                 @ColumnName("isCOD") @Nullable Boolean isCOD,

                 @ColumnName("quantity") @Nullable Integer quantity,
                 @ColumnName("total") @Nullable Integer total,
                 @ColumnName("product_name") @Nullable String product_name,
                 @ColumnName("product_image") @Nullable String product_image,
                 @ColumnName("user_name") @Nullable String user_name,


                 @ColumnName("shipping_fee") @Nullable Integer shipping_fee,
                 @ColumnName("shipping_id") @Nullable String shipping_id,


                 @ColumnName("is_reviewed") @Nullable Boolean is_reviewed

    ){
        this.id = id;
        this.create_at = create_at;
        this.payment_status = payment_status;
        this.order_status = order_status;
        this.user_id = user_id;
        this.card_id = card_id;
        this.address_id = address_id;
        this.isCOD = isCOD;
        this.quantity = quantity;
        this.total = total;
        this.product_name = product_name;
        this.product_image = product_image;
        this.user_name = user_name;
        this.shipping_fee = shipping_fee;
        this.shipping_id = shipping_id;
        this.is_reviewed = false;

    }

    public Order( ) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public OrderStatus getOrder_status() {
        return order_status;
    }

    public void setOrder_status(OrderStatus order_status) {
        this.order_status = order_status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Boolean getCOD() {
        return isCOD;
    }

    public void setCOD(Boolean COD) {
        isCOD = COD;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Integer shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public Boolean getIs_reviewed() {
        return is_reviewed;
    }

    public void setIs_reviewed(Boolean is_reviewed) {
        this.is_reviewed = is_reviewed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", create_at=" + create_at +
                ", payment_status=" + payment_status +
                ", order_status=" + order_status +
                ", user_id=" + user_id +
                ", card_id=" + card_id +
                ", address_id=" + address_id +
                ", isCOD=" + isCOD +
                ", quantity=" + quantity +
                ", total=" + total +
                ", product_name='" + product_name + '\'' +
                ", product_image='" + product_image + '\'' +
                ", user_name='" + user_name + '\'' +
                ", shipping_fee=" + shipping_fee +
                ", shipping_id='" + shipping_id + '\'' +
                ", is_reviewed=" + is_reviewed +
                '}';
    }
}
