// Biến toàn cục để quản lý slide ảnh
let currentSlideIndex = 0;

/**
 * PHẦN 1: XỬ LÝ CAROUSEL HÌNH ẢNH
 */
function showSlide(index) {
    const thumbnails = document.querySelectorAll(".thumbnail");
    if (thumbnails.length === 0) return;

    // Đảm bảo index vòng lặp
    if (index >= thumbnails.length) currentSlideIndex = 0;
    else if (index < 0) currentSlideIndex = thumbnails.length - 1;
    else currentSlideIndex = index;

    const mainImage = document.getElementById("mainImage");
    const selectedThumb = thumbnails[currentSlideIndex];

    // Cập nhật ảnh chính và hiệu ứng mờ
    mainImage.style.opacity = '0.5';
    setTimeout(() => {
        mainImage.src = selectedThumb.src;
        mainImage.style.opacity = '1';
    }, 100);

    // Cập nhật class active cho thumbnail
    thumbnails.forEach(thumb => thumb.classList.remove("active"));
    selectedThumb.classList.add("active");
}

function nextSlide() {
    showSlide(currentSlideIndex + 1);
}

function prevSlide() {
    showSlide(currentSlideIndex - 1);
}

// Cho phép click trực tiếp vào thumbnail (hàm này được gọi từ attribute onclick trong JSP)
function changeImage(src, element) {
    const mainImage = document.getElementById("mainImage");
    mainImage.src = src;

    document.querySelectorAll(".thumbnail").forEach(thumb => thumb.classList.remove("active"));
    element.classList.add("active");

    // Cập nhật lại index hiện tại để nút next/prev chạy đúng
    const thumbnails = Array.from(document.querySelectorAll(".thumbnail"));
    currentSlideIndex = thumbnails.indexOf(element);
}

/**
 * PHẦN 2: XỬ LÝ CHỌN BIẾN THỂ VÀ GIÁ (BUYING TOOL)
 */
function initBuyingTool() {
    const optionItems = document.querySelectorAll(".option-item");
    const priceDisplay = document.getElementById("price");
    const hiddenOptionInput = document.getElementById("selectedOptionId");

    optionItems.forEach(item => {
        item.addEventListener("click", function () {
            // 1. Highlight nút được chọn trong cùng một nhóm (wrap_variant)
            const parent = this.closest(".wrap_variant");
            parent.querySelectorAll(".option-item").forEach(el => el.classList.remove("active"));
            this.classList.add("active");

            // 2. Lấy thông tin từ data attributes
            const optionId = this.getAttribute("data-option-id");
            const price = this.getAttribute("data-price");

            // 3. Cập nhật giá hiển thị (định dạng tiền tệ VN)
            if (priceDisplay && price) {
                const formattedPrice = new Intl.NumberFormat('vi-VN').format(price) + " VND";
                priceDisplay.innerText = formattedPrice;
            }

            // 4. Cập nhật vào form ẩn để gửi đi
            if (hiddenOptionInput) {
                hiddenOptionInput.value = optionId;
            }
        });
    });
}

/**
 * PHẦN 3: XỬ LÝ GIỎ HÀNG (AJAX) VÀ MUA NGAY
 */
function initCartActions() {
    const addBtn = document.getElementById("add-to-cart-btn");
    const buyBtn = document.getElementById("buy-now-btn");
    const buyForm = document.getElementById("buyForm");
    const notification = document.getElementById("cart-notification");

    // Thêm vào giỏ hàng bằng AJAX (Sử dụng jQuery vì file JSP của bạn đã import jQuery)
    if (addBtn) {
        addBtn.addEventListener("click", function (e) {
            e.preventDefault();

            const productId = buyForm.querySelector('[name="productId"]').value;
            const optionId = document.getElementById("selectedOptionId").value;
            const quantity = buyForm.querySelector('[name="quantity"]').value;

            $.ajax({
                url: contextPath + "/add-to-cart", // Sử dụng biến contextPath từ JSP
                type: "POST",
                data: {
                    productId: productId,
                    optionId: optionId,
                    quantity: quantity
                },
                success: function (response) {
                    // Hiển thị thông báo thành công
                    notification.style.display = "block";
                    setTimeout(() => {
                        notification.style.display = "none";
                    }, 3000);
                },
                error: function () {
                    alert("Không thể thêm vào giỏ hàng. Vui lòng thử lại!");
                }
            });
        });
    }

    // Mua ngay (Submit form trực tiếp)
    if (buyBtn) {
        buyBtn.addEventListener("click", function () {
            buyForm.submit();
        });
    }
}

/**
 * KHỞI TẠO KHI TRANG TẢI XONG
 */
document.addEventListener("DOMContentLoaded", function () {
    // Khởi tạo Carousel
    if (document.querySelectorAll(".thumbnail").length > 0) {
        showSlide(0);
    }

    // Khởi tạo công cụ chọn biến thể
    initBuyingTool();

    // Khởi tạo hành động giỏ hàng
    initCartActions();
});