package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.user.order;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.*;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserOrderDetailController", value = "/user-order-detail")
public class UserOrderDetailController extends HttpServlet {
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    OrderService orderService = new OrderService(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer orderId = Integer.parseInt(request.getParameter("orde_id"));
        HttpSession session = request.getSession();
        Integer userId = Integer.parseInt(session.getAttribute("userI_id").toString());

        User user = userService.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
        }

        Order order = orderService.getOrderByIdAndUserId(orderId,userId);
        if (order != null) {
            request.setAttribute("order", order);

            if (!order.getCOD()){
                Card card = cardService.getCardById(order.getCard_id());
                if (card != null) {
                    request.setAttribute("card", card);
                }
            }


            Address address = addressService.findById(order.getAddress_id());
            if (address != null) {
                request.setAttribute("address", address);
            }

            request.setAttribute("COD", order.getCOD());
        }

        if (order.getId() != null) {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(orderId);
            request.setAttribute("orderDetails", orderDetails);
        }





        request.getRequestDispatcher("user/user-order-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
