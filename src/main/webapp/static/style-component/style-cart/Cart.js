$(document).ready(function () {

    /* =============================
       LOGIN / CONTINUE SHOPPING
    ============================== */
    $('.btn_login').on('click', function () {
        window.location.href = "login";
    });

    $('.btn_shopping').on('click', function () {
        window.location.href = "home";
    });

    /* =============================
       UPDATE BILL (CHỈ TÍNH SP ĐƯỢC CHECK)
    ============================== */
    function updateBill() {
        let totalPrice = 0;

        $('.product_checked:checked').each(function () {
            const item = $(this).closest('.product-item');
            const priceEl = item.find('.price');

            const price = parseInt(priceEl.data('price'));
            const quantity = parseInt(priceEl.data('quantity'));

            totalPrice += price * quantity;
        });

        const vat = totalPrice * 0.1;
        const beforeTax = totalPrice - vat;

        $('#total').text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
        $('#VAT').text(Intl.NumberFormat('vi-VN').format(vat) + ' VND');
        $('#before_tax').text(Intl.NumberFormat('vi-VN').format(beforeTax) + ' VND');
    }

    /* =============================
       CHECK ALL
    ============================== */
    $(document).on('change', '#check_all', function () {
        $('.product_checked').prop('checked', this.checked);
        updateBill();
    });

    /* =============================
       CHECK SINGLE → SYNC CHECK ALL
    ============================== */
    $(document).on('change', '.product_checked', function () {
        const total = $('.product_checked').length;
        const checked = $('.product_checked:checked').length;

        $('#check_all').prop('checked', total === checked);
        updateBill();
    });

    /* =============================
       INCREASE QUANTITY
    ============================== */
    $(document).on('click', '.increment', function () {
        const item = $(this).closest('.product-item');
        const qtyEl = item.find('.num');
        const priceEl = item.find('.price');

        let quantity = parseInt(qtyEl.data('quantity'));
        const stock = parseInt(item.data('stock'));
        const productId = item.data('id');

        if (quantity < stock) {
            quantity++;

            qtyEl.text(quantity).data('quantity', quantity);
            priceEl.data('quantity', quantity);

            updateQuantity(productId, quantity);
            updateBill();
        } else {
            alert("Đã đạt số lượng tối đa");
        }
    });

    /* =============================
       DECREASE QUANTITY
    ============================== */
    $(document).on('click', '.decrement', function () {
        const item = $(this).closest('.product-item');
        const qtyEl = item.find('.num');
        const priceEl = item.find('.price');

        let quantity = parseInt(qtyEl.data('quantity'));
        const productId = item.data('id');

        if (quantity > 1) {
            quantity--;

            qtyEl.text(quantity).data('quantity', quantity);
            priceEl.data('quantity', quantity);

            updateQuantity(productId, quantity);
            updateBill();
        }
    });

    /* =============================
       UPDATE QUANTITY (AJAX)
    ============================== */
    function updateQuantity(productId, quantity) {
        $.ajax({
            url: 'cart/update-quantity',
            method: 'POST',
            data: {
                productId: productId,
                quantity: quantity
            },
            error: function () {
                alert('Cập nhật số lượng thất bại');
            }
        });
    }

    /* =============================
       REMOVE ITEM
    ============================== */
    $(document).on('click', '.del', function () {
        const item = $(this).closest('.product-item');
        const productId = item.data('id');

        if (confirm('Bạn có chắc muốn xóa sản phẩm này?')) {
            $.ajax({
                url: 'cart/remove',
                method: 'POST',
                data: { productId },
                success: function () {
                    item.remove();
                    updateBill();
                }
            });
        }
    });

    /* =============================
       CHECKOUT
    ============================== */
    $('#pay').on('click', function (e) {
        const userId = sessionStorage.getItem("userId");
        const sessionId = sessionStorage.getItem("sessionId");

        if (!userId || !sessionId) {
            alert("Bạn cần đăng nhập trước!");
            e.preventDefault();
            return;
        }

        const productIds = $('.product_checked:checked')
            .map(function () {
                return $(this).val();
            }).get();

        if (productIds.length === 0) {
            alert('Vui lòng chọn ít nhất 1 sản phẩm');
            return;
        }

        window.location.href = 'checkout?productIds=' + productIds.join(',');
    });

    /* =============================
       INIT
    ============================== */
    updateBill();
});
