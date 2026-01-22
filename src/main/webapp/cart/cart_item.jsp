<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="wrap mid_align row product-item"
     data-id="${item.productId}"
     data-stock="${item.stock}">

    <!-- CHECKBOX -->
    <input type="checkbox"
           class="product_checked"
           value="${item.productId}"
           checked>

    <!-- IMAGE -->
    <div class="image">
        <img src="${empty item.imageUrl
            ? pageContext.request.contextPath.concat('static/image/default_img.jpg')
            : item.imageUrl}" />
    </div>

    <!-- DESCRIPTION -->
    <div class="description mid_align col">
        <div class="title">${item.name}</div>

        <div class="color">
            ${item.optionName}:
            <span>${item.optionValue}</span>
        </div>

        <div class="status">
            <span class="status_type">Còn hàng</span>
        </div>
    </div>

    <!-- PRICE + QUANTITY -->
    <div class="section_price mid_align col">

        <span class="price"
              data-price="${item.price}"
              data-quantity="${item.quantity}">
            <fmt:formatNumber value="${item.price}" pattern="#,###"/> VND
        </span>

        <div class="quantity mid_align row">
            <button type="button" class="decrement">
                <i class="fa-solid fa-minus"></i>
            </button>

            <span class="num mid_align"
                  data-quantity="${item.quantity}">
                ${item.quantity}
            </span>

            <button type="button" class="increment">
                <i class="fa-solid fa-plus"></i>
            </button>
        </div>

        <div class="remove">
            <i class="del fa-solid fa-trash-can"></i>
        </div>
    </div>
</div>
