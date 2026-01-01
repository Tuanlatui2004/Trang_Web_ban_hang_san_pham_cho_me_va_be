package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.OrderStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.PaymentStatus;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Order;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(Order.class)
public interface OrderDao {

    @SqlUpdate(value = "INSERT INTO orders(create_at, payment_status, order_status, user_id, address_id, card_id, isCOD, shipping_fee)" +
            "VALUE (" +
            "  :create_at , :payment_status, :order_status , :user_id, :address_id, :card_id, :isCOD, :shipping_fee" +
            " )")

    @GetGeneratedKeys
    Integer createOrder(
            @Bind("create_at")LocalDate create_at,
            @Bind("payment_status") PaymentStatus payment_status,
            @Bind("order_status") OrderStatus order_status,
            @Bind("user_id") Integer user_id,
            @Bind("address_id") Integer address_id,
            @Bind("card_id") Integer card_id,
            @Bind("isCOD") Boolean isCOD,
            @Bind("shipping_fee") Integer shipping_fee
    );



    @SqlQuery(value = """
            SELECT
                o.id,
                o.create_at,
                o.payment_status,
                o.order_status,
                o.user_id,
                o.address_id,
                o.card_id,
                o.isCOD,
                o.shipping_fee,
                SUM(od.quantity) AS quantity,
                (SUM(od.total) + o.shipping_fee) AS total,
                MIN(p.name) AS product_name,
                i.url AS product_image
            FROM
                orders o
                    INNER JOIN order_detail od ON o.id = od.order_id
                    INNER JOIN products p ON p.id = od.product_id
                    INNER JOIN image i ON i.id = p.image_id
            WHERE
                o.user_id = :user_id
            GROUP BY
                o.id, o.create_at, o.payment_status, o.order_status,
                o.user_id, o.address_id, o.card_id, o.isCOD, o.shipping_fee, i.url
            ORDER BY
                o.create_at DESC;
"""

    )
    List<Order> getOrdersByUserId(@Bind("userId") Integer user_id);


    @SqlQuery(value = "select\n" +
            "    o.id as id, o.create_at, o.payment_status, o.order_status,\n" +
            "    o.user_id, o.address_id, o.card_id, o.isCOD,  o.shipping_fee as  shipping_fee,\n " +
            " o.shipping_id as shipping_id , " +
            "    sum(od.total) as total\n" +
            "from orders as o inner join order_detail as od\n" +
            "                            on o.id = od.order_id\n" +
            "where o.user_id = :user_id and o.id = :order_id\n" +
            "group by\n" +
            "    o.id, o.create_at, o.payment_status, o.order_status,\n" +
            "    o.user_id, o.address_id, o.card_id, o.isCOD, o.shipping_fee " +
            " order by o.create_at DESC ")
    Order getOrderByIdAndUserId(@Bind("order_id") Integer order_id, @Bind("user_id") Integer user_id);


    @SqlQuery(value = "select\n" +
            "    o.id as id, o.create_at, o.payment_status, o.order_status,\n" +
            "    o.user_id, o.address_id, o.card_id, o.isCOD,  o.shipping_fee as  shipping_fee,\n" +
            "     o.shipping_id as shipping_id ," +
            "    sum(od.total) as total\n" +
            "from orders as o inner join order_detail as od\n" +
            "                            on o.id = od.order_id\n" +
            "where o.id = :order_id\n" +
            "group by\n" +
            "    o.id, o.create_at, o.payment_status, o.order_status,\n" +
            "    o.user_id, o.address_id, o.card_id, o.isCOD\n")
    Order getOrderById (@Bind("order_id") Integer order_id );






    @SqlQuery(value ="select \n" +
            "\to.id, o.create_at, o.payment_status, o.order_status, \n" +
            "  u.fullName as userName , o.shipping_fee as  shipping_fee,\n" +
            "  sum(od.total) as total\n" +
            "from orders as o\n" +
            "     inner join order_detail as od\n" +
            "           on o.id = od.order_id\n" +
            "     inner join user as u\n" +
            "           on u.id = o.user_id\n" +
            "group by\n" +
            "   o.id, o.create_at, o.payment_status,\n" +
            "   o.order_status,\n" +
            "   u.fullName \n" +
            "order by o.create_at desc")

    List<Order> getAllOrders();



    @SqlUpdate("UPDATE orders " +
            "SET order_status = :order_status "+
            "WHERE id = :order_id ")

    void updateOrderStatus(@Bind("order_id") Integer order_id, @Bind("order_status") OrderStatus order_status);


    @SqlUpdate("UPDATE orders " +
            "SET order_status = :order_status "+
            "WHERE shipping_id = :shipping_id ")

    boolean updateOrderStatusByShippingId(@Bind("shipping_id") String shipping_id, @Bind("order_status") OrderStatus order_status);





    @SqlUpdate("UPDATE orders " +
            "SET shipping_id = :shipping_id "+
            "WHERE id = :order_id ")

    void updateOrderShippingId(@Bind("order_id") Integer order_id, @Bind("shipping_id") String shipping_id);


}

