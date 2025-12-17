package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;
import java.util.Base64;

public abstract class BaseDao {
    private Jdbi jdbi;

    protected Jdbi get(){
        if (jdbi == null){
            makeConnect();
        }
        return jdbi;
    }

    private void makeConnect(){
        MysqlDataSource src = new MysqlDataSource();
        String url = "jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.dbname() + "?"; //+ DBProperties.option();
        src.setURL(url);
        src.setUser(DBProperties.usernsame());
        src.setPassword(DBProperties.password());

        try{
            src.setUseCompression(true);
            src.setAutoReconnect(true);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
        jdbi = jdbi.create(src);
    }
    //test running
//    public static void main(String[] args) {
//        BaseDao dao = new BaseDao();
//        dao.get();
//    }
}

