package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.CategoryWithStock;

import java.util.List;

@RegisterConstructorMapper(CategoryWithStock.class)
public interface CategoryCustomDao {

    @SqlQuery("""
        SELECT c.id, c.name, c.is_active,
               COALESCE(SUM(o.stock), 0) AS totalStock
        FROM categories c
        LEFT JOIN products p ON c.id = p.category_id
        LEFT JOIN option_variant o ON p.id = o.product_id
        GROUP BY c.id, c.name, c.is_active
    """)
    List<CategoryWithStock> getCustomCategoriesWithStock();


    @SqlQuery("SELECT c.id, c.name FROM categories c WHERE c.name LIKE CONCAT('%', :search, '%')")
    List<CategoryWithStock> searchCategoriesByName(@Bind("search") String search);
}
