<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.name} - Chi tiết sản phẩm</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/product-detail/review.css">

    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail-item.js" defer></script>
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.js" defer></script>
    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/review.js" defer></script>
</head>
<body>

<div class="cart_header">
    <jsp:include page="/home/header.jsp"/>
</div>

<div class="container product-main-content" style="display: flex; gap: 30px; margin-top: 20px;">

    <div class="section-left" style="flex: 1;">
        <div class="carousel-container">
            <div class="main-image-box">
                <img id="mainImage" src="${primaryImageUrl}" alt="${product.name}" class="carousel-image">
            </div>

            <c:if test="${images.size() > 0}">
                <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
                <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>
            </c:if>

            <div class="thumbnails">
                <img src="${primaryImageUrl}" class="thumbnail active" onclick="changeImage(this.src, this)">
                <c:forEach var="img" items="${images}">
                    <c:if test="${img ne primaryImageUrl}">
                        <img src="${img}" class="thumbnail" onclick="changeImage(this.src, this)">
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

    <div class="section-right" style="flex: 1;">
        <div id="product-info-container" data-id="${product.id}">
            <h1 class="product-title">${product.name}</h1>

            <div class="product-rating">
                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-regular fa-star"></i>
                <span>(4.5/5 - ${product.noOfViews} lượt xem)</span>
            </div>

            <c:if test="${not empty optionVariant && not empty variants}">
                <c:forEach items="${variants}" var="type">
                    <div class="wrap_variant">
                        <div class="option-title">Chọn ${type}:</div>
                        <div class="option-list" style="display: flex; gap: 10px; margin: 10px 0;">
                            <c:forEach items="${optionVariant}" var="op">
                                <c:if test="${op.variantName eq type}">
                                    <div class="option-item ${op.id == product.optionId ? 'active' : ''}"
                                         data-option-id="${op.id}"
                                         data-price="${op.price}"
                                         data-stock="${op.stock}">
                                            ${op.variantValue}
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <div id="price" class="price" style="color: #d0021b; font-size: 32px; font-weight: bold; margin: 20px 0;">
                <c:choose>
                    <c:when test="${not empty productPrice}">
                        <fmt:formatNumber value="${productPrice}" pattern="#,###"/> VND
                    </c:when>
                    <c:otherwise>Đang cập nhật</c:otherwise>
                </c:choose>
            </div>

            <div class="product-features">
                <ul style="padding-left: 20px;">
                    <c:forEach var="desc" items="${descriptions}">
                        <c:if test="${not empty desc}">
                            <li>${desc}</li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>

            <div class="button-group" style="display: flex; gap: 15px; margin-top: 30px;">
                <button type="button" id="add-to-cart-btn" class="btn-add-to-cart" style="flex: 1; padding: 15px; cursor: pointer;">
                    THÊM VÀO GIỎ HÀNG
                </button>
                <button type="button" id="buy-now-btn" class="btn-buy-now" style="flex: 1; padding: 15px; background: #007bff; color: white; border: none; cursor: pointer;">
                    MUA NGAY
                </button>
            </div>

            <div id="cart-notification" class="notification hidden" style="display:none; color: green; margin-top: 10px;">
                <i class="fa-solid fa-circle-check"></i> Thêm thành công!
            </div>
        </div>
    </div>
</div>

<form id="buyForm" action="${pageContext.request.contextPath}/buy" method="post" style="display: none;">
    <input type="hidden" name="productId" value="${product.id}">
    <input type="hidden" name="optionId" id="selectedOptionId" value="${product.optionId}">
    <input type="hidden" name="quantity" value="1">
</form>

<div class="summary__list" style="display: flex; justify-content: space-around; background: #f9f9f9; padding: 20px; margin-top: 40px;">
    <div class="summary__item text-center">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/pediatrics_24dp_000000_FILL0_wght400_GRAD0_opsz24.png" alt="Brand" class="summary__icon">
        <p class="summary__text">Thương Hiệu Chính Hãng</p>
    </div>
    <div class="summary__item text-center">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/replay_10_24dp_000000_FILL0_wght400_GRAD0_opsz24.png" alt="Age" class="summary__icon">
        <p class="summary__text">Phù hợp mọi lứa tuổi</p>
    </div>
    <div class="summary__item text-center">
        <img src="${pageContext.request.contextPath}/static/image/img-detail/cool_to_dry_24dp_000000_FILL0_wght400_GRAD0_opsz24.png" alt="Storage" class="summary__icon">
        <p class="summary__text">Bảo quản nơi khô ráo</p>
    </div>
</div>

<div class="feature-benefit" style="padding: 40px 10%; line-height: 1.8;">
    <h3>Hướng Dẫn Sử Dụng & Bảo Quản</h3>
    <div class="content">
        ${product.description}
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-detail.js"></script>
</body>
</html>