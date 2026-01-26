$(document).ready(function () {

    // TÍNH TIỀN
    let totalPrice = 0;
    $('.product-item').each(function () {
        const price = Number($(this).find('#price').data('price'));
        const quantity = Number($(this).find('#quantity').data('quantity'));
        totalPrice += price * quantity;
    });

    const VAT = totalPrice * 0.1;
    const beforeTax = totalPrice - VAT;

    $('#total').text(Intl.NumberFormat('vi-VN').format(totalPrice) + ' VND');
    $('#VAT').text(Intl.NumberFormat('vi-VN').format(VAT) + ' VND');
    $('#before_tax').text(Intl.NumberFormat('vi-VN').format(beforeTax) + ' VND');


    // THANH TOÁN CỨNG
    $('#pay').on('click', function (e) {
        e.preventDefault();
        window.location.href = "success";
    });

});
