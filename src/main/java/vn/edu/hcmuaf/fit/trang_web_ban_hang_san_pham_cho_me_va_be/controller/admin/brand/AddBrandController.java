package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin.brand;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.cloudinary.json.JSONObject;

import java.io.IOException;
@WebServlet(name = "AddBrandController", value = "/admin/add-brand")
public class AddBrandController extends HttpServlet {
    BrandService brandService = new BrandService(DBConnection.getJdbi());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonString.append(line);
        }

        JSONObject jsonRequest = new JSONObject(jsonString.toString());
//   String brand_name = jsonRequest.getString("brand"); hay name
        String brand_name = jsonRequest.getString("brand");
        boolean is_active = jsonRequest.getBoolean("is_active");

        brandService.createBrand(brand_name, is_active);

        // Phản hồi cho client
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\", \"message\": \"Brand added successfully\"}");
    }
}
