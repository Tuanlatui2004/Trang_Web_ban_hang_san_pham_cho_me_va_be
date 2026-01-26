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
            "            p.sku as sku, p.isActive as isActive, p.brandId as brandId,  " +
            "            p.noOfViews as noOfViews, p.noOfSold as noOfSold,  " +
            "            p.categoryId as categoryId, p.imageId as imageId, " +
            "            ops.id as optionId ,ops.price as price, " +
            "            ops.stock as stock,  " +
            "            img.url as image_url  " +
            "            FROM products as p " +
            "                INNER JOIN categories as cate on cate.id = p.categoryId " +
            "                INNER JOIN `option_variant` as ops on ops.productId = p.id " +
            "                inner join image as img on p.imageId = img.id " +
            "            WHERE cate.id= :categoryId and ops.price = ( " +
            "                    SELECT MIN(price) " +
            "                    FROM option_variant as ops " +
            "                    WHERE p.id = ops.productId and ops.stock > 0 " +
            "                       and p.isActive = true );")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("categoryId") int categoryId);


//    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, " +
//            "p.sku as sku, p.isActive as isActive, p.brandId as brandId, " +
//            "p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
//            "p.categoryId as categoryId, p.imageId as imageId " +
//            "FROM products p WHERE p.id = :id")


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.description  as description,\n" +
            "       p.sku          as sku,\n" +
            "       p.isActive    as isActive,\n" +
            "       p.brandId     as brandId,\n" +
            "       p.noOfViews  as noOfViews,\n" +
            "       p.noOfSold   as noOfSold,\n" +
            "       p.categoryId  as categoryId,\n" +
            "       p.imageId     as imageId,\n" +
            "       ops.id         as optionId,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock,\n" +
            "       img.url        as image_url \n" +
            "FROM products as p\n" +
            "         INNER JOIN categories as cate on cate.id = p.categoryId\n" +
            "         INNER JOIN `option_variant` as ops on ops.productId = p.id\n" +
            "         inner join image as img on p.imageId = img.id\n" +
            "WHERE p.id = :id" +
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM option_variant as ops\n" +
            "                   WHERE p.id = ops.productId\n" +
            "                     and ops.stock > 0)")
    @RegisterConstructorMapper(Product.class)
    Product getProductById(@Bind("id") int id);


    @SqlQuery(value =
            "SELECT p.id as id, p.name as name, p.description as description, " +
                    "         p.isActive as isActive, " +
                    "       p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
                    "        p.imageId as imageId, " +
                    "       ops.id as optionId ,ops.price as price, " +
                    "       ops.stock as stock, " +
                    "       img.url as image_url " +
                    "FROM products as p " +
                    "         INNER JOIN categories as cate on cate.id = p.categoryId " +
                    "         INNER JOIN `option_variant` as ops on ops.productId = p.id " +
                    "         inner join image as img on p.imageId = img.id " +
                    "WHERE p.id= :productId and ops.id =:optionId ;")

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("productId") int productId,
                                      @Bind("optionId") int optionId);

    @SqlQuery(value = "SELECT p.id, p.name, p.sku, p.description, p.isActive, " +
            "       p.categoryId, cate.name as categoryName, " +
            "       p.brandId, p.noOfViews, p.noOfSold, " +
            "       p.imageId, img.url as image_url, " +
            "       ops.price, ops.stock, ops.id as optionId " +
            "FROM products p " +
            "         INNER JOIN categories cate ON cate.id = p.categoryId " +
            "         INNER JOIN option_variant ops ON ops.productId = p.id " +
            "         INNER JOIN image img ON img.id = p.imageId " +
            "WHERE p.isActive = true and stock > 0"
    )
    @RegisterConstructorMapper(Product.class)
    List<Product> getAllProducts();

    @SqlQuery("SELECT price FROM option_variant WHERE productId = :productId AND stock > 0 ORDER BY price ASC LIMIT 1")
    Integer getMinimumPriceForProduct(@Bind("productId") int productId);

    @SqlQuery("SELECT price FROM option_variant WHERE id = :optionId AND stock > 0")
    Integer getPriceForOption(@Bind("optionId") int optionId);


    @SqlUpdate("INSERT INTO products (name,description, isActive, categoryId, brandId, noOfViews, noOfSold, imageId, sku) "
            + "VALUES (:name, :description,COALESCE(:isActive, 1), :categoryId, :brandId, 0, 0, COALESCE(:imageId, NULL), :sku)")
    @GetGeneratedKeys
    int addProduct(@Bind("name") String name,
                   @Bind("description") String description,
                   @Bind("isActive") Boolean isActive,
                   @Bind("categoryId") Integer categoryId,
                   @Bind("brandId") Integer brandId,
                   @Bind("imageId") Integer imageId,
                   @Bind("sku") String sku
    );

    @SqlQuery("""
               SELECT p.id AS id, p.name AS name, p.imageId AS image, i.url AS image_url, o.price AS price
               FROM products p
               LEFT JOIN option_variant o ON p.id = o.productId
               LEFT JOIN image i ON p.imageId = i.id
               WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')
            """)
    @RegisterConstructorMapper(Product.class)
    List<Product> searchProducts(@Bind("name") String name);


    @SqlQuery(value = "SELECT p.id           as id, " +
            "       p.name         as name, " +
            "       p.description  as description, " +
            "       p.sku          as sku, " +
            "       p.isActive     as isActive, " +
            "       p.brandId      as brandId, " +
            "       p.noOfViews    as noOfViews, " +
            "       p.noOfSold     as noOfSold, " +
            "       p.categoryId   as categoryId, " +
            "       p.imageId as imageId, " +
            "       ops.id         as optionId, " +
            "       ops.price      as price, " +
            "       ops.stock      as stock, " +
            "       img.url        as image_url " +
            "FROM products as p " +
            "         INNER JOIN categories as cate on cate.id = p.categoryId " +
            "         INNER JOIN `option_variant` as ops on ops.productId = p.id " +
            "         inner join image as img on p.imageId = img.id " +
            "WHERE cate.id = :categoryId " +
            "  and ops.price = (SELECT MIN(price) " +
            "                   FROM option_variant as ops " +
            "                   WHERE p.id = ops.productId " +
            "                     and ops.stock > 0" +
            "                     and p.isActive = true ) " +
            "order by p.noOfViews desc , p.noOfSold desc " +
            "limit 3")
    public List<Product> getTopProductsByCategoryId(@Bind("categoryId") int categoryId, @Bind("limit") Integer limit);


    @SqlUpdate("UPDATE products SET isActive = false WHERE id = :id")
    boolean deactivateProduct(@Bind("id") int id);

    @SqlQuery(value = """
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.isActive as isActive, p.brandId as brandId,  
                       p.noOfViews as noOfViews, p.noOfSold as noOfSold,  
                       p.categoryId as categoryId, p.imageId as imageId,
                       ops.id as optionId, ops.price as price,
                       ops.stock as stock,  
                       img.url as image_url,
                       v.id as variantId,
                       v.value as variantValue,
                       v.name as variantName
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.categoryId 
                    INNER JOIN `option_variant` as ops on ops.productId = p.id 
                    INNER JOIN image as img on p.imageId = img.id
                    INNER JOIN variant as v on ops.id = v.optionId
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ops 
                        WHERE p.id = ops.productId AND ops.stock > 0
                  );
            """)
    @RegisterConstructorMapper(Product.class)
    Product editProduct(@Bind("id") int id);


    @SqlQuery("""
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.isActive as isActive, p.brandId as brandId,  
                       p.noOfViews as noOfViews, p.noOfSold as noOfSold,  
                       p.categoryId as categoryId, p.imageId as imageId,
                       ops.id as optionId, ops.price as price,
                       ops.stock as stock,  
                       img.url as image_url,
                       v.id as variantId,
                       v.value as variantValue,
                       v.name as variantName 
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.categoryId 
                    INNER JOIN `option_variant` as ops on ops.productId = p.id 
                    INNER JOIN image as img on p.imageId = img.id  
                    INNER JOIN variant as v on ops.id = v.optionId
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ops 
                        WHERE p.id = ops.productId AND ops.stock > 0
                );
            """)
    @RegisterConstructorMapper(Variant.class)
    List<Variant> getVariants(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set noOfViews = noOfViews +1\n" +
            "where id = :id;")
    Boolean increaseNoOfViews(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set noOfSold = noOfSold + :quantity\n" +
            "where id = :id ;\n")
    Boolean increaseNoOfSold(@Bind("id") int id, @Bind("quantity") Integer quantity);


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.noOfViews    as noOfViews,\n" +
            "       p.noOfSold     as noOfSold,\n" +
            "       p.sku          as sku,\n" +
            "       p.isActive     as isActive,\n" +
            "       p.brandId      as brandId,\n" +
            "       p.categoryId   as categoryId,\n" +
            "       p.imageId as imageId,\n" +
            "       img.url        as image_url,\n" +
            //(0)cái này t giữ lại do thấy hợp lí, nếu jsp fix lỏ thì lấy cái dưới nữa
            "       ops.id         as optionId,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock\n" +
            //(1)"       sum(ops.stock) as stock\n" +
            "FROM products as p\n" +
            "         INNER JOIN `option_variant` as ops on ops.productId = p.id\n" +
            "         inner join image as img on p.imageId = img.id\n" +
            "where p.isActive = true\n" +
            //(0) lấy theo số
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM option_variant as ops\n" +
            "                   WHERE p.id = ops.productId\n" +
            "                     and ops.stock > 0)\n" +
            //(1) lấy theo số
//             "group by p.id, p.name, p.description,\n" +
//            "         p.sku, p.isActive, p.brandId,\n" +
//            "         p.noOfViews, p.noOfSold, p.categoryId,\n" +
//            "         p.imageId, img.url\n" +
            "order by p.noOfSold desc, p.noOfViews desc\n" +
            "limit 10\n")
    List<Product> getTopProducts();




}
