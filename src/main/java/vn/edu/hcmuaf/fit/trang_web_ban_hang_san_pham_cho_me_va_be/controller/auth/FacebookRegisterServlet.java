package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.auth;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.config.ConfigLoader;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.config.EnvConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/register-facebook")
public class FacebookRegisterServlet  extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private final String FACEBOOK_APP_ID = EnvConfig.get("FACEBOOK_APP_ID");
    //    private final String REDIRECT_URI = "http://localhost:8080/backend_war/facebook-callback";
    private String redirectUri;

    @Override
    public void init() throws ServletException {
        String hostProduct = ConfigLoader.get("host.dev");
        this.redirectUri = hostProduct + "/Trang_Web_ban_hang_san_pham_cho_me_va_be_war_exploded/facebook-callback";
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String state = UUID.randomUUID().toString();

        HttpSession session = request.getSession(true);
        session.setAttribute("facebook_state", state);
        session.setAttribute("fbAuthMode", "register");

        // Debug logging
        System.out.println("Register - Setting session state: " + state);
        System.out.println("Register - Session ID: " + session.getId());
        // deloy lấy http bỏ vô NV ghi
        String authUrl = "https://www.facebook.com/v24.0/dialog/oauth" +
                "?client_id=" + FACEBOOK_APP_ID +
                "&redirect_uri=" + redirectUri +
                "&state=" + state +
                "&scope=email,public_profile";

        // Thêm cache headers
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        response.sendRedirect(authUrl);
    }
}
