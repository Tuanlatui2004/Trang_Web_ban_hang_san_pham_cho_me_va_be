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
    UserService userService = new UserService(DBConnection.getJdbi());

    int codAmount;
    StringBuilder content = new StringBuilder();
    //chưa xử lí cái món này
//    List<GHNItem> items = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        HttpSession session = request.getSession();
        Integer user_id = (Integer) session.getAttribute("user_id");
        List<ProductCart> product_list = new ArrayList<>();

        List<Address> address_list = new ArrayList<>();
        List<Card> card_list = new ArrayList<>();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }


        String productParam = request.getParameter("product_ids");

        if ( productParam != null) {

            String[] arrProducts = productParam.split(",");
            for (String product : arrProducts) {
                if (cart.getData().containsKey(Integer.parseInt(product))) {
                    product_list.add(cart.getData().get(Integer.parseInt(product)));
                }
            }

        }

        address_list = addressService.findByUserId(user_id);
        if (address_list == null || address_list.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/user-address?requireAddress=true");
            return;
        }
        card_list = cardService.getCartByUserId(user_id);

        request.setAttribute("productList", product_list);
        request.setAttribute("addressList", address_list);
        request.setAttribute("cardList", card_list);

        request.getRequestDispatcher("Checkout/Checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");


        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        Boolean flag = false;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        //còn xử lí json chưa thêm, chưa xong phương thức. đã thêm pom.xml
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        String address_id = jsonObject.getString("address_id");
        String card = jsonObject.getString("card");
        int shipping_fee = jsonObject.getInt("ship_fee");
        codAmount   =shipping_fee;
        JSONArray products = jsonObject.getJSONArray("products");

        // User
        Integer user_id = (Integer) session.getAttribute("user_id");
        User user = userService.getUserById(user_id);
        if (user == null) throw new RuntimeException("User not found");

        // Address
        Address address = addressService.findById(Integer.parseInt(address_id));
        if ((address == null) || !(address.getUser_id().equals(user_id)))
            throw new RuntimeException("Address not found");



        // Order
        Order order = new Order();
        order.setCreate_at(LocalDate.now());
        order.setOrder_status(PENDING);
        order.setShipping_fee(shipping_fee);
        order.setUser_id(user_id);
        order.setAddress_id(Integer.parseInt(address_id));
        try {
            if (card.equals("COD")) {
                order.setCOD(true);
                order.setPayment_status(PaymentStatus.PENDING);
            }else{
                order.setCard_id(Integer.parseInt(card));
                order.setCOD(false);
                order.setPayment_status(PaymentStatus.PAID);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        // Create order
        Integer order_id = orderService.addOrder(order);

        if (order_id != null) {
            order.setId(order_id);
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);

                int product_id = product.getInt("id");
                int option_id = product.getInt("optionId");
                Product p = productService.getProductByIdAndOptionId(product_id, option_id);

                if (p == null) throw new RuntimeException("Product not found");
                if (p.getStock() <=0) throw new RuntimeException("Product sold out");

                int quantity = product.getInt("quantity");
                int total = product.getInt("total");


                // Prepare data
                codAmount+=total;
                content.append(p.getName()).append("\n");
//                chưa thêm chưa có GHN
//                GHNItem item = new GHNItem(p, quantity);
//                items.add(item);


                OrderDetail od= new OrderDetail();
                od.setOrder_id(order_id);
                od.setProduct_id(product_id);
                od.setQuantity(quantity);
                od.setTotal(total);
                od.setOption_id(option_id);

                flag= orderDetailService.addOrderDetail(od);
                if (flag && cart != null) {
                    cart.delete(product_id);
                    productService.increaseNoOfSold(product_id, quantity);
                }


            }
        }


        // Call GHN API
//        if (codAmount > 50000000) throw new RuntimeException("Cod amount exceeds 50000000");
        if (codAmount > 50000000) {
            codAmount = 49999999;
        }
//        đoạn dưới chưa thêm GHN nên đang lỗi, chưa fix
//        GHNCreateOrderRequest GHNRequest= new GHNCreateOrderRequest(
//                address, user, "Ten san pham", card.equals("COD") ? codAmount: 0, items);
//
//        String GHNResponse = GHNCreateOrder(GHNRequest);
//        ObjectMapper mapper = new ObjectMapper();
//        APIResponse<CreateOrderResponse> apiResponse = mapper.readValue(GHNResponse,  new TypeReference<APIResponse<CreateOrderResponse>>() {});
//
//        if (apiResponse.getCode() == 200) {orderService.updateShippingId(order.getId(), apiResponse.getData().getOrder_code());
//        }
//
//        if (apiResponse.getCode() != 200) {
//            orderService.updateStatus(order.getId(), OrderStatus.ORDER_CREATE_ERROR);
//        }




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

//    lỗi lằm lỗi lốn, chưa fix vì chưa có GHN
//    private String GHNCreateOrder( GHNCreateOrderRequest GHNCreateOrderRequest ) throws IOException {
//        GHNApiCaller apiCaller = new GHNApiCaller();
//        Gson gson = new Gson();
//        String json = gson.toJson(GHNCreateOrderRequest);
//        String response =  apiCaller.createOrder(json);
//
//        return response;
//    }
}
