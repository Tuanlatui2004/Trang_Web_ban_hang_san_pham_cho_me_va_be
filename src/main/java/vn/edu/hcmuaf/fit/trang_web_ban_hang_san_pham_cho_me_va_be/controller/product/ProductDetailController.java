package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.product;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OptionVariant;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
//import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ImageService;
//import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.OptionService;
//import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet(name = "ProductDetailController",value = "/product_detail")
public class ProductDetailController extends HttpServlet {
    // chưa có service
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(product_id);

        Integer product_price = productService.getMinimumPriceForProduct(product_id); // Default to minimum price
        if (product.getOption_id() != null) {
            product_price = productService.getPriceForOption(product.getOption_id());
        }



        List<String> images = imageService.getAllImagesByProductId(product.getId());
        String image_id = imageService.getImageUrlById(product.getImage_id());
        List<String> descriptions = List.of(product.getDescription().split("\\n"));


        List<OptionVariant> options = optionService.getOptionsByProductId(product.getId());
        List<Integer> optionIds = options.stream().map(OptionVariant::getId).collect(Collectors.toList());

        List<OptionVariant> optionVariant = optionService.getVariantByOptionId(optionIds);
        List<String> varaints = optionVariant.stream().map(OptionVariant::).distinct().collect(Collectors.toList());



        request.setAttribute("images", images);
        request.setAttribute("primaryImageUrl", primaryImageUrl); // Add primary image URL
        request.setAttribute("product", product);
        request.setAttribute("descriptions", descriptions);
        request.setAttribute("product_price", product_price);
        request.setAttribute("optionVariant", optionVariant);
        request.setAttribute("varaints", varaints);


        productService.increaseNoOfViews(product_id);

        request.getRequestDispatcher("product_detail/ProductDetail.jsp").forward(request, response);
    }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

    }
}
