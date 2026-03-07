<%--
  Created by IntelliJ IDEA.
  User: vinhp
  Date: 12/27/2025
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Lấy các thuộc tính từ session
    Boolean otpVerified = (Boolean) session.getAttribute("otpVerified");
    String userEmail = (String) session.getAttribute("userEmail");
    String errorMessage = (String) request.getAttribute("errorMessage");

    // Xác định trạng thái hiển thị
    String resetBoxStyle = "block";
    String otpBoxStyle = "none";
    String passwordBoxStyle = "none";

    if (userEmail != null && otpVerified != null && otpVerified) {
        // OTP đã được xác thực - hiển thị box nhập mật khẩu mới
        resetBoxStyle = "none";
        otpBoxStyle = "none";
        passwordBoxStyle = "block";
    } else if (userEmail != null) {
        // Đã gửi OTP nhưng chưa xác thực - hiển thị box nhập OTP
        resetBoxStyle = "none";
        otpBoxStyle = "block";
        passwordBoxStyle = "none";
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt lại mật khẩu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/Style-forgot-password/forgot_password.css">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/static/style-component/Style-forgot-password/forgot_password.js"></script>

</head>
<body>
<div class="container">
    <div class="hear">
        <div class="ft1">Đặt lại mật khẩu</div>
<%--        pbha sửa tạo link--%>
        <a href="https://linkhelp " target="_blank" rel="noopener noreferrer" class="help">Bạn cần giúp đỡ?</a>
    </div>
    <!-- Email Reset Box -->
    <div id="resetBox" class="reset-box" style="display: <%= resetBoxStyle %>;">
        <div class="header">
            <h2>Đặt lại mật khẩu</h2>
        </div>
        <div class="infield">
            <input
                    type="email"
                    id="emailInput"
                    placeholder=" "
                    name="email"
                    required
            />
            <label for="emailInput">Nhập lại email <span class="required">*</span> </label>
        </div>
        <div id="errorMessage" class="error-message">Email không hợp lệ</div>
        <button onclick="validateEmail()">Tiếp theo</button>
    </div>

    <!-- OTP Verification Box -->
    <div id="otpBox" class="reset-box" style="display: <%= otpBoxStyle %>;">
        <div class="header">

            <h2>Nhập mã xác nhận</h2>
        </div>
        <p>Mã xác minh của bạn sẽ được gửi qua email.</p>
        <div class="otp-input-wrap">
            <input type="text" inputmode="numeric" pattern="[0-9]*" maxlength="6" id="otpInput" name="otp" placeholder="Nhập 6 số mã OTP" autocomplete="one-time-code">
        </div>
        <div id="otpErrorMessage" class="error-message" style="display: none;"></div>
        <p class="timer-text">Vui lòng chờ <span id="timer">60</span> giây để gửi lại.</p>
        <button onclick="confirmOTP()">Tiếp theo</button>
    </div>

    <!-- New Password Box -->
    <div id="passwordBox" class="reset-box" style="display: <%= passwordBoxStyle %>;">
        <div class="header">
            <h2>Nhập mật khẩu mới </h2>
        </div>
        <div class="password-wrapper">
            <input type="password" id="passwordInput" name="newPassword" placeholder="Nhập mật khẩu mới" required>
            <i class="fa-solid fa-eye toggle-password" data-toggle="#passwordInput"></i>
        </div>
        <div class="password-wrapper">
            <input type="password" id="confirmPasswordInput" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
            <i class="fa-solid fa-eye toggle-password" data-toggle="#confirmPasswordInput"></i>
        </div>
        <div id="passwordErrorMessage" class="error-message">Mật khẩu không khớp</div>
        <button onclick="submitPassword()">Đặt lại mật khẩu</button>
    </div>

</div>
</body>
</html>
