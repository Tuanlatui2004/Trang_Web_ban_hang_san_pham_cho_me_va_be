package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.CategoryDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.CategoryWithStock;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(Jdbi jdbi) {
        this.categoryDao = jdbi.onDemand(CategoryDao.class);
    }




    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }



    public Category getCategoryById(Integer id) {
        Category category = categoryDao.getCategoryById(id);
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        return category;
    }

    public Category createCategory(String name, Boolean isActive ) {
        int id = categoryDao.createCategory(name, isActive);
        return categoryDao.getCategoryById(id);
    }

    public void updateCategory(Integer id, String name) {
        categoryDao.updateCategory(id, name);
    }

    public void deleteCategory(Integer id) {
        categoryDao.deleteCategory(id);
    }


}
