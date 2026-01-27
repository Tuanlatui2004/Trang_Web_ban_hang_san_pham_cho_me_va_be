package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// xem lại
public class EmailService {
    // private static final String SMTP_HOST = "smtp.gmail.com";
    // private static final String SMTP_PORT = "587";
    // private static final String USERNAME = "Vinhphanngoc61@gmail.com";
    // private static final String PASSWORD = "sfqi fzci hkrv iqsp";
    // private static final Properties properties = new Properties();
    // private static Session session;
    String host = "smtp.gmail.com";
    String fromEmail = "Vinhphanngoc61@gmail.com";
    String password = "sfqi fzci hkrv iqsp";

    // Hàm gửi email chứa mã OTP
    // Hàm gửi email chứa mã OTP
    public void sendEmailWithOTP(String toEmail, String otp) throws MessagingException {
        // Cấu hình thông tin kết nối với SMTP server

        // Cấu hình các thuộc tính SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Tạo một session email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo đối tượng MimeMessage
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail)); // Địa chỉ người gửi
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Địa chỉ người nhận
        message.setSubject("Mã OTP xác thực"); // Tiêu đề email

        // Nội dung email
        String emailContent = "<h3>Toi da gui ma OTP cho ban. Ma OTP cua ban la: " + otp + "</h3>"
                + "<p>Vui lòng không chia sẻ mã OTP này voi bat ki ai.</p>";
        message.setContent(emailContent, "text/html");

        // Gửi email
        Transport.send(message);
        System.out.println("Email đã được gửi thành công đến " + toEmail);
    }

    public void sendConfirmationEmail(String toEmail, String sessionId) throws MessagingException {

        // Cấu hình các thuộc tính SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Tạo một session email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo đối tượng MimeMessage
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail)); // Địa chỉ người gửi
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Địa chỉ người nhận
        message.setSubject("Xác nhận đăng ký tài khoản");

        // Nội dung email chứa liên kết xác nhận
        String confirmLink = "http://localhost:8080//Trang_Web_ban_hang_san_pham_cho_me_va_be_war_exploded/confirm?sessionId=" + sessionId;
        String emailContent = "<h3>Xin Chào!,</h3>"
                + "<p>Vui lòng nhap vào liên ket duoi dây de xac nhan tai khoan cua ban:</p>"
                + "<a href=\"" + confirmLink + "\">Xác nhận</a>";

        message.setContent(emailContent, "text/html");

        // Gửi email
        Transport.send(message);
        System.out.println("Email xác nhận đã được gửi đến " + toEmail);
    }

    public String generateOTP() {
        int otp = (int) (Math.random() * 90000) + 10000; // Tạo OTP 5 chữ số
        return String.valueOf(otp);
    }
}
