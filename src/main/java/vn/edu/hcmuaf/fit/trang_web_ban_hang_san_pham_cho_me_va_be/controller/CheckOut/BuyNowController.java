package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.CheckOut;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Card;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart.ProductCart;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BuyNowController", value = "/buy-now")
public class BuyNowController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    OrderService orderService = new OrderService(DBConnection.getJdbi());
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        String optionIdStr = request.getParameter("optionId");

        if (productIdStr == null || optionIdStr == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        Product product = productService.getProductByIdAndOptionId(Integer.parseInt(productIdStr),
                Integer.parseInt(optionIdStr));

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (product == null) {
            throw new ServletException("Product not found");
        }
        ProductCart productCart = new ProductCart(product);

        List<ProductCart> productList = new ArrayList<>();
        productList.add(productCart);

        // Handle null userId (guest user) - use empty lists instead of querying with
        // null
        List<Address> addressList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();
        if (userId != null) {
            addressList = addressService.findByUserId(userId);
            cardList = cardService.getCartByUserId(userId);
        }

        request.setAttribute("productList", productList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("cardList", cardList);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("checkout/checkout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Just delegate to doGet or handle as AJAX if needed
        // If it's AJAX from Search_Product.js
        String productIdStr = request.getParameter("productId");
        String optionIdStr = request.getParameter("optionId");

        if (productIdStr != null && optionIdStr != null) {
            // For AJAX, we might just want to acknowledge it's ready for redirect
            response.setContentType("application/json");
            response.getWriter().write("{\"ok\": true}");
        } else {
            doGet(request, response);
        }
    }
}
