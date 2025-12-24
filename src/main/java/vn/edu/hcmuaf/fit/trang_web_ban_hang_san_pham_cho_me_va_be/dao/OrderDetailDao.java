package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OrderDetail;

import java.util.List;

@RegisterConstructorMapper(OrderDetail.class)
public interface OrderDetailDao {

    @SqlUpdate(value = "INSERT INTO order_detail (order_id, product_id, quantity, total, option_id) " +
            "VALUES (:order_id, :product_id, :quantity, :total, :option_id)")
    Boolean addOrderDetail(
            @Bind("order_id") Integer order_id,
            @Bind("product_id") Integer product_id,
            @Bind("quantity") Integer quantity,
            @Bind("total") Integer total,
            @Bind("option_id") Integer option_id
    );

    @SqlQuery("SELECT name FROM products WHERE id = :product_id")
    String getProductNameById(@Bind("product_id") Integer product_id);

    @SqlQuery("SELECT quantity FROM order_detail WHERE id = :orderDetail_id")
    Integer getQuantityByOrderDetailId(@Bind("orderDetail_id") Integer orderDetail_id);

    @SqlQuery("SELECT order_status FROM orders WHERE id = :order_id")
    String getOrderStatusByOrderId(@Bind("order_id") Integer order_id);

    @SqlQuery(value = "SELECT " +
            "od.id AS id, " +
            "od.order_id AS order_id, " +
            "od.product_id AS product_id, " +
            "od.quantity AS quantity, " +
            "od.total AS total, " +
            "p.name AS product_name, " +
            "i.url AS image_url " + // Lấy thêm URL hình ảnh
            "FROM order_detail od " +
            "JOIN products p ON od.product_id = p.id " +
            "LEFT JOIN image i ON p.image_id = i.id " +
            "WHERE od.order_id = :order_id")
    OrderDetail getOrderDetailById(@Bind("order_id") Integer order_id);

//    @SqlQuery(value ="\n" +
//            "select\n" +
//            "    od.id, od.orderId, od.productId, od.quantity,\n" +
//            "    od.total,\n" +
//            "    p.name as productName,\n" +
//            "    i.url as imageUrl\n" +
//            "from order_detail as od\n" +
//            "    inner join products as p\n" +
//            "        on p.id = od.productId\n" +
//            "    inner join options as ops\n" +
//            "        on od.optionId = ops.id\n" +
//            "    inner join image as i\n" +
//            "        on i.id = p.primaryImage\n" +
//            "\n" +
//            "where od.orderId = :orderId \n"
//    )
//    List<OrderDetail> getOrderDetailByOrderId(@Bind("orderId") Integer orderId );
// OrderDetailDAO.java
    @SqlQuery("""
        SELECT 
            od.id AS id,
            od.order_id AS order_id,
            od.product_id AS product_id,
            od.quantity AS quantity,
            od.total AS total,
            p.name AS product_name,
            i.url AS image_url
        FROM order_detail od
            INNER JOIN products p ON p.id = od.product_id
            LEFT JOIN image i ON i.id = p.image_id
        WHERE od.order_id = :order_id
    """)
    @RegisterConstructorMapper(OrderDetail.class)
    List<OrderDetail> getOrderDetailByOrderId(@Bind("order_id") Integer order_id);

}

