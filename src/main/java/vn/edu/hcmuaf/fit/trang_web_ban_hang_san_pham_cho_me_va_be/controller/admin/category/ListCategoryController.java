package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin.category;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.CategoriesWithStock;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.CategoryCustomService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "ListCategoryController", value = "/admin/category")
public class ListCategoryController extends HttpServlet{
    CategoryCustomService categoryCustomService =  new CategoryCustomService(DBConnection.getJdbi()); ;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CategoriesWithStock> categoriesWithStock = categoryCustomService.getCustomCategoriesWithTotalStock();
            request.setAttribute("categoriesWithStock", categoriesWithStock);
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh mục.");
        }
    }
}
