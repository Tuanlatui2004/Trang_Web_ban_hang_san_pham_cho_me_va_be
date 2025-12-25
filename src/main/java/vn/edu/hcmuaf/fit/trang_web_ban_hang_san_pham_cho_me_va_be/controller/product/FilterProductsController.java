package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "filterProduct", value = "/product/filter")

public class FilterProductsController extends HttpServlet {

    ProductService productService = new ProductService(DBConnection.getJdbi());

    private static final Logger log = LoggerFactory.getLogger(FilterProductsController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String , Object> jsonMap = mapper.readValue(request.getReader(), Map.class);

            int category_id = Integer.parseInt(jsonMap.get("category_id").toString());

            List<Integer> options = ((List<?>) jsonMap.getOrDefault("option_variant_id", new ArrayList<>()))
                    .stream()
                    .map(obj -> Integer.parseInt(obj.toString()))
                    .collect(Collectors.toList());

            Integer maxPrice = (Integer) jsonMap.get("maxPrice");
            Integer minPrice = (Integer) jsonMap.get("minPrice");

            log.info("category_id: " + category_id);
            log.info("option_variant_id: " + options);
            log.info("maxPrice: " + maxPrice);
            log.info("minPrice: " + minPrice);

            List<Product> products = new ArrayList<>();
            if (options.isEmpty()) {
                products  = productService.filterProductsByPrice(category_id,  minPrice, maxPrice);

            }
            else {
                products = productService.filterProduct(category_id, options,  minPrice, maxPrice);
            }
            log.info("Products List: " + products);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(products));

        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}