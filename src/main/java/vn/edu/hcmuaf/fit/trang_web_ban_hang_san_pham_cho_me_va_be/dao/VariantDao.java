package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Variant;

import java.util.List;

//chưa fix cái thuộc tính value
@RegisterConstructorMapper(Variant.class)
public interface VariantDao {

    @SqlQuery("SELECT * FROM variant")
    List<Variant> getAllVariants();

    @SqlQuery("SELECT * FROM variant WHERE id = :id")
    Variant getVariantById(@Bind("id") Integer id);



    @SqlQuery("SELECT * FROM variant WHERE category_id IS NULL OR category_id = :category_id")
    List<Variant> getVariantsByCategoryId(@Bind("category_id") Integer category_id);


    @SqlUpdate("INSERT INTO variant (name, category_id) VALUES (:name, :category_id)")
    @GetGeneratedKeys("id")
    int createVariant(@Bind("name") String name, @Bind("category_id") Integer category_id);

//    @SqlUpdate("UPDATE variant SET name = :name, category_id = :categoryId WHERE id = :id")
//    void updateVariant(@Bind("id") Integer id, @Bind("name") String name, @Bind("categoryId") Integer categoryId);
//
//    @SqlUpdate("DELETE FROM variant WHERE id = :id")
//    void deleteVariant(@Bind("id") Integer id);
@SqlUpdate("INSERT INTO variant (optionId, category_id) VALUES (:option_id, :category_id)")
@GetGeneratedKeys
int addOptionVariantValue(@Bind("option_id") Integer option_id, @Bind("category_id") Integer category_id);


    @SqlQuery(value = "select *\n" +
            "from option_variant_value\n" +
            "where id = :id;")
    Variant getOptionVariantValueId(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM variant WHERE id = :id")
    List<Variant> getVariantValuesByVariantId(@Bind("id") Integer id);
}

