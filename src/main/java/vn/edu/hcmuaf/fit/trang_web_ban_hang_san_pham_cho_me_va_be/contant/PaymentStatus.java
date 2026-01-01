package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant;

public enum PaymentStatus {
    PENDING,       // Chưa thanh toán
    PAID,          // Đã thanh toán
    FAILED,        // Thanh toán thất bại
    REFUNDED,      // Đã hoàn tiền
    CANCELLED
}
