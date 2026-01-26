package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.product;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OptionVariant;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ImageService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.OptionService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ProductDetailController", value = "/product-detail")
public class ProductDetailController extends HttpServlet {
    // chưa có service
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(productId);

        if (product != null && product.getDescription() != null) {
            String formattedDescription = product.getDescription()
                    .trim()
                    .replaceAll("\\.\\s*", ".<br/>");
            product.setDescription(formattedDescription);
        }

        Integer productPrice = productService.getMinimumPriceForProduct(productId);
        if (product.getOptionId() != null) {
            productPrice = productService.getPriceForOption(product.getOptionId());
        }

        List<String> images = imageService.getAllImagesByProductId(product.getId());
        String primaryImageUrl = imageService.getImageUrlById(product.getImageId());

        List<OptionVariant> options = optionService.getOptionsByProductId(product.getId());
        List<Integer> optionIds = options.stream()
                .map(OptionVariant::getId)
                .collect(Collectors.toList());

        List<OptionVariant> optionVariant = optionService.getVariantByOptionId(optionIds);
        List<String> variants = optionVariant.stream()
                .map(OptionVariant::getVariantName)
                .distinct()
                .collect(Collectors.toList());

        request.setAttribute("images", images);
        request.setAttribute("primaryImageUrl", primaryImageUrl);
        request.setAttribute("product", product);
        request.setAttribute("productPrice", productPrice);
        request.setAttribute("optionVariant", optionVariant);
        request.setAttribute("variants", variants);

        productService.increaseNoOfViews(productId);

        request.getRequestDispatcher("product_detail/ProductDetail.jsp")
                .forward(request, response);
    }


    // lỗi
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
