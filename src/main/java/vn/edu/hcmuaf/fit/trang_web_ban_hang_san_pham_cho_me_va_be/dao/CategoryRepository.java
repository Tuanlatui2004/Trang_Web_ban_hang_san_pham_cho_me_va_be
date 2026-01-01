package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


@RegisterConstructorMapper(Category.class)
public interface CategoryRepository {
    @SqlUpdate("INSERT INTO categories (name) VALUES (:name)")
    void addCategory(@Bind("name") String name);
}
