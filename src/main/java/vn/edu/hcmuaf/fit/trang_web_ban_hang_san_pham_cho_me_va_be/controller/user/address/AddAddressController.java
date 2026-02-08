package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.user.address;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AddressService;

import java.io.IOException;

@WebServlet(name = "AddAddressController", value = "/AddAddressController")
public class AddAddressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get userId from session instead of request parameter
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            System.out.println("[AddAddress] userId from session: " + userId);

            if (userId == null) {
                System.err.println("[AddAddress] ERROR: User not logged in");
                response.sendRedirect("login");
                return;
            }

            String addressType = request.getParameter("addressType");
            String fullName = request.getParameter("fullName");
            String phoneNumber = request.getParameter("phoneNumber");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            Boolean isDefault = Boolean.valueOf(request.getParameter("isDefault"));

            // Default addressType if not provided
            if (addressType == null || addressType.isEmpty()) {
                addressType = "shipping";
            }

            System.out.println("[AddAddress] Creating address for user: " + userId);
            System.out.println("[AddAddress] FullName: " + fullName + ", Phone: " + phoneNumber);

            // Khởi tạo Address
            Address newAddress = new Address(
                    null, userId, addressType, fullName, phoneNumber, street, city, state, country, isDefault);

            // Thêm vào cơ sở dữ liệu
            AddressService addressService = new AddressService(DBConnection.getJdbi());
            int resultId = addressService.addAddress(newAddress);

            System.out.println("[AddAddress] Result ID: " + resultId);

            if (resultId > 0) {
                System.out.println("[AddAddress] SUCCESS: Address added with ID: " + resultId);
                response.sendRedirect("user-address"); // Điều hướng về trang danh sách địa chỉ
            } else {
                System.err.println("[AddAddress] ERROR: Failed to add address");
                response.getWriter().println("Thêm địa chỉ thất bại.");
            }
        } catch (Exception e) {
            System.err.println("[AddAddress] EXCEPTION: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().println("Lỗi khi thêm địa chỉ: " + e.getMessage());
        }
    }
}
