package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OptionVariant;

import java.util.List;


@RegisterConstructorMapper(OptionVariant.class)
public interface OptionVariantDao {

    @SqlUpdate("INSERT INTO option_variant (product_id, price, stock) VALUES (:product_id, :price, :stock)")
    @GetGeneratedKeys
    int createOption(@Bind("product_id") Integer product_id, @Bind("price") Integer price, @Bind("stock") Integer stock);


    @SqlQuery(value = "select *\n" +
            "from option_variant\n" +
            "where id = :id;")
    OptionVariant getOptionById(@Bind("id") Integer id);



    @SqlUpdate(value = "update option_variant\n" +
            "set\n" +
            "    stock = :stock " +
            "where id = :id")
    Boolean updateStock(@Bind("id") Integer id, @Bind("stock") Integer stock);



    @SqlQuery(value = "select *\n" +
            "from option_variant\n" +
            "where product_id = :product_id")
    List<OptionVariant> getOptionsByProductId(@Bind("product_id") Integer product_id);





    @SqlQuery(value = "select\n" +
            "    o.id as id, o.product_id, o.price, o.stock,\n" +
            "    v.id as variantId, v.name as variantName,\n" +
            "    v.value as variantValue \n" +
            "from\n" +
            "    option_variant as o\n" +
            "    inner join variant as  v\n" +
            "        on o.id = v.option_id\n" +
            "where o.id in (<option_ids>)\n")
    List<OptionVariant> getVariantByOptionId(@BindList("option_ids") List<Integer> option_ids);



}