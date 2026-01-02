package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Address;

import java.util.List;
@RegisterConstructorMapper(Address.class)
public interface AddressDao {

        @SqlQuery(value = "SELECT * " +
                "FROM address as a " +
                "WHERE a.user_id = :user_id " )
        List<Address> getAddressByUserId(@Bind("user_id") Integer user_id);


        @SqlQuery(value = "SELECT *" +
                " FROM address" +
                " WHERE id = :address_id;")
        Address getAddressById(@Bind("address_id") Integer address_id);

    @SqlUpdate("INSERT INTO address (user_id, address_type, full_name, phone_number, street, city, state, country, is_default) " +
            "VALUES (:user_id, :province, :district, :commune, :detail, :phone, :name, :is_default, :type)")
    @GetGeneratedKeys("id")
    int addAddress(@Bind("user_id") Integer user_id,
                   @Bind("address_type") String address_type,
                   @Bind("full_name") String full_name,
                   @Bind("phone_number") String phone_number,
                   @Bind("street") String street,
                   @Bind("city") String city,
                   @Bind("state") String state,
                   @Bind("country") String country,
                   @Bind("is_default") Boolean is_default);
}
