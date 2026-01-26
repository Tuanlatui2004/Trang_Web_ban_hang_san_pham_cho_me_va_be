package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCategoryController", value = "/admin/add-category")
public class AddCategoryController extends  HttpServlet {
    private final CategoryService categoryService = new CategoryService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect or show error if accessed via GET
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        try {
            // Đọc dữ liệu từ request body
            StringBuilder jsonString = new StringBuilder();
            String line;
            try (var reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }
            }

            // Parse dữ liệu JSON
            org.json.JSONObject jsonRequest = new org.json.JSONObject(jsonString.toString());
            String categoryName = jsonRequest.optString("name", "").trim();

            // Kiểm tra dữ liệu
            if (categoryName.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new org.json.JSONObject().put("message", "Tên danh mục không được để trống").toString());
                return;
            }

            // Thêm danh mục sử dụng CategoryService
            Category newCategory = categoryService.createCategory(categoryName, true);

            if (newCategory != null) {
                // Phản hồi thành công
                response.setStatus(HttpServletResponse.SC_OK);
                out.write(new org.json.JSONObject().put("message", "Danh mục được thêm thành công").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write(new org.json.JSONObject().put("message", "Không thể tạo danh mục").toString());
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(new JSONObject().put("message", "Có lỗi xảy ra khi thêm danh mục: " + e.getMessage()).toString());
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
