package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/confirm")
public class ConfirmAccountController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getParameter("session_id");

        if (sessionId != null) {
            if (authService.verifySession(request, sessionId)) {
                authService.activateUserAccount(request, sessionId);

                request.setAttribute("message", "Tài khoản của bạn đã được xác nhận. Vui lòng đăng nhập.");
                request.getRequestDispatcher("/auth/auth.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Mã xác nhận không hợp lệ hoặc đã hết hạn.");
            }
        } else {
            request.setAttribute("error", "Mã xác nhận không hợp lệ.");
        }
    }
}
