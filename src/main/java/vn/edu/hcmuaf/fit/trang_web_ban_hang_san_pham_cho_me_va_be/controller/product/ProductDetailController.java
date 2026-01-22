package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.ProductDTO;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ImageService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetailController", value = "/product-detail")
public class ProductDetailController extends HttpServlet {

    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));

            ProductDTO productDTO = productService.editProductById(productId);

            if (productDTO == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại");
                return;
            }

            List<String> subImages = imageService.getAllImagesByProductId(productId);

            String rawDesc = productDTO.getDescription();
            List<String> descriptions = (rawDesc != null) ? List.of(rawDesc.split("\\n")) : List.of();

            productService.increaseNoOfViews(productId);

            request.setAttribute("product", productDTO);
            request.setAttribute("images", subImages);
            request.setAttribute("descriptions", descriptions);

            request.getRequestDispatcher("product_detail/ProductDetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
        }
    }
}