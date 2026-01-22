<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.css">

    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>

    <script src="${pageContext.request.contextPath}/static/style-component/product-detail/Product-buying-tool.js"
            defer></script>
</head>
<body>

<div class="container product-buy-tool" data-product-id="${product.id}">

    <div class="product-title">${product.name}</div>

    <a href="${pageContext.request.contextPath}/reviews?productId=${product.id}" class="product-rating">
        ★★★☆☆ <span>(${product.reviewCount} đánh giá)</span>
    </a>

    <div class="product-features">
        <ul>
            <li>
                <div id="stock-status-text">
                    Tình trạng:
                    <c:choose>
                        <c:when test="${product.stock > 0}">Còn hàng</c:when>
                        <c:otherwise><span style="color:red">Hết hàng</span></c:otherwise>
                    </c:choose>
                </div>
                <div>Mã sản phẩm: ${product.sku}</div>
                <div>Thương hiệu: ${product.brand}</div>
                <div>Dòng sản phẩm: ${product.category}</div>
            </li>
        </ul>
    </div>

    <c:if test="${not empty optionVariant}">
        <div class="option-title">Chọn phân loại</div>
        <div class="capacity-options">
            <c:forEach var="op" items="${optionVariant}" varStatus="st">
                <%-- Bổ sung data-price và data-stock để JS có thể đọc được --%>
                <div class="capacity-option option-item ${st.index == 0 ? 'active' : ''}"
                     data-option-id="${op.id}"
                     data-price="${op.price}"
                     data-stock="${op.stock}">
                        ${op.variantValue}
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div id="price" class="price">
        <c:choose>
            <c:when test="${not empty productPrice}">
                <fmt:formatNumber value="${productPrice}" pattern="#,###"/> VND
            </c:when>
            <c:otherwise>Đang cập nhật</c:otherwise>
        </c:choose>
    </div>

    <div class="button-group">
        <button type="button" id="add-to-cart" class="btn-add-to-cart" ${product.stock <= 0 ? 'disabled' : ''}>
            Thêm vào giỏ hàng
        </button>
        <button type="button" id="buy-now" class="btn-buy-now" ${product.stock <= 0 ? 'disabled' : ''}>
            Mua ngay
        </button>
    </div>

</div>

<form id="buyForm" method="post" action="${pageContext.request.contextPath}/buy">
    <input type="hidden" name="productId" value="${product.id}">
    <%-- Gán giá trị mặc định cho option đầu tiên --%>
    <input type="hidden" name="optionId" id="selectedOptionId" value="${optionVariant[0].id}">
    <input type="hidden" name="quantity" value="1">
</form>

</body>
</html>