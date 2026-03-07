package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.CategoryRepository;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Category;

public class CategoryManager {
    private final CategoryRepository categoryRepository;

    public CategoryManager(Jdbi jdbi) {
        this.categoryRepository = jdbi.onDemand(CategoryRepository.class);
    }

    public void addCategory(Category category) {
        categoryRepository.addCategory(category.getName());
    }
}

