package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.CheckOut;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.PaymentStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.*;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart.Cart;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart.ProductCart;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus.PENDING;

@WebServlet(name = "Checkout", value = "/checkout")
public class CheckOutController extends HttpServlet {
    OrderService orderService = new OrderService(DBConnection.getJdbi());
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());
    ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        List<ProductCart > productList = new ArrayList<>();

        List<Address> addressList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }


        String productParam = request.getParameter("productIds");

        if ( productParam != null) {

            String[] arrProducts = productParam.split(",");
            for (String product : arrProducts) {
                if (cart.getData().containsKey(Integer.parseInt(product))) {
                    productList.add(cart.getData().get(Integer.parseInt(product)));
                }
            }

        }




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


        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        Boolean flag = false;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);

        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        String address = jsonObject.getString("address_id");
        String card = jsonObject.getString("card");
        JSONArray products = jsonObject.getJSONArray("products");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        Order order = new Order();
        order.setCreate_at(LocalDate.now());
        // xem lại khúc này biến tạm
        order.setPayment_status(PaymentStatus.valueOf("PAID"));
        order.setOrder_status(OrderStatus.valueOf("DELIVERED"));
        order.setUser_id(userId);
        try {
            order.setAddress_id(Integer.parseInt(address));
            if (card.equals("COD")) {
                order.setCOD(true);
            }
            else{
                order.setCard_id(Integer.parseInt(card));
                order.setCOD(false);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        Integer orderid = orderService.addOrder(order);

        if (orderid != null) {
            Integer oderid  = orderid;
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);

                int productId = product.getInt("id");
                int quantity = product.getInt("quantity");
                int total = product.getInt("total");
                int optionId = product.getInt("option_id");

                OrderDetail od= new OrderDetail();
                od.setOrder_id(oderid);
                od.setProduct_id(productId);
                od.setQuantity(quantity);
                od.setTotal(total);
                od.setOption_id(optionId);

                flag = orderDetailService.addOrderDetail(od);
                if (flag){
                    productService.increaseNoOfSold(productId, quantity);
                }


            }
        }

        if (flag){


            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", true);
            response.getWriter().write(jsonResponse.toString());
        }else {


            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", false);

            response.getWriter().write(jsonResponse.toString());
        }


    }
}
