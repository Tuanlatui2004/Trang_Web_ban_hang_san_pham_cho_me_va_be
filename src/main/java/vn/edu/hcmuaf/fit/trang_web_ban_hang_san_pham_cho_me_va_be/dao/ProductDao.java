package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Variant;


import java.util.List;


@RegisterConstructorMapper(Product.class)
public interface ProductDao {

    @SqlQuery(value = " " +
            "SELECT p.id as id, p.name as name, p.description as description, " +
            "            p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  " +
            "            p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  " +
            "            p.category_id as category_id, p.image_id as image_id, " +
            "            ops.id as option_id ,ops.price as price, " +
            "            ops.stock as stock,  " +
            "            img.url as image_url  " +
            "            FROM products as p " +
            "                INNER JOIN categories as cate on cate.id = p.category_id " +
            "                INNER JOIN `option_variant` as ops on ops.product_id = p.id " +
            "                inner join image as img on p.image_id = img.id " +
            "            WHERE cate.id= :category_id and ops.price = ( " +
            "                    SELECT MIN(price) " +
            "                    FROM option_variant as ops " +
            "                    WHERE p.id = ops.product_id and ops.stock > 0 " +
            "                       and p.is_active = true );")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("category_id") int category_id);


//    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, " +
//            "p.sku as sku, p.is_active as is_active, p.brand_id as brand_id, " +
//            "p.no_of_views as no_of_views, p.no_of_sold as no_of_sold, " +
//            "p.category_id as category_id, p.image_id as image_id " +
//            "FROM products p WHERE p.id = :id")


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.description  as description,\n" +
            "       p.sku          as sku,\n" +
            "       p.is_active    as is_active,\n" +
            "       p.brand_id     as brand_id,\n" +
            "       p.no_of_views  as no_of_views,\n" +
            "       p.no_of_sold   as no_of_sold,\n" +
            "       p.category_id  as category_id,\n" +
            "       p.image_id     as image_id,\n" +
            "       ops.id         as option_id,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock,\n" +
            "       img.url        as image_url, \n" +
            "FROM products as p\n" +
            "         INNER JOIN categories as cate on cate.id = p.category_id\n" +
            "         INNER JOIN `option_variant` as ops on ops.product_id = p.id\n" +
            "         inner join image as img on p.image_id = img.id\n" +
            "WHERE p.id = :id" +
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM option_variant as ops\n" +
            "                   WHERE p.id = ops.product_id\n" +
            "                     and ops.stock > 0)")
    @RegisterConstructorMapper(Product.class)
    Product getProductById(@Bind("id") int id);


    @SqlQuery(value =
            "SELECT p.id as id, p.name as name, p.description as description, " +
                    "         p.is_active as is_active, " +
                    "       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold, " +
                    "        p.image_id as image_id, " +
                    "       ops.id as option_id ,ops.price as price, " +
                    "       ops.stock as stock, " +
                    "       img.url as image_url " +
                    "FROM products as p " +
                    "         INNER JOIN categories as cate on cate.id = p.category_id " +
                    "         INNER JOIN `option_variant` as ops on ops.product_id = p.id " +
                    "         inner join image as img on p.image_id = img.id " +
                    "WHERE p.id= :product_id and ops.id =:option_id ;")

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("product_id") int product_id,
                                      @Bind("option_id") int option_id);

    @SqlQuery(value = "SELECT p.id, p.name, p.sku, p.description, p.is_active, " +
            "       p.category_id, cate.name as categoryName, " +
            "       p.brand_id, p.no_of_views, p.no_of_sold, " +
            "       p.image_id, img.url as image_url, " +
            "       ops.price, ops.stock, ops.id as option_id " +
            "FROM products p " +
            "         INNER JOIN categories cate ON cate.id = p.category_id " +
            "         INNER JOIN option_variant ops ON ops.product_id = p.id " +
            "         INNER JOIN image img ON img.id = p.image_id " +
            "WHERE p.is_active = true and stock > 0"
    )
    @RegisterConstructorMapper(Product.class)
    List<Product> getAllProducts();

    @SqlQuery("SELECT price FROM option_variant WHERE product_id = :product_id AND stock > 0 ORDER BY price ASC LIMIT 1")
    Integer getMinimumPriceForProduct(@Bind("product_id") int product_id);

    @SqlQuery("SELECT price FROM option_variant WHERE id = :option_id AND stock > 0")
    Integer getPriceForOption(@Bind("option_id") int option_id);


    @SqlUpdate("INSERT INTO products (name,description, is_active, category_id, brand_id, no_of_views, no_of_sold, image_id, sku) "
            + "VALUES (:name, :description,COALESCE(:is_active, 1), :category_id, :brand_id, 0, 0, COALESCE(:image_id, NULL), :sku)")
    @GetGeneratedKeys
    int addProduct(@Bind("name") String name,
                   @Bind("description") String description,
                   @Bind("is_active") Boolean is_active,
                   @Bind("category_id") Integer category_id,
                   @Bind("brand_id") Integer brand_id,
                   @Bind("image_id") Integer image_id,
                   @Bind("sku") String sku
    );

    @SqlQuery("""
               SELECT p.id AS id, p.name AS name, p.image_id AS image, i.url AS image_url, o.price AS price
               FROM products p
               LEFT JOIN option_variant o ON p.id = o.product_id
               LEFT JOIN image i ON p.image_id = i.id
               WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')
            """)
    @RegisterConstructorMapper(Product.class)
    List<Product> searchProducts(@Bind("name") String name);


    @SqlQuery(value = "SELECT p.id           as id, " +
            "       p.name         as name, " +
            "       p.description  as description, " +
            "       p.sku          as sku, " +
            "       p.is_active     as is_active, " +
            "       p.brand_id      as brand_id, " +
            "       p.no_of_views    as no_of_views, " +
            "       p.no_of_sold     as no_of_sold, " +
            "       p.category_id   as category_id, " +
            "       p.image_id as image_id, " +
            "       ops.id         as option_id, " +
            "       ops.price      as price, " +
            "       ops.stock      as stock, " +
            "       img.url        as image_url " +
            "FROM products as p " +
            "         INNER JOIN categories as cate on cate.id = p.category_id " +
            "         INNER JOIN `option_variant` as ops on ops.product_id = p.id " +
            "         inner join image as img on p.image_id = img.id " +
            "WHERE cate.id = :category_id " +
            "  and ops.price = (SELECT MIN(price) " +
            "                   FROM option_variant as ops " +
            "                   WHERE p.id = ops.product_id " +
            "                     and ops.stock > 0" +
            "                     and p.is_active = true ) " +
            "order by p.no_of_views desc , p.no_of_sold desc " +
            "limit 3")
    public List<Product> getTopProductsByCategoryId(@Bind("category_id") int category_id, @Bind("limit") Integer limit);


    @SqlUpdate("UPDATE products SET is_active = false WHERE id = :id")
    boolean deactivateProduct(@Bind("id") int id);

    @SqlQuery(value = """
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  
                       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  
                       p.category_id as category_id, p.image_id as image_id,
                       ops.id as option_id, ops.price as price,
                       ops.stock as stock,  
                       img.url as image_url,
                       v.id as variant_id,
                       v.value as variantValue,
                       v.name as variantName
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.category_id 
                    INNER JOIN `option_variant` as ops on ops.product_id = p.id 
                    INNER JOIN image as img on p.image_id = img.id
                    INNER JOIN variant as v on ops.id = v.option_id
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ops 
                        WHERE p.id = ops.product_id AND ops.stock > 0
                  );
            """)
    @RegisterConstructorMapper(Product.class)
    Product editProduct(@Bind("id") int id);


    @SqlQuery("""
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  
                       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  
                       p.category_id as category_id, p.image_id as image_id,
                       ops.id as option_id, ops.price as price,
                       ops.stock as stock,  
                       img.url as image_url,
                       v.id as variant_id,
                       v.value as variantValue,
                       v.name as variantName 
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.category_id 
                    INNER JOIN `option_variant` as ops on ops.product_id = p.id 
                    INNER JOIN image as img on p.image_id = img.id  
                    INNER JOIN variant as v on ops.id = v.option_id
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ops 
                        WHERE p.id = ops.product_id AND ops.stock > 0
                );
            """)
    @RegisterConstructorMapper(Variant.class)
    List<Variant> getVariants(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set no_of_views = no_of_views +1\n" +
            "where id = :id;")
    Boolean increaseNoOfViews(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set no_of_sold = no_of_sold + :quantity\n" +
            "where id = :id ;\n")
    Boolean increaseNoOfSold(@Bind("id") int id, @Bind("quantity") Integer quantity);


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.no_of_views    as no_of_views,\n" +
            "       p.no_of_sold     as no_of_sold,\n" +
            "       p.sku          as sku,\n" +
            "       p.is_active     as is_active,\n" +
            "       p.brand_id      as brand_id,\n" +
            "       p.category_id   as category_id,\n" +
            "       p.image_id as image_id,\n" +
            "       img.url        as image_url,\n" +
            //(0)cái này t giữ lại do thấy hợp lí, nếu jsp fix lỏ thì lấy cái dưới nữa
            "       ops.id         as option_id,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock\n" +
            //(1)"       sum(ops.stock) as stock\n" +
            "FROM products as p\n" +
            "         INNER JOIN `option_variant` as ops on ops.product_id = p.id\n" +
            "         inner join image as img on p.image_id = img.id\n" +
            "where p.is_active = true\n" +
            //(0) lấy theo số
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM option_variant as ops\n" +
            "                   WHERE p.id = ops.product_id\n" +
            "                     and ops.stock > 0)\n" +
            //(1) lấy theo số
//             "group by p.id, p.name, p.description,\n" +
//            "         p.sku, p.is_active, p.brand_id,\n" +
//            "         p.no_of_views, p.no_of_sold, p.category_id,\n" +
//            "         p.image_id, img.url\n" +
            "order by p.no_of_sold desc, p.no_of_views desc\n" +
            "limit 10\n")
    List<Product> getTopProducts();




}
