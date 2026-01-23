package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
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
public class ForgotPasswordController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }

    // Xử lý quên mật khẩu
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        // Kiểm tra email có tồn tại trong hệ thống hay không
        User user = authService.getUserByEmail(email);
        if (user == null) {
            request.setAttribute("errorMessage", "Email không tồn tại");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        } else {
            String otp = generateOTP();
            sendEmailWithOTP(user.getEmail(), otp);

            // Lưu OTP vào session để xác minh sau
            request.getSession().setAttribute("otp", otp);
            request.getSession().setAttribute("userEmail", user.getEmail());

            // Chuyển sang trang nhập OTP
            response.sendRedirect("forgotpassword.jsp");
        }
    }

    private String generateOTP() {
        EmailService emailService = new EmailService();
        return emailService.generateOTP();
    }

    private void sendEmailWithOTP(String email, String otp) {
        EmailService emailService = new EmailService();
        try {
            emailService.sendEmailWithOTP(email, otp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
