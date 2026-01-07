package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ProductReviewDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.ProductReview;

public class ProductReviewService {
    Jdbi jdbi;
    ProductReviewDao productReviewDao;

    public ProductReviewService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.productReviewDao = jdbi.onDemand(ProductReviewDao.class);
    }

    public Boolean addReview(ProductReview review) {
        return productReviewDao.addReview(review.getUser_id(),
                review.getProduct_id(),
                review.getOrder_id(),
                review.getRating(),
                review.getDescription()
        );
    }

    public static void main(String[] args) {
        ProductReviewService reviewService = new ProductReviewService(DBConnection.getJdbi());
        ProductReview review = new ProductReview();
        review.setDescription("Test Review");
        review.setOrder_id(57);
        review.setRating(5);
        review.setUser_id(39);
        review.setProduct_id(44);

        System.out.println(reviewService.addReview(review));
    }
}
