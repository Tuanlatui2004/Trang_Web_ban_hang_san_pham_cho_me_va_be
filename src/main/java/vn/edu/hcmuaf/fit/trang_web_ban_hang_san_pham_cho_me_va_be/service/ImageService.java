package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ImageDao;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;
public class ImageService {
    Jdbi jdbi;
    private  Cloudinary cloudinary;

    private  ImageDao imageDao;

    public ImageService(Jdbi jdbi, Cloudinary cloudinary, ImageDao imageDao) {
        this.jdbi = jdbi;
        this.cloudinary = cloudinary;
        this.imageDao = imageDao;
    }


    public ImageService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.imageDao = jdbi.onDemand(ImageDao.class);
    }


    public ImageService(Cloudinary cloudinary, ImageDao imageDao) {
        this.cloudinary = cloudinary;
        this.imageDao = imageDao;
    }

    // Upload ảnh lên Cloudinary và lưu vào DB
    public String uploadImage(byte[] fileBytes) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
        String image_url = (String) uploadResult.get("url");
        imageDao.saveImage(image_url);
        return image_url;
    }

    public boolean addImageToProduct(Integer product_id, Integer image_id) {
        return imageDao.addImageToProduct(product_id, image_id);
    }

    public int saveImage(String image_url) {
        return imageDao.saveImage(image_url);
    }

    public String getImageUrlById(int image_id) {
        return imageDao.getImageUrlById(image_id);
    }


    public List<String> getAllImagesByProductId(Integer product_id) {
        return imageDao.getAllImagesByProductId(product_id);
    }

}
