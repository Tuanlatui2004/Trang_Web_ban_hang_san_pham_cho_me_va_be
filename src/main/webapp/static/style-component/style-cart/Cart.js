$(document).ready(function () {
    const loginButton = $('.btn_login');

    // Xử lý khi người dùng click nút Đăng nhập
    loginButton.on('click', function () {
        // Chuyển đến trang đăng nhập
        window.location.href = "login"; // Đường dẫn trang đăng nhập
    });

    // Kiểm tra trạng thái đăng nhập khi trang được tải
    const userId = sessionStorage.getItem("userId");
    const sessionId = sessionStorage.getItem("sessionId");

    if (userId && sessionId) {
        $('.btn_login').hide();
    }

    const tieptuc = $('.btn_shopping');
    tieptuc.on('click', function (){
        window.location.href = 'home';
    })

});