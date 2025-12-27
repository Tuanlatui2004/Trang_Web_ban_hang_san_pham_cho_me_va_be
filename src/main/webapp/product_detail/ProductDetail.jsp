<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: vinhp
  Date: 12/24/2025
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Product Detail</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.js"
            defer></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.css">
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/review.css"/>
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/review.js"></script>
</head>
<body>
<div class="cart_header">
    <jsp:include page="/home/header.jsp"/>
</div>
<div class="container">
    <div class="section1">
        <div class="carousel-container">


            <div class="carousel-container">

                <img id="mainImage" src="${image_url}" alt="Carousel Image" class="carousel-image">
                <!-- Navigation Arrows -->
                <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
                <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>

                <!-- Thumbnails -->
                <div class="thumbnails">
                    <div class="thumbnail">
                        <c:if test="${not empty images}">
                            <c:forEach var="image" items="${images}">
                                <img src="${image}" alt="Thumbnail "/>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <%-- product này là gì--%>
    <div class="section1">
        <div id="product" data-id="${product.id}" data-option-default="${product.option_id}"
             class="container-product-Bt">
            <div class="product-title">
                ${product.name}
            </div>

            <%-- product này là gì--%>
            <%--   VARIANT              --%>
            <c:if test="${not empty optionVariant  && not empty variants}">
                <c:forEach items="${variants}" var="type">

                    <div class="wrap_variant ">
                        <div class="option-title">Chọn ${type}:</div>

                        <c:forEach items="${optionVariant}" var="op">
                            <c:if test="${op.variantName eq type  }">
                                <div class="option-item" data-option-id="${op.id}"
                                     data-price="${op.price}"> ${op.variantValue}</div>
                            </c:if>
                        </c:forEach>
                    </div>

                </c:forEach>

            </c:if>


            <div id="price" class="price">
                <c:choose>
                    <c:when test="${not empty product_price}">

                        <fmt:formatNumber value="${product_price}" pattern="#,###"/> VND
                    </c:when>
                    <c:otherwise>
                        Đang câp nhật
                    </c:otherwise>
                </c:choose>
            </div>


            <div class="product-features">
                <ul>

                    <!-- Lặp qua danh sách descriptions -->
                    <c:forEach var="desc" items="${descriptions}">
                        <li>${desc}</li>
                    </c:forEach>
                </ul>
            </div>



            <div class="button-group">
                <a id="add-to-cart" href="#">
                    <button class="btn-add-to-cart btn add">Thêm vào giỏ hàng</button>
                </a>

                <a id="buy-now" href="#">
                    <button class="btn-buy-now btn buy"> Mua ngay</button>
                </a>
            </div>

            <div id="cart-notification" class="notification hidden">
                <i class="fa-solid fa-circle-check"></i>
                <span>Thêm vào giỏ hàng thành công</span>
            </div>

        </div>
    </div>

</div>
<%--summary__list--%>
</body>
</html>
