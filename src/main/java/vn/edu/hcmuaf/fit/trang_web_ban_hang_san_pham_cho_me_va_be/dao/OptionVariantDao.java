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

    @SqlUpdate("INSERT INTO options (productId, price, stock) VALUES (:productId, :price, :stock)")
    @GetGeneratedKeys
    int createOption(@Bind("productId") Integer productId, @Bind("price") Integer price, @Bind("stock") Integer stock);


    @SqlQuery(value = "select *\n" +
            "from options\n" +
            "where id = :id;")
    OptionVariant getOptionById(@Bind("id") Integer id);



    @SqlUpdate(value = "update options\n" +
            "set\n" +
            "    stock = :stock " +
            "where id = :id")
    Boolean updateStock(@Bind("id") Integer id, @Bind("stock") Integer stock);



    @SqlQuery(value = "select *\n" +
            "from options\n" +
            "where productId = :productId")
    List<OptionVariant> getOptionsByProductId(@Bind("productId") Integer productId);





    @SqlQuery(value = "select\n" +
            "    o.id as id, o.productId, o.price, o.stock,\n" +
            "\n" +
            "    v.id as variantId, v.name as variantName,\n" +
            "    vv.value as variantValue \n" +
            "from\n" +
            "    options as o\n" +
            "    inner join  option_variant_value as ovv\n" +
            "         on o.id = ovv.optionId\n" +
            "    inner join variant_value as vv\n" +
            "        on ovv.variantValueId = vv.id\n" +
            "    inner join variant as  v\n" +
            "        on v.id = vv.variantId\n" +
            "where o.id in (<optionIds>)\n")
    List<OptionVariant> getVariantByOptionId(@BindList("optionIds") List<Integer> optionIds);


    @SqlUpdate("""
            UPDATE options 
            SET price = :price,
                stock = :stock
            WHERE id = :id
            """)
    boolean updateOption(@Bind("id") Integer id,
                         @Bind("price") Integer price,
                         @Bind("stock") Integer stock);

}