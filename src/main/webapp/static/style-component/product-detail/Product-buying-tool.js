// Xử lý variant (FIX)
$(document).ready(function () {

    const buy_now = $('#buy-now');
    const add_to_cart = $('#add-to-cart');
    const product = $('#product');

    const product_id = product.data('id');
    const option_id_default = product.data('option-default');

    const wrap_variant = $('.wrap_variant');

    // lưu option theo từng variant
    let selectedOptions = {};
    let currentOptionId = option_id_default;

    // ====== CHỌN MẶC ĐỊNH ======
    wrap_variant.each(function () {
        const variantBlock = $(this);
        const options = variantBlock.find('.option-item');
        const first = options.first();

        options.removeClass('selected');
        first.addClass('selected');

        const variantKey = variantBlock.data('variant');
        // ví dụ: data-variant="weight", "age"

        selectedOptions[variantKey] = {
            optionId: first.data('option-id'),
            price: first.data('price')
        };

        // set giá theo option đang xét
        if (first.data('price')) {
            $('#price').text(
                Number(first.data('price')).toLocaleString('vi-VN') + ' VND'
            );
        }

        currentOptionId = first.data('option-id');
    });

    updateButtons(currentOptionId);

    // ====== CLICK CHỌN OPTION ======
    wrap_variant.each(function () {
        const variantBlock = $(this);
        const options = variantBlock.find('.option-item');
        const variantKey = variantBlock.data('variant');

        options.on('click', function () {

            // ❗ CHỈ BỎ selected TRONG CÙNG 1 VARIANT
            options.removeClass('selected');
            $(this).addClass('selected');

            const optionId = $(this).data('option-id');
            const price = $(this).data('price');

            selectedOptions[variantKey] = {
                optionId: optionId,
                price: price
            };

            // cập nhật giá (500g / 800g sẽ đổi đúng)
            if (price) {
                $('#price').text(
                    Number(price).toLocaleString('vi-VN') + ' VND'
                );
            }

            currentOptionId = optionId;
            updateButtons(currentOptionId);
        });
    });

    // ====== ADD TO CART ======
    add_to_cart.on('click', function (e) {
        e.preventDefault();
        addToCart(product_id, currentOptionId);
    });

    function updateButtons(optionId) {
        buy_now.attr(
            'href',
            'buy-now?productId=' + product_id + '&optionId=' + optionId
        );
    }
});

// ====== ADD CART ======
function addToCart(productId, optionId) {
    fetch("add-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `productId=${productId}&optionId=${optionId}`
    })
        .then(res => console.log(res))
        .catch(err => console.log(err));
}
