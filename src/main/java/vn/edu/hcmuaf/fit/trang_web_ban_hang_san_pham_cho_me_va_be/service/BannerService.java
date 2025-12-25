package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.BannerDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Banner;

import java.time.LocalDate;
import java.util.List;

public class BannerService {
    private final BannerDao bannerDao;

    public BannerService(Jdbi jdbi) {
        this.bannerDao = jdbi.onDemand(BannerDao.class);
    }

    public List<Banner> getAllBanners() {
        return bannerDao.getAllBanners();
    }

    public Banner getBannerById(int id) {
        return bannerDao.getBannerById(id);
    }

    public Banner createBanner(String title, String image_id, LocalDate start_date, LocalDate end_date, boolean is_active, String description) {
        int id = bannerDao.createBanner(title, image_id, start_date, end_date, is_active, description);
        return getBannerById(id);
    }

    public void updateBanner(int id, String title, String image_id, LocalDate start_date, LocalDate end_date, String description) {
        bannerDao.updateBanner(id, title, image_id, start_date, end_date, description);
    }

    public void toggleBannerStatus(int id, boolean is_active) {
        bannerDao.updateBannerTitle(id, is_active);
    }


    public void deleteBanner(int id) {
        bannerDao.deleteBanner(id);
    }
}

