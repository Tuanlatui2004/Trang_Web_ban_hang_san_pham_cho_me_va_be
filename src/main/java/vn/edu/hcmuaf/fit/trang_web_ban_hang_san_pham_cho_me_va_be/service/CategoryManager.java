package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.CategoryRepository;
import org.jdbi.v3.core.Jdbi;
public class CategoryManager {
    private final CategoryRepository categoryRepository;

    public CategoryManager(Jdbi jdbi) {
        this.categoryRepository = jdbi.onDemand(CategoryRepository.class);
    }

    public void addCategory(Category category) {
        categoryRepository.addCategory(category.getName());
    }
}
