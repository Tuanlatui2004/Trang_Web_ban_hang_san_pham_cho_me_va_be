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
                "WHERE a.user_id = :user_id and a.status= 'ACTIVE'" )
        List<Address> getAddressByUserId(@Bind("user_id") Integer user_id);


        @SqlQuery(value = "SELECT *" +
                " FROM address" +
                " WHERE id = :address_id;")
        Address getAddressById(@Bind("address_id") Integer address_id);

    @SqlUpdate("INSERT INTO address (user_id, province, district, commune, detail, phone, name, is_default, type) " +
            "VALUES (:user_id, :province, :district, :commune, :detail, :phone, :name, :is_default, :type)")
    @GetGeneratedKeys("id")
    int addAddress(@Bind("userId") Integer user_id,
                   @Bind("province") String province,
                   @Bind("district") String district,
                   @Bind("commune") String commune,
                   @Bind("detail") String detail,
                   @Bind("phone") String phone,
                   @Bind("name") String name,
                   @Bind("isDefault") Boolean is_default,
                   @Bind("type") String type);
}
