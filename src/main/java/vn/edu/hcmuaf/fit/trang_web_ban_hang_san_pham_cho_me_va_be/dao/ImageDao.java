package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


public interface ImageDao {

    @SqlUpdate("INSERT INTO image (url) VALUES (:url)")
    @GetGeneratedKeys
    int saveImage(@Bind("url") String url);

    @SqlUpdate("INSERT INTO product_images (product_id, image_id) VALUES (:product_id, :image_id)")
    boolean addImageToProduct(@Bind("product_id") Integer product_id, @Bind("image_id") Integer image_id);

    @SqlQuery("SELECT url FROM image WHERE id = :id")
    String getImageUrlById(@Bind("id") int id);

    @SqlQuery(value = "SELECT image.url from image " +
            "INNER JOIN product_images ON image.id = product_images.image_id " +
            "WHERE product_images.product_id = :product_id ")
    List<String> getAllImagesByProductId(@Bind("product_id") Integer product_id);
}
