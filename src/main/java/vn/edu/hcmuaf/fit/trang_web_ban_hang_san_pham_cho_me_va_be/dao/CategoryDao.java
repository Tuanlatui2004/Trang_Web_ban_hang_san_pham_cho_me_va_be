package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;

import java.util.List;

@RegisterConstructorMapper(Category.class)
public interface CategoryDao {



    @SqlQuery("SELECT * FROM categories WHERE is_active = 1")
    List<Category> getAllCategories();

    @SqlQuery("SELECT * FROM categories WHERE id = :id")
    Category getCategoryById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO categories (name, is_active) VALUES (:name, COALESCE(:is_active, 1))")
    @GetGeneratedKeys("id")
    int createCategory(@Bind("name") String name, @Bind("is_active") Boolean is_active);


    @SqlUpdate("UPDATE categories SET name = :name WHERE id = :id")
    void updateCategory(@Bind("id") Integer id, @Bind("name") String name);

    @SqlUpdate("DELETE FROM categories WHERE id = :id")
    void deleteCategory(@Bind("id") Integer id);
//
//    @SqlUpdate("UPDATE categories SET isActive = :isActive WHERE id = :id")
//    void updateCategoryStatus(@Bind("id") Integer id, @Bind("isActive") Boolean isActive);

    @SqlUpdate("UPDATE categories SET isActive = :is_active WHERE id = :id")
    void updateCategoryStatus(@Bind("id") Integer id, @Bind("is_active") int is_active);


    //    @SqlQuery("""
//    SELECT c.id, c.name, c.isActive, COUNT(p.id) AS totalStock
//    FROM categories c
//    LEFT JOIN products p ON p.categoryId = c.id
//    GROUP BY c.id, c.name, c.isActive
//""")

//chưa full đâu


}
