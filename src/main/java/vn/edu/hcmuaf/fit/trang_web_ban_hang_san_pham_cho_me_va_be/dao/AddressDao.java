package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;

import java.util.List;
@RegisterConstructorMapper(Address.class)
public interface AddressDao {

        @SqlQuery(value = "SELECT * " +
                "FROM address as a " +
                "WHERE a.user_id = :user_id and a.status= 'ACTIVE'" )
        List<Address> getAddressByUserId(@Bind("user_id") Integer user_id);


        @SqlQuery(value = "SELECT *" +
                " FROM address" +
                " WHERE id = :address_id;")
        Address getAddressById(@Bind("address_id") Integer address_id);



        @SqlQuery(value = "SELECT *" +
                " FROM address" +
                " WHERE user_id = :user_id and is_default =1;")
        Address getAddressDefaultByUserId(@Bind("user_id") Integer user_id);


        @SqlUpdate("UPDATE address " +
                "SET is_default = :default_status " +
                "WHERE id = :id; ")
        Boolean updateDefaultById(@Bind("id") Integer id, @Bind("default_status") boolean default_status);




        @SqlUpdate("INSERT INTO address (user_id, province, district, commune,  detail, phone, name, is_default, type, status) " +
                "VALUES (:user_id, :province, :district,  :commune, :detail, :phone, :name, :is_default, :type, :status)")
        int addAddress(    @BindBean Address address);



        @SqlUpdate("UPDATE address " +
                " SET status=:status " +
                "WHERE id =:id ")
        Boolean updateStatus(@Bind("id") Integer id,@Bind("status") String status);

}
