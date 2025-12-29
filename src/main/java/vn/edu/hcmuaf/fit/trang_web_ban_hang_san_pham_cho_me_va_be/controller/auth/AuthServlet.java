package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.config.EnvConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    String client_id = EnvConfig.get("GOOGLE_CLIENT_ID");
    String clientSecret = EnvConfig.get("GOOGLE_CLIENT_SECRET");

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            if (input == null) {
                throw new ServletException("Unable to find application.properties");
            }
            prop.load(input);
            client_id = EnvConfig.get("GOOGLE_CLIENT_ID");;
            if (client_id == null) {
                throw new ServletException("Google Client ID not found in application.properties");
            }
            System.out.println("Google Client ID loaded: " + client_id); // Debug log
        } catch (IOException e) {
            throw new ServletException("Error loading Google OAuth credentials", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("googleClientId", client_id);
        System.out.println("Setting Google Client ID in request: " + client_id); // Debug log
        request.getRequestDispatcher("/auth/auth.jsp").forward(request, response);
    }
}
