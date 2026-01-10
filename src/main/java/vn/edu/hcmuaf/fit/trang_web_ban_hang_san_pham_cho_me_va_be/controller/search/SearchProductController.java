package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.search;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ProductService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.util.ResponseWrapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home/products/search")
public class SearchProductController extends HttpServlet{
    private final ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write(new ResponseWrapper<>(400, "error", "Keyword must not be empty", null).toJson());
            return;
        }

        List<Product> products = productService.searchProducts(name);

        if (products.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write(new ResponseWrapper<>(404, "error", "No products found", null).toJson());
        } else {
            // Rút gọn thông tin trả về: chỉ tên, giá và hình ảnh
            List<Product> simplifiedProducts = products.stream()
                    .map(product -> new Product(
                            product.getId(),
                            product.getName(),
                            null, // Không cần sku
                            null, // Không cần description
                            null, // Không cần isActive
                            null, // Không cần categoryId
                            null, // Không cần brandId
                            null, // Không cần noOfViews
                            null, // Không cần noOfSold
                            product.getImageId(),
                            product.getPrice(),
                            product.getStock(),
                            product.getOptionId(),
                            null,// Không cần categoryName
                            product.getImageUrl()

                    ))
                    .collect(Collectors.toList());

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write(new ResponseWrapper<>(200, "success", "Products found", simplifiedProducts).toJson());
        }
    }
}
