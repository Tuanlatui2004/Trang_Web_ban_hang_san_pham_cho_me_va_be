<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="carousel-container">
    <div class="main-image-wrapper">
        <img id="mainImage"
             src="${not empty primaryImageUrl ? primaryImageUrl : (not empty images ? images[0] : '/static/image/default-product.png')}"
             alt="${product.name}"
             class="carousel-image">
    </div>

    <c:if test="${images.size() > 1}">
        <div class="nav-arrow left" onclick="prevImage()">&#10094;</div>
        <div class="nav-arrow right" onclick="nextImage()">&#10095;</div>
    </c:if>

    <div class="thumbnails">
        <%-- Ảnh chính cũng nên xuất hiện trong danh sách thumbnail --%>
        <c:if test="${not empty primaryImageUrl}">
            <img src="${primaryImageUrl}"
                 alt="Primary Image"
                 class="thumbnail active"
                 onclick="changeMainImage(this.src, this)">
        </c:if>

        <%-- Danh sách ảnh phụ --%>
        <c:forEach var="imgUrl" items="${images}" varStatus="status">
            <img src="${imgUrl}"
                 alt="Thumbnail ${status.index + 1}"
                 class="thumbnail"
                 onclick="changeMainImage(this.src, this)">
        </c:forEach>
    </div>
</div>