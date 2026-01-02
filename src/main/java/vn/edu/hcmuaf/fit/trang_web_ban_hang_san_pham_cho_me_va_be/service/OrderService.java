package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.OrderDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Order;

import java.util.List;

public class OrderService {

    OrderDao orderDao;


    public OrderService(Jdbi jdbi) {
        orderDao = jdbi.onDemand(OrderDao.class);
    }

    public Integer addOrder(Order order) {
        return orderDao.createOrder(
                order.getCreate_at(),
                order.getPayment_status(),
                order.getOrder_status(),
                order.getUser_id(),
                order.getAddress_id(),
                order.getCard_id(),
                order.getCOD()
        );
    }

    public List<Order> getOrdersByUserId(Integer user_id) {
        return orderDao.getOrdersByUserId(user_id);
    }


    public  Order getOrderByIdAndUserId(Integer order_id ,Integer user_id) {
        return orderDao.getOrderByIdAndUserId(order_id, user_id);
    }

    public  Order getOrderById(Integer order_id  ) {
        return orderDao.getOrderById(order_id);
    }



    public List<Order> getAllOrders( ) {
        return orderDao.getAllOrders();
    }


    public static void main(String[] args) {
        OrderService orderSerivce = new OrderService(DBConnection.getJdbi());
//        Order order = new Order();
//        order.setCreateAt(LocalDate.now());
//        order.setPaymentStatus("PAID");
//        order.setOrderStatus("PAID");
//        order.setUserId(1);
//        order.setAddressId(1);
//        order.setCardId(1);
//        order.setIsCOD(false);
//
//
//        Integer orderId = orderSerivce.addOrder(order);
//        System.out.println(orderId);
//        System.out.println(orderSerivce.getOrderByIdAndUserId( 24,42));
//        System.out.println(orderSerivce.getOrdersByUserId(42));
        System.out.println(orderSerivce.getOrdersByUserId(23 ));
    }
}
