package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.BrandDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Brand;

import java.util.List;

// NV fix is_active theo models
public class BrandService {
        private final BrandDao brandDao;

        public BrandService(Jdbi jdbi) {
            this.brandDao = jdbi.onDemand(BrandDao.class);
        }

        public List<Brand> getAllBrands() {
            return brandDao.getAllBrand();
        }

        public Brand getBrandById(Integer id) {
            return brandDao.getBrandById(id);
        }

        public Brand createBrand(String name, boolean is_active) {
            int id = brandDao.createBrand(name, is_active);
            return brandDao.getBrandById(id);
        }

        public void toggleBrandStatus(Integer id, boolean is_active) {
            brandDao.updateBrandStatus(id, is_active);
        }


        public void updateBrand(Integer id, String name) {
            brandDao.updateBrand(id, name);
        }

        public void deleteBrand(Integer id) {
            brandDao.deleteBrand(id);
        }
}
