package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.CategoryWithStock;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.CategoryCustomDao;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
public class CategoryCustomService {
    private final CategoryCustomDao categoryCustomDAO;

    public CategoryCustomService(Jdbi jdbi) {
        this.categoryCustomDAO = jdbi.onDemand(CategoryCustomDao.class);
    }

    public List<CategoryWithStock> getCustomCategoriesWithTotalStock() {
        return categoryCustomDAO.getCustomCategoriesWithStock();
    }

    public List<CategoryWithStock> searchCategories(String search) {
        return categoryCustomDAO.searchCategoriesByName(search);
    }


    public static void main(String[] args) {
        CategoryCustomService categoryCustomService =  new CategoryCustomService(DBConnection.getJdbi());
        List<CategoryWithStock> categories = categoryCustomService.getCustomCategoriesWithTotalStock();
        System.out.println(categories);
    }
}
