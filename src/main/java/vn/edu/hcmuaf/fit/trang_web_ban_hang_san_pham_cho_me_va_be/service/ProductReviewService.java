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

    public boolean isOrderDelivered(int order_id, int user_id) {
        OrderStatus orderStatus = productReviewDao.getOrderStatus(order_id, user_id);
        return orderStatus != null && orderStatus == OrderStatus.DELIVERED;
    }

    public ProductReview getReview(int user_id, int order_id, int product_id) {
        return productReviewDao.getReview(user_id, order_id, product_id);
    }


    public Boolean addReview(ProductReview review) {
        int existing = productReviewDao.countExistingReview(
                review.getUser_id(),
                review.getOrder_id(),
                review.getProduct_id()
        );

        if (existing > 0) {
            System.out.println(" Review đã tồn tại");
            return false;
        }

        boolean success = productReviewDao.addReview(
                review.getUser_id(),
                review.getProduct_id(),
                review.getOrder_id(),
                review.getRating(),
                review.getDescription()
        );

        if (success) {
            productReviewDao.updateIsReviewed(review.getOrder_id(), review.getProduct_id());
        }

        return success;
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
