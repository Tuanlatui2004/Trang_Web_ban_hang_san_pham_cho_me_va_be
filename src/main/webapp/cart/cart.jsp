<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-cart/Cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-cart/CartItem.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/style-component/style-cart/Cart.js"></script>
</head>

<body>
<div id="wrap_cart">

    <!-- HEADER -->
    <jsp:include page="/home/header.jsp"/>

    <!-- BODY -->
    <div id="cart_body" class="mid_align col">

        <!-- ================= EMPTY CART ================= -->
        <c:if test="${empty productCarts}">
            <div id="empty_cart" class="mid_align col">
                <i class="fa-solid fa-cart-plus"></i>
                <h2>Giỏ hàng đang trống</h2>
                <span>Hãy tiếp tục mua sắm hoặc đăng nhập để xem giỏ hàng</span>

                <div class="btn row">
                    <button class="btn_shopping">Tiếp tục mua sắm</button>
                    <button class="btn_login">Đăng nhập</button>
                </div>
            </div>
        </c:if>

        <!-- ================= CART HAS ITEMS ================= -->
        <c:if test="${not empty productCarts}">

            <div class="content row">

                <!-- ===== LEFT: LIST ITEM ===== -->
                <div class="list_item">

                    <!-- CHECK ALL -->
                    <div class="select_all row mid_align">
                        <input type="checkbox" id="check_all" checked>
                        <label for="check_all">Chọn tất cả</label>
                    </div>

                    <!-- ITEMS -->
                    <c:forEach items="${productCarts}" var="item">
                        <jsp:include page="/cart/cart_item.jsp"/>
                    </c:forEach>

                </div>

                <!-- ===== RIGHT: BILL ===== -->
                <div class="bill mid_align col">

                    <!-- DISCOUNT -->
                    <div class="discount">
                        <span class="title">Nhập mã khuyến mãi</span>
                        <div class="wrap_input">
                            <input type="text" placeholder="Voucher hoặc gift code">
                            <button type="button">Áp dụng</button>
                        </div>
                    </div>

                    <!-- SUMMARY -->
                    <div class="summary col">
                        <span class="title">Tóm tắt đơn hàng</span>

                        <div class="item_price">
                            <span>Tổng trước thuế</span>
                            <span id="before_tax">0 VND</span>
                        </div>

                        <div class="item_price">
                            <span>Thuế GTGT (10%)</span>
                            <span id="VAT">0 VND</span>
                        </div>
                    </div>

                    <!-- TOTAL -->
                    <div class="wrap_total">
                        <div class="total_label">
                            <span>Tổng cộng</span>
                            <span id="total">0 VND</span>
                        </div>
                        <span class="note">Đã bao gồm thuế GTGT</span>
                    </div>

                    <!-- PAY -->
                    <button id="pay">Tiếp tục</button>

                    <!-- TERMS -->
                    <div class="term_condition">
                        <span>
                            Bằng cách đặt hàng, bạn đồng ý với
                            <a href="#">Điều khoản & điều kiện</a> và
                            <a href="#">Chính sách quyền riêng tư</a>.
                        </span>
                    </div>

                </div>
            </div>
        </c:if>

    </div>
</div>
</body>
</html>
