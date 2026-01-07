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
    OrderService orderSerivce = new OrderService(DBConnection.getJdbi());
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Product product = productService.getProductByIdAndOptionId(Integer.parseInt(request.getParameter("product_id")),
                Integer.parseInt(request.getParameter("option_id")));


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (product == null) {
            throw new ServletException("Product not found");
        }
        ProductCart productCart = new ProductCart(product);

        List<ProductCart> productList = new ArrayList<>();
        productList.add(productCart);

        List<Address> addressList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();


        addressList = addressService.findByUserId(userId);
        cardList = cardService.getCartByUserId(userId);





        request.setAttribute("productList", productList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("cardList", cardList);



        request.getRequestDispatcher("checkout/checkout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
