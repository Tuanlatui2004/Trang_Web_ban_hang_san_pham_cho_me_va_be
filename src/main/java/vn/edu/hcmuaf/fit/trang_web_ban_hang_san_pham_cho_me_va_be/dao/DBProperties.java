package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import java.io.IOException;
import java.util.Properties;
public class DBProperties {
    private static Properties prop = new Properties();

    static {
        try{
            prop.load(DBProperties.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String host(){
        return prop.get("db.host").toString();
    }

    public static int port(){
        try{
            return Integer.parseInt(prop.get("db.port").toString());
        } catch (NumberFormatException e){
            return 3306;
        }
    }

    public static String usernsame(){
        return prop.get("db.username").toString();
    }

    public static String password(){
        return prop.get("db.password").toString();
    }

    public static String dbname(){
        return prop.get("db.name").toString();
    }

    //public static String dboption(){
    //        return prop.get("db.option").toString();
    //    }
}
