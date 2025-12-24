package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Brand;

import java.util.List;

@RegisterConstructorMapper(Brand.class)
public interface BrandDao {
    @SqlQuery("SELECT * FROM brand WHERE is_active = 1")
    List<Brand> getAllBrand();

    @SqlQuery("SELECT * FROM brand WHERE id = :id")
    Brand getBrandById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO brand (name) VALUES (:name)")
    @GetGeneratedKeys("id")
    int createBrand(@Bind("name") String name);

    @SqlUpdate("UPDATE brand SET name = :name WHERE id = :id")
    void updateBrand(@Bind("id") Integer id, @Bind("name") String name);

    @SqlUpdate("DELETE FROM brand WHERE id = :id")
    void deleteBrand(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO brand (name, is_active) VALUES (:name, COALESCE(:is_active, 1))")
    @GetGeneratedKeys("id")
    int createBrand(@Bind("name") String name, @Bind("is_active") Boolean is_active);


    @SqlUpdate("UPDATE brand SET is_active = :is_active WHERE id = :id")
    void updateBrandStatus(@Bind("id") Integer id, @Bind("is_active") boolean isActive);

}
