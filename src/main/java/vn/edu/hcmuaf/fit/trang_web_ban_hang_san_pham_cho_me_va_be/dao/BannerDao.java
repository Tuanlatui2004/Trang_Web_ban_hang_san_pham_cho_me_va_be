package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.*;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Banner;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(Banner.class)
public interface BannerDao {
    @SqlQuery("SELECT * FROM banners")
    List<Banner> getAllBanners();

    @SqlQuery("SELECT * FROM banners WHERE id = :id")
    Banner getBannerById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO banners (title, image_id, start_date, end_date, is_active, description) " +
            "VALUES (:title, :image_id, :start_date, :end_date, :is_active, :description)")
    @GetGeneratedKeys("id")
    int createBanner(@Bind("title") String title,
                     @Bind("imageId") String image_id,
                     @Bind("startDate") LocalDate start_date,
                     @Bind("endDate") LocalDate end_date,
                     @Bind("isActive") boolean is_active,
                     @Bind("description") String description);


    @SqlUpdate("UPDATE banners SET title = :title, image_id = :image_id, start_date = :start_date, end_date = :end_date, description = :description WHERE id = :id")
    void updateBanner(@Bind("id") Integer id,
                      @Bind("title") String title,
                      @Bind("imageId") String image_id,
                      @Bind("startDate") LocalDate start_date,
                      @Bind("endDate") LocalDate end_date,
                      @Bind("description") String description);


    @SqlUpdate("DELETE FROM banners WHERE id = :id")
    void deleteBanner(@Bind("id") Integer id);

    @SqlUpdate("UPDATE banners SET is_active = :isActive WHERE id = :id")
    void updateBannerTitle(@Bind("id") Integer id, @Bind("is_active") boolean is_active);
}
