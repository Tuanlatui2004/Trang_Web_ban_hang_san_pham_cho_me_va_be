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

    @SqlQuery("SELECT * FROM variant WHERE id = :id")
    List<Variant> getVariantValuesByVariantId(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM variant WHERE categoryId IS NULL OR categoryId = :categoryId")
    List<Variant> getVariantsByCategoryId(@Bind("categoryId") Integer categoryId);


    @SqlUpdate("INSERT INTO variant (name, categoryId) VALUES (:name, :categoryId)")
    @GetGeneratedKeys("id")
    int createVariant(@Bind("name") String name, @Bind("categoryId") Integer categoryId);

//    @SqlUpdate("UPDATE variant SET name = :name, categoryId = :categoryId WHERE id = :id")
//    void updateVariant(@Bind("id") Integer id, @Bind("name") String name, @Bind("categoryId") Integer categoryId);
//
//    @SqlUpdate("DELETE FROM variant WHERE id = :id")
//    void deleteVariant(@Bind("id") Integer id);
@SqlUpdate("INSERT INTO variant (optionId, categoryId) VALUES (:optionId, :categoryId)")
@GetGeneratedKeys
int addOptionVariantValue(@Bind("optionId") Integer optionId, @Bind("categoryId") Integer categoryId);


    @SqlQuery(value = "select *\n" +
            "from variant\n" +
            "where id = :id;")
    Variant getOptionVariantValueId(@Bind("id") Integer id);


}

