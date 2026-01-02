package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.user.address;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.AddressService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "AddAddressController", value = "/AddAddressController")
public class AddAddressController extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = Integer.parseInt(request.getParameter("user_id"));
            String addressType = request.getParameter("address_type");
            String fullName = request.getParameter("full_name");
            String phoneNumber = request.getParameter("phone_number");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            // xem lại này nha HaiAnh
            Boolean is_default = Boolean.valueOf(request.getParameter("is_default"));

            // Khởi tạo Address
            Address newAddress = new Address(
                    null, userId, addressType, fullName, phoneNumber, street, city, state, country, is_default
            );

            // Thêm vào cơ sở dữ liệu
            AddressService addressService = new AddressService(DBConnection.getJdbi());
            int resultId = addressService.addAddress(newAddress);

            if (resultId > 0) {
                response.sendRedirect("user-address"); // Điều hướng về trang danh sách địa chỉ
            } else {
                response.getWriter().println("Thêm địa chỉ thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi khi thêm địa chỉ.");
        }
    }
}
