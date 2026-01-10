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
            "INSERT INTO review (userId, productId, orderId, rating, description) " +
                    "VALUES (:userId, :productId, :orderId, :rating, :description)")
    Boolean addReview(@Bind("userId") Integer userId,
                      @Bind("productId") Integer productId,
                      @Bind("orderId") Integer orderId,
                      @Bind("rating") Integer rating,
                      @Bind("description") String description
    );


}

