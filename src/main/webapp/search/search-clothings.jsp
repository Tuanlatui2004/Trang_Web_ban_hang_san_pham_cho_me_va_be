<%--
  Created by IntelliJ IDEA.
  User: fileh
  Date: 1/1/2026
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>List Product</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/TopProduct.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.css">

</head>
<body>
<div class="container">
    <div class="search_header">
        <jsp:include page="../home/header.jsp"/>
    </div>

    <div id="banner">
        <%--    <iframe src = "../home/banner.html"></iframe>--%>
        <jsp:include page="../home/banner.jsp"/>
    </div>
    <div id="body" class="row">
        <div id="sidebar">

                <div class="title f18 ">
                    <i class="fa-solid fa-filter"></i>
                    Bộ lọc
                </div>

                <button id="apply_btn"> Áp dụng</button>

            <!---------------------------- Default ------------------------------------------------>
<%--            kiểm tra xem cái này chưa có xử lí chức năng--%>
<%--            <div class="section_price section_item col">--%>
            <div class="section_type section_item col">

                <div class="title">Mức giá</div>


                <div class="item mid_align">
                    <input type="radio" name="price" id="price1" data-max="300000" >
                    <label for="price1">Dưới 3 trăm</label>
                </div>


                <div class="item mid_align">
                    <input type="radio" name="price" id="price2" data-max="500000" data-min="300000">
                    <label for="price2">Từ 3-5 trăm</label>
                </div>

                <div class="item">
                    <input type="radio" name="price" id="price3"  data-min="500000" data-max="700000">
                    <label for="price3">Từ 5-7 trăm</label>
                </div>

                <div class="item">
                    <input type="radio" name="price"  id="price4"  data-min="700000" data-max="900000">
                    <label for="price3">Từ 7-9 trăm</label>
                </div>

                <div class="item">
                    <input type="radio" name="price" id="price5"  data-min="1000000">
                    <label for="price5">Trên 1 triệu</label>
                </div>


            </div>

            <div class="rec_horizontal"></div>
        </div>




        <div id="list_product">


            <div id="top_product" class="col mid_align">
                <span>Top Sản Phẩm Bán Chạy </span>

                <div class="wrap_item row mid_align">
                    <c:if test="${not empty topProducts}">
                        <c:forEach var="pro" items="${topProducts}">


                            <div id="search_body">

                                <div class="wrap_img">
                                    <img id="image" src="${pro.imageUrl}"
                                         alt="" height="225"
                                         width="225"/>
                                </div>

                                <div class="infor mid_align col ">
                                    <div id="top_name" class="bold f16">
                                        <a href="product-detail?id=${pro.id}"> ${pro.name}</a>
                                    </div>


                                    <div id="price" class="bold f22">
                                            <%--                                        1.900.999 ₫--%>
                                        <fmt:formatNumber value="${pro.price}" pattern="#,###"/> VND

                                        <span id="ratting" class="" style="padding: 0 5px">
                                            5 (153)
                                            <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                                        </span>

                                    </div>

                                    <div id="top_description">
                                        <ul class="list_descriptions">
                                            <li class="desc_item f12">Bộ quần áo dài êm mềm giúp giữ ấm cơ thể bé, đặc biệt là lưng và bụng</li>
                                            <li class="desc_item f12">Áo cổ tròn, phom suông giúp ba mẹ dễ dàng thay ra mặc vào cho bé</li>
                                            <li class="desc_item f12">Hoạ tiết độc đáo, giúp vẻ ngoài của bé thêm đáng yêu và năng động</li>
                                            <li class="desc_item f12">Chất liệu 100% vải cotton mềm mại, an toàn và có khả năng thấm hút mồ hôi vượt trội</li>
                                        </ul>
                                    </div>

                                </div>


                                <div class="operation col">
                                    <a href="buy-now?productId=${pro.id}&optionId=${pro.optionId}" class="btn buy">
                                        Mua ngay
                                    </a>
                                    <button onclick="addToCart(${pro.id},${pro.optionId})" class="btn add">Thêm vào giỏ hàng</button>
                                </div>

                                <div id="top_cart-notification" class="notification hidden">
                                    <i class="fa-solid fa-circle-check"></i>
                                    <span>Thêm vào giỏ hàng thành công</span>
                                </div>


                            </div>

                        </c:forEach>

                    </c:if>

                    <c:if test="${empty topProducts}">
                        <p>Empty product</p>
                    </c:if>

                </div>
            </div>


            <span class="popular_title mid_align">Top Sản Phẩm Nổi Bật </span>

            <div id="product_list">
                <c:if test="${not empty products}">
                    <c:forEach items="${products}" var="p">

                        <div class="product_item col" data-stock="${p.stock}">

                            <div class="wrap mid_align row">

                                <div class="img_section">

                                    <c:if test="${not empty p.imageUrl}">
                                        <img src="${p.imageUrl}" alt=""/>
                                    </c:if>

                                </div>


                                <div class="infor_section">

                                    <div class="infor_name bold f22" id="name">
                                        <a href="product-detail?id=${p.id}"> ${p.name}</a>
                                    </div>
                                        <%--phân biệt màu sắc or khối lượng--%>

                                    <div class="infor_color col">
                                        <span class="bold f16">Size: <span class="normal f16"> Vừa vặn</span></span>

                                        <div class="choose_color row">
                                            <div class="col_item" id="pink"></div>
                                            <div class="col_item" id="gray"></div>
                                            <div class="col_item" id="yellow"></div>
                                        </div>


                                    </div>
<%--                                        phân biệt size quần áo hoặc khối lượng sữa--%>
                                    <div class="rating row mid_align">
                            <span id="noOfRatting" class="bold" style="padding: 0 5px">
                                4.7 (153)
<%--                                <i class="fa-solid fa-star" style="color: #FFD43B;"></i>--%>
                                <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                            </span>
                                    </div>


                                    <div id="description">
                                        <ul class="list_descriptions">
                                            <li class="desc_item f14">Phù hợp với trẻ mọi lứa tuổi</li>
                                            <li class=" desc_item f14">Được các chuyên gia kiểm định đạt chuẩn</li>
                                            <li class="desc_item f14">Là sự lựa chọn của mọi nhà</li>
                                        </ul>
                                    </div>

                                </div>


                                <div class="rec_vertical"></div>


                                <div class="section_right col">
                                    <div class="price">
                                   <span class="bold f22">
                                       <fmt:formatNumber value="${p.price}" pattern="#,###"/> VND
                                   </span>
                                    </div>

                                    <div class="service">
                                        <div class="service_item">
                                            <i class="fa-solid fa-gift"></i>
                                            <span>Luôn sẵn sàng cho khách hàng</span>
                                        </div>

                                        <div class="service_item">
                                            <i class="fa-solid fa-truck"></i>
                                            <span>Miễn Phí Vận Chuyển Toàn Quốc</span>
                                        </div>

                                        <div class="service_item">
                                            <i class="fa-solid fa-box-open"></i>
                                            <span>Đổi trả trong 14 ngày</span>
                                        </div>


                                    </div>


                                    <div class="wrap_btn col">
                                        <a href="buy-now?productId=${p.id}&optionId=${p.optionId}" class="btn buy buy-now-btn">Mua Ngay</a>

                                        <button onclick="addToCart(${p.id},${p.optionId})" class="btn add">
                                            Thêm vào giỏ hàng
                                        </button>

                                    </div>

                                    <div id="cart-notification" class="notification hidden">
                                        <i class="fa-solid fa-circle-check"></i>
                                        <span>Thêm vào giỏ hàng thành công</span>
                                    </div>

                                </div>


                            </div>


                        </div>
                    </c:forEach>
                </c:if>
            </div>



        </div>


    </div>

</div>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/SearchProductItem.js"></script>
<script src="${pageContext.request.contextPath}/static/style-component/style_product/Search_Product.js"></script>
<%-- xem thêm chỗ lọc sản phẩm bằng variant--%>
</body>
</html>

