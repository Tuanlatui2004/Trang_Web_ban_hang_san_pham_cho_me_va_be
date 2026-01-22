/**
 * Xử lý logic chọn biến thể, cập nhật giá và thêm vào giỏ hàng
 */
document.addEventListener("DOMContentLoaded", function () {
    const optionItems = document.querySelectorAll('.option-item');
    const priceDisplay = document.getElementById('price');
    const selectedOptionInput = document.getElementById('selectedOptionId');
    const buyForm = document.getElementById('buyForm');
    const stockStatus = document.querySelector('.product-features div:first-child');

    // 1. Hàm định dạng tiền tệ VNĐ
    const formatCurrency = (amount) => {
        return new Intl.NumberFormat('vi-VN').format(amount) + " VND";
    };

    // 2. Xử lý khi click vào từng Option (Size/Dung tích)
    optionItems.forEach(item => {
        item.addEventListener('click', function () {
            // Loại bỏ active ở các nút khác và thêm vào nút hiện tại
            optionItems.forEach(el => el.classList.remove('active'));
            this.classList.add('active');

            // Lấy dữ liệu từ thuộc tính data-
            const optionId = this.getAttribute('data-option-id');
            const price = this.getAttribute('data-price');
            const stock = this.getAttribute('data-stock');

            // Cập nhật giá trị vào input ẩn để gửi form
            if (selectedOptionInput) {
                selectedOptionInput.value = optionId;
            }

            // Cập nhật hiển thị giá tiền
            if (priceDisplay && price) {
                priceDisplay.innerText = formatCurrency(price);
            }

            // Cập nhật trạng thái kho hàng và vô hiệu hóa nút nếu hết hàng
            updateStockUI(stock);
        });
    });

    // 3. Hàm cập nhật trạng thái kho hàng trên giao diện
    function updateStockUI(stock) {
        const buyNowBtn = document.getElementById('buy-now');
        const addToCartBtn = document.getElementById('add-to-cart');
        const stockValue = parseInt(stock);

        if (stockValue > 0) {
            stockStatus.innerHTML = `Tình trạng: <span>Còn hàng</span>`;
            stockStatus.style.color = "inherit";
            if (buyNowBtn) buyNowBtn.disabled = false;
            if (addToCartBtn) addToCartBtn.disabled = false;
        } else {
            stockStatus.innerHTML = `Tình trạng: <span style="color:red">Hết hàng</span>`;
            if (buyNowBtn) buyNowBtn.disabled = true;
            if (addToCartBtn) addToCartBtn.disabled = true;
        }
    }

    // 4. Xử lý nút "Mua ngay"
    const buyNowBtn = document.getElementById('buy-now');
    if (buyNowBtn) {
        buyNowBtn.addEventListener('click', function () {
            if (!selectedOptionInput.value) {
                alert("Vui lòng chọn một phân loại sản phẩm!");
                return;
            }
            buyForm.action = contextPath + "/buy"; // contextPath lấy từ biến toàn cục
            buyForm.submit();
        });
    }

    // 5. Xử lý nút "Thêm vào giỏ hàng" bằng AJAX (Không load lại trang)
    const addToCartBtn = document.getElementById('add-to-cart');
    if (addToCartBtn) {
        addToCartBtn.addEventListener('click', function () {
            const optionId = selectedOptionInput.value;
            if (!optionId) {
                alert("Vui lòng chọn một phân loại!");
                return;
            }

            // Gửi yêu cầu AJAX
            const productId = buyForm.querySelector('input[name="productId"]').value;
            const quantity = buyForm.querySelector('input[name="quantity"]').value;

            fetch(`${contextPath}/add-to-cart`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `productId=${productId}&optionId=${optionId}&quantity=${quantity}`
            })
                .then(response => response.json())
                .then(data => {
                    if (data.status === "success") {
                        alert("Đã thêm vào giỏ hàng thành công!");
                        // Cập nhật số lượng trên icon giỏ hàng nếu cần
                    } else {
                        alert("Có lỗi xảy ra: " + data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    }
});