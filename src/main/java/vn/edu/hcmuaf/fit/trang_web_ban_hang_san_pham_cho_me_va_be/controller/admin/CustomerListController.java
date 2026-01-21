package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.AddressDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerListController", value = "/admin/customers")
public class CustomerListController  extends HttpServlet {
    private UserService userService;
    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService(DBConnection.getJdbi());
        this.addressDao = DBConnection.getJdbi().onDemand(AddressDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        Map<Integer, String> userAddresses = new HashMap<>();

        for (User user : users) {
            Address address = addressDao.getAddressByUserId(user.getId()).stream().findFirst().orElse(null);
            userAddresses.put(user.getId(), address != null ? address.getCountry() : "N/A");

            if (user.getAvatarId() != null) {
                String avatarUrl = userService.getAvatarUrlById(user.getAvatarId());
                user.setAvatarUrl(avatarUrl); // Set avatar URL to User object
            } else {
                user.setAvatarUrl("default-avatar-url.jpg"); // Default avatar URL
            }
        }
// xem db có biến này không anh em
        request.setAttribute("users", users);
            request.setAttribute("userAddresses", userAddresses);


        request.getRequestDispatcher("customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
