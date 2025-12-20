package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.ProductReview;

@RegisterConstructorMapper(ProductReview.class)
public interface ProductReviewDao {

    @SqlUpdate(value =
            "INSERT INTO review (userId, productId, orderid, rating, description) " +
                    "VALUES (:userId, :productId, :orderId, :rating, :description)")

    Boolean addReview(@Bind("userId") Integer userId,
                      @Bind("productId") Integer productId,
                      @Bind("orderId") Integer orderId,
                      @Bind("rating") Integer rating,
                      @Bind("description") String description

    );

    @SqlUpdate("UPDATE order_detail SET isReviewed = 1 WHERE orderId = :orderId AND productId = :productId")
    void updateIsReviewed(@Bind("orderId") int orderId,
                          @Bind("productId") int productId);

//chưa fix cái này
    @SqlQuery("SELECT orderStatus FROM orders WHERE id = :orderId AND userId = :userId")
    OrderStatus getOrderStatus(@Bind("orderId") int orderId, @Bind("userId") int userId);



    @SqlQuery("SELECT * FROM review WHERE userId = :userId AND orderId = :orderId AND productId = :productId LIMIT 1")
    ProductReview getReview(@Bind("userId") int userId,
                     @Bind("orderId") int orderId,
                     @Bind("productId") int productId);


    @SqlQuery("SELECT COUNT(*) FROM review WHERE userId = :userId AND orderId = :orderId AND productId = :productId")
    int countExistingReview(@Bind("userId") int userId,
                            @Bind("orderId") int orderId,
                            @Bind("productId") int productId);

}

