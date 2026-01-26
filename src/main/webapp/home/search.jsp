<%--
  Created by IntelliJ IDEA.
  User: vinhp
  Date: 12/27/2025
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<head>
    <title>Tìm kiếm sản phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-home/search.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
<div class="overlay">
    <div class="search-container">
        <h2>Chúng tôi có thể giúp bạn tìm kiếm?</h2>
        <div class="search-bar" onclick="toggleDropdown()">
            <input type="text" id="search-input" placeholder="Nhập từ khoá ..." onfocus="toggleDropdown()" />
            <button class="search-icon" >
<%--                <i class="fa-solid fa-magnifying-glass"></i>--%>
                 <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"></path>
                 </svg>
            </button>
        </div>
        <div class="popular-keywords" id="popular-keywords">
            <p>Từ khóa Phổ biến</p>
            <ul>
                <li>Sữa Aptamil</li>
                <li>Máy hâm sữa</li>
                <li>Đồ cho bé</li>
                <li>Sữa bột</li>
            </ul>
        </div>

        <!-- Dropdown gợi ý -->
        <div class="suggestions">
            <h3>Gợi ý dành cho bạn</h3>
            <div class="product-suggestions">
                <div class="product">
                    <img src="https://cdn1.concung.com/2023/11/64617-106389-large_mobile/tpbs-aptamil-profutura-kid-cesarbiotik-3-growing-up-milk-formula-tre-tu-24-thang-tuoi-tro-len-800g.webp" alt="Product 1">
                    <p>Combo 2 sữa Aptamil Profutura KID Cesarbiotik 3 800g (Từ 2 tuổi)</p>
                    <span>1.382.000 VND</span>
                </div>
                <div class="product">
                    <img src="https://cdn1.concung.com/2024/06/40424-111100-large_mobile/abbott-grow-gold-3-huong-vani-900g.webp" alt="Product 2">
                    <p>Sữa Abbott Grow 3+ 850g (3-6 tuổi)</p>
                    <span>445.000 VND</span>
                </div>
                <div class="product">
                    <img src="https://cdn1.concung.com/2024/07/52753-112126-large_mobile/nan-supreme-pro-3-800g.webp" alt="Product 3">
                    <p>Sữa NAN SUPREME PRO số 3 800g (2-6 tuổi)</p>
                    <span>1.350.000 VND</span>
                </div>
                <div class="product">
                    <img src="https://cdn1.concung.com/2025/11/74408-131636-large_mobile/metacare-eco-2-850g.webp" alt="Product 4">
                    <p>Combo 3 Sữa Metacare Eco 2+ 850g (2-10 tuổi)</p>
                    <span>1.125.000 VND</span>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="${pageContext.request.contextPath}/static/style-component/style-home/search.js"></script>
</body>
