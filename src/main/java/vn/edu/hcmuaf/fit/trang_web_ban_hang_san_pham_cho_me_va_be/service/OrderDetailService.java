package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.OrderDetailDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OptionVariant;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OrderDetail;

import java.util.List;

public class OrderDetailService {
    Jdbi jdbi;
    OrderDetailDao orderDetailDao;
    OptionService optionService = new OptionService(DBConnection.getJdbi());
    public OrderDetailService(Jdbi jdbi) {
        orderDetailDao = jdbi.onDemand(OrderDetailDao.class);
    }


    public Boolean addOrderDetail(OrderDetail orderDetail) {

        OptionVariant options = optionService.getOptionById(orderDetail.getOption_id());
        if (options == null || (options.getStock() < orderDetail.getQuantity())) {
            throw new RuntimeException("Bad request");
        }
        else {

            Boolean flag = orderDetailDao.addOrderDetail(
                    orderDetail.getOrder_id(),
                    orderDetail.getProduct_id(),
                    orderDetail.getQuantity(),
                    orderDetail.getTotal(),
                    orderDetail.getOption_id()
            );

            if (flag) {
                Integer newStock = options.getStock() - orderDetail.getQuantity() ;
                // Increase Stock Quantity
                optionService.updateStock(orderDetail.getOption_id(), newStock);
            }

            return flag;
        }


    }
    public String getProductNameById(Integer product_id) {
        return orderDetailDao.getProductNameById(product_id);
    }

    public Integer getQuantityByOrderDetailId(Integer orderDetail_id) {
        return orderDetailDao.getQuantityByOrderDetailId(orderDetail_id);
    }

    public String getOrderStatusByOrderId(Integer order_id) {
        return orderDetailDao.getOrderStatusByOrderId(order_id);
    }

    public OrderDetail getOrderDetailsByOrderId(Integer order_id) {
        return orderDetailDao.getOrderDetailById(order_id);
    }



    public List<OrderDetail> getOrderDetailByOrderId(Integer order_id) {
        return orderDetailDao.getOrderDetailByOrderId(order_id);
    }



    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
        System.out.println(orderDetailService.getOrderDetailByOrderId(39));
    }

}
