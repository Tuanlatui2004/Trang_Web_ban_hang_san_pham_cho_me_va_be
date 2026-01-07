package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Order;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OrderDetail;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.OrderDetailService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.OrderService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "AdminOrderDetailController", value = "/admin/order-detail")
public class AdminOrderDetailController extends HttpServlet {
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    OrderService orderSerivce = new OrderService(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Integer orderId = Integer.parseInt(request.getParameter("order_id"));
        Order order = orderSerivce.getOrderById(orderId);



        if (order.getId() != null) {
            request.setAttribute("order", order);
// xem orderDetails orderDetailsorderDetails
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order.getId());
            request.setAttribute("orderDetails", orderDetails);

            User user = userService.getUserById(order.getUser_id());
            request.setAttribute("user", user);


        }

        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
