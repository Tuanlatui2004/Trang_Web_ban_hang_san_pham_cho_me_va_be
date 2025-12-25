package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.ProductReview;

@RegisterConstructorMapper(ProductReview.class)
public interface ProductReviewDao {

    @SqlUpdate(value =
            "INSERT INTO review (user_id, product_id, order_id, rating, description) " +
                    "VALUES (:user_id, :product_id, :order_id, :rating, :description)")

    Boolean addReview(@Bind("user_id") Integer user_id,
                      @Bind("product_id") Integer product_id,
                      @Bind("order_id") Integer order_id,
                      @Bind("rating") Integer rating,
                      @Bind("description") String description

    );

    @SqlUpdate("UPDATE order_detail SET is_reviewed = 1 WHERE order_id = :order_id AND product_id = :product_id")
    void updateIsReviewed(@Bind("order_id") int order_id,
                          @Bind("product_id") int product_id);


    @SqlQuery("SELECT orderStatus FROM orders WHERE id = :order_id AND userId = :user_id")
    OrderStatus getOrderStatus(@Bind("order_id") int order_id, @Bind("user_id") int user_id);



    @SqlQuery("SELECT * FROM review WHERE user_id = :user_id AND order_id = :order_id AND product_id = :product_id LIMIT 1")
    ProductReview getReview(@Bind("user_id") int user_id,
                     @Bind("order_id") int order_id,
                     @Bind("product_id") int product_id);


    @SqlQuery("SELECT COUNT(*) FROM review WHERE user_id = :user_id AND order_id = :order_id AND product_id = :product_id")
    int countExistingReview(@Bind("user_id") int user_id,
                            @Bind("order_id") int order_id,
                            @Bind("product_id") int product_id);

}

