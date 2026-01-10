package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.test;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
public class testdb {
    public static void main(String[] args) {
        DBConnection.getJdbi().useHandle(handle -> {
            String db = handle.createQuery("SELECT DATABASE()")
                    .mapTo(String.class)
                    .one();
            System.out.println("WEB CONNECT DB: " + db);
        });
    }
}
