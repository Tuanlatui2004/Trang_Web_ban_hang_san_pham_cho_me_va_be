package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/reset-password")
public class ResetPasswordController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = (String) request.getSession().getAttribute("userEmail");

        if (newPassword.equals(confirmPassword)) {
            User user = authService.getUserByEmail(email);
            if (user != null) {
                try {
                    authService.changePassword(user.getId(),null, newPassword, false);
// đổi chỗ này sao HẢI ANH
                    response.sendRedirect("/backend_war/login");
                } catch (IllegalArgumentException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("errorMessage", "Mật khẩu không khớp");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }
}
