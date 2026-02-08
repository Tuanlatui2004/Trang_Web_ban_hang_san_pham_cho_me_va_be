package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.CartController;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart.Cart;

import java.io.IOException;

@WebServlet(name = "Remove", value = "/cart/remove")
public class Remove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Bước 1: Lấy session
        HttpSession session = request.getSession();

        // Bước 2: Lấy Cart từ session
        Cart cart = (Cart) session.getAttribute("cart");

        // Bước 3: Kiểm tra cart tồn tại
        if (cart != null) {
            // Bước 4: Lấy optionId từ request
            Integer optionId = Integer.parseInt(request.getParameter("optionId"));

            // Bước 5: Xóa sản phẩm khỏi giỏ hàng
            if (cart.getData().containsKey(optionId)) {
                cart.getData().remove(optionId);
            }
        }
    }
}