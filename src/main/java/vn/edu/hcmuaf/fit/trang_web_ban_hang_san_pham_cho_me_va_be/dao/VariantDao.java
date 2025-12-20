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

    @SqlQuery("SELECT * FROM variant_value WHERE id = :id")
    Variant getVariantById(@Bind("id") Integer id);
//chưa fix
//    @SqlQuery("SELECT * FROM variant_value WHERE variantId = :variantId")
//    List<VariantValue> getVariantValuesByVariantId(@Bind("variantId") Integer variantId);

    @SqlQuery("SELECT * FROM variant WHERE categoryId IS NULL OR categoryId = :categoryId")
    List<Variant> getVariantsByCategoryId(@Bind("categoryId") Integer categoryId);


    @SqlUpdate("INSERT INTO variant (name, category_id) VALUES (:name, :categoryId)")
    @GetGeneratedKeys("id")
    int createVariant(@Bind("name") String name, @Bind("categoryId") Integer categoryId);

//    @SqlUpdate("UPDATE variant SET name = :name, category_id = :categoryId WHERE id = :id")
//    void updateVariant(@Bind("id") Integer id, @Bind("name") String name, @Bind("categoryId") Integer categoryId);
//
//    @SqlUpdate("DELETE FROM variant WHERE id = :id")
//    void deleteVariant(@Bind("id") Integer id);
}

