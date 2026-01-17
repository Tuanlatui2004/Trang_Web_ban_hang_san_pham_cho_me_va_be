package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import  vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AuthService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.OtpService;

import java.io.IOException;

@WebServlet("/auth/forgot-password")
public class ForgotPasswordController extends HttpServlet{
    private final AuthService authService = new AuthService(DBConnection.getJdbi());
    private final OtpService otpService = new OtpService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Kiểm tra email có tồn tại trong hệ thống hay không
        User user = authService.getUserByEmail(email);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Email không tồn tại trong hệ thống");
            return;
        }

        try {
            // Tạo và gửi OTP
            boolean success = otpService.generateAndSendOTP(email);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("OTP đã được gửi đến email của bạn");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Đã xảy ra lỗi khi gửi OTP");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Đã xảy ra lỗi khi gửi OTP");
        }
    }
}
