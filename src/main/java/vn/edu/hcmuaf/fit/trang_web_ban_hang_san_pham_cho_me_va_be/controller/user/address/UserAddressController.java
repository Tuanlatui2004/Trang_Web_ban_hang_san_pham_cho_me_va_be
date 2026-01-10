package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.user.address;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AddressService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserAddressController", value = "/user-address")
public class UserAddressController extends HttpServlet {
    UserService userService = new UserService(DBConnection.getJdbi());
    AddressService addressSevice = new AddressService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            User user = userService.getUserById(userId);
            List<Address> addresses = addressSevice.findByUserId(userId);

            request.setAttribute("user", user);
            request.setAttribute("addresses", addresses);
        }

        request.getRequestDispatcher("user/user-address.jsp").forward(request, response);
    }
}
