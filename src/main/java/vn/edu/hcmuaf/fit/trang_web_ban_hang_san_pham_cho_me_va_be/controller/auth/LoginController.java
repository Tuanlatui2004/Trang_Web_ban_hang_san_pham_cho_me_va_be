package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AuthService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends  HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> json =
                    objectMapper.readValue(request.getReader(), Map.class);

            String email = json.get("email");
            String password = json.get("password");

            if (email == null || password == null || email.isBlank() || password.isBlank()) {
                response.getWriter().write(
                        objectMapper.writeValueAsString(
                                new ResponseWrapper<>(400, "error",
                                        "Email và mật khẩu không được để trống", null)
                        ));
                return;
            }

            User user = authService.login(email, password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user_id", user.getId());
                session.setAttribute("role", user.getRole());
                session.setMaxInactiveInterval(30 * 60);

                Map<String, Object> userData = Map.of(
                        "id", user.getId(),
                        "fullName", user.getFullName(),
                        "displayName", user.getDisplayName(),
                        "email", user.getEmail(),
                        "role", user.getRole()
                );

                response.getWriter().write(
                        objectMapper.writeValueAsString(
                                new ResponseWrapper<>(200, "success",
                                        "Đăng nhập thành công", userData)
                        ));
            } else {
                response.getWriter().write(
                        objectMapper.writeValueAsString(
                                new ResponseWrapper<>(401, "error",
                                        "Email hoặc mật khẩu không chính xác", null)
                        ));
            }

        } catch (Exception e) {
            response.getWriter().write(
                    objectMapper.writeValueAsString(
                            new ResponseWrapper<>(500, "error",
                                    "Lỗi hệ thống", null)
                    ));
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/auth.jsp").forward(request, response);
    }
}
