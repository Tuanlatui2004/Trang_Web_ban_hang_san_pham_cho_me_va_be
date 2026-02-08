$(document).ready(function () {

    // TÍNH TIỀN
    let totalPrice = 0;
    $('.product-item').each(function () {
        const price = Number($(this).data('price'));
        const quantity = Number($(this).data('quantity'));
        totalPrice += price * quantity;
    });

    const VAT = totalPrice * 0.1;
    const beforeTax = totalPrice - VAT;

    $('#total').text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
    $('#VAT').text(Intl.NumberFormat('vi-VN').format(VAT) + ' VND');
    $('#before_tax').text(Intl.NumberFormat('vi-VN').format(beforeTax) + ' VND');


    // THANH TOÁN
    $('#pay').on('click', function (e) {
        e.preventDefault();

        // Collect products
        const products = [];
        $('.product-item').each(function () {
            products.push({
                id: $(this).data('id'),
                optionId: $(this).data('option-id'),
                quantity: $(this).data('quantity'),
                total: Number($(this).data('price')) * Number($(this).data('quantity'))
            });
        });

        // Collect address
        const address_id = $('#address').data('address-id');
        if (!address_id) {
            alert("Vui lòng chọn địa chỉ giao hàng");
            return;
        }

        // Collect payment method
        const card = $('input[name="payment-method"]:checked').data('payment');
        if (!card) {
            alert("Vui lòng chọn phương thức thanh toán");
            return;
        }

        const data = {
            address_id: address_id.toString(),
            card: card.toString(),
            products: products
        };

        console.log("Sending checkout data:", data);

        fetch('checkout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                console.log("Checkout result:", result);
                if (result.success) {
                    window.location.href = "success";
                } else {
                    alert("Đã có lỗi xảy ra trong quá trình thanh toán. Vui lòng thử lại.");
                }
            })
            .catch(error => {
                console.error("Error during checkout:", error);
                alert("Lỗi kết nối máy chủ.");
            });
    });

});
