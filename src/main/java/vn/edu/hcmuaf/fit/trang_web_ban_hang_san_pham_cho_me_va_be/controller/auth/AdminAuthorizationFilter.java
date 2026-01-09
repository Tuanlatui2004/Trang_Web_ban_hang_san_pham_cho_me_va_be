package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter("/admin/*")
public class AdminAuthorizationFilter implements Filter{
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        // Kiểm tra xem userId có tồn tại trong session hay không
        if (userId == null) {
            redirectToLoginWithMessage(request, response, "Bạn chưa đăng nhập.");
            return;
        }

        // Lấy thông tin người dùng từ cơ sở dữ liệu dựa trên userId từ session
        User user = authService.getUserById(userId);

        if (user == null || !"ADMIN".equalsIgnoreCase(role)) {
            session.invalidate();
            redirectToLoginWithMessage(request, response, "Bạn không có quyền truy cập vào trang này.");
            return;
        }

        chain.doFilter(req, res);
    }

    private void redirectToLoginWithMessage(HttpServletRequest request, HttpServletResponse response, String message)
            throws IOException {
        // Đưa ra thông báo và redirect về trang login
        request.getSession().setAttribute("errorMessage", message);
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {}
}
