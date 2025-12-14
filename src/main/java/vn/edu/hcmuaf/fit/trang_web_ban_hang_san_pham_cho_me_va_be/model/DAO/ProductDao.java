package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.DAO;


import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Product.class)
public interface ProductDao {
    @SqlQuery(value = " " +
            "SELECT p.id as id, p.name as name, p.description as description, " +
            "            p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  " +
            "            p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  " +
            "            p.category_id as category_id, p.image_id as image_id, " +
            "            ov.id as option_id ,ov.price as price, " +
            "            ov.stock as stock,  " +
            "            img.url as imageUrl  " +
            "            FROM products as p " +
            "                INNER JOIN categories as c on c.id = p.category_id " +
            "                INNER JOIN `option_variant` as ov on ov.product_id = p.id " +
            "                inner join image as img on p.image_id = img.id " +
            "            WHERE c.id= :category_id and ov.price = ( " +
            "                    SELECT MIN(price) " +
            "                    FROM option_variant as ov " +
            "                    WHERE p.id = ov.product_id and ov.stock > 0 " +
            "                       and p.is_active = true );")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("category_id") int category_id);

    @SqlQuery(value = "SELECT p.id as id, \n" +
            "p.name as name, \n" +
            "p.description as description, \n" +
            "p.sku as sku, \n" +
            "p.is_active as is_activer" +
            "p.brand_id as bran_id, \n" +
            "p.no_of_views as no_of_views, \n" +
            "p.no_of_sold as no_of_sold, \n" +
            "p.category_id as category_id, \n" +
            "p.image_id as image_id, \n" +
            "ov.id as option_id, \n" +
            "ov.price as price, \n" +
            "ov.stock as stock, \n" +
            "img.url as image_url, \n"
//            +"p.height as height, p.length as length, p.width as width, p.weight as weight"
            +
            "FROM products as p \n"+
            "inner join categories as c on c.id = p.category_id \n" +
            "inner join option_variant as ov on ov.product_id = p.id \n" +
            "inner join image as img on p.image_id = img.id \n"
            +
            "WHERE p.id = :id" +
            "and ov.price = (select min(price)\n"+
                            "from option_variant as ov \n" +
                            "where p.id =ov.product_id \n" +
                            "and ov.stock >0)"
    )
    @RegisterConstructorMapper(Product.class)
    Product getProductById(@Bind("id") int id);

    @SqlQuery(value = "SELECT p.id as id, p.name as name, p.description as description, " +
                    "       p.is_active as is_active " +
                    "       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold, " +
                    "       p.image_id as image_id, " +
                    "       ov.id as option_id ,ov.price as price, " +
                    "       ov.stock as stock, " +
                    "       img.url as image_url " +
                    "FROM products as p " +
                    "         INNER JOIN categories as c on c.id = p.category_id " +
                    "         INNER JOIN `option_variant` as ov on ov.product_id = p.id " +
                    "         inner join image as img on p.image_id = img.id " +
                    "WHERE p.id= :product_id and ov.id =:option_id ;")

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("product_id") int product_id,
                                      @Bind("option_id") int option_id);

    @SqlQuery(value = "SELECT p.id, p.name, p.sku, p.description, p.is_active, " +
            "p.category_id, c.name as category_name, "+
            "p.brand_id, p.no_of_views, p.no_of_sold" +
            "p.image_id, img.url as image_url"+
            "ov.price, ov.stock, ov.id as option_id"
            +
            "FROM products p"+
            "INNER JOIN categories c on p.category_id = c.id"+
            "INNER jOIN option_variant ov on p.id = ov.product_id"+
            "INNER JOIN image as img on img.id = p.image_id WHERE p.is_active = true and stock >0;"
    )
    @RegisterConstructorMapper(Product.class)
    List<Product> getAllProduct();

    @SqlQuery("SELECT price FROM option_variant where product_id = :product_id and stock >0 order by price asc limit 1")
    Integer getMinimumPriceForProduct(@Bind("product_id") int product_id);

    @SqlQuery("SELECT price FROM option_variant WHERE id = :product_id AND stock >0")
    Integer getPriceForOption(@Bind("option_id") int option_id);

    @SqlUpdate("INSERT INTO products (name, description,is_active, category_id, brand_id, no_of_views, no_of_sold, image_id, sku, height, length, width, weight )"
        + "VALUES (:name, :description,COALESCE(:is_active, 1), :category_id, brand_id,0, 0, COALESCE(:image_id, null),:sku, :height, :length, :width, :weight)"
    )
    @GetGeneratedKeys
    int addProduct(@Bind("name") String name,
                   @Bind("description") String description,
                   @Bind("is_active") Boolean is_active,
                   @Bind("category_id") Integer category_id,
                   @Bind("brand_id") Integer brand_id,
                   @Bind("image_id") Integer image_id,
                   @Bind("sku") String sku,
                   @Bind("height") Integer height,
                   @Bind("length") Integer length,
                   @Bind("width") Integer width,
                   @Bind("weight") Integer weight
    );

    @SqlQuery("""
            SELECT  p.id as id, p.name as name, p.image_id as image, i.url as iamge_url, ov.price as price
            FROM product p
                LEFT JOIN option_variant ov on p.id = ov.product_id
                LEFT JOIN image i on p.image_id = i.id
                WHERE LOWER(p.name) LIKE CONCAT('%',LOWER(:name), '%')
            """)
    @RegisterConstructorMapper(Product.class)
    List<Product> searchProducts(@Bind("name") String name);

    //comment have another query in local backup file
    @SqlQuery("""
                 SELECT p.id as id, p.name as name, p.image_id as image, i.url as image_url. o.price as price           
                FROM products p
                LEFT JOIN option_variant ov on p.id = ov.product_id
                LEFT JOIN image i on p.image_id = i.id
                WHERE LOWER(p.name) LIKE CONCAT('%' , LOWER(:name), '%')
                LIMIT :limit OFFSET :offset
    """)
    List<Product> searchProduct(@Bind("name") String name, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery(value = "SELECT p.id as id, "+
            "p.name as name, "+
            "p.description as description, "+
            "p.sku as sku, "+
            "p.is_active as is_active, "+
            "p.brand_id as brand_id, "+
            "p.no_of_views as no_of_views, "+
            "p.no_of_sold as no_of_sold, "+
            "p.category_id as category_id, "+
            "p.image_id as image_id, "+
            "ov.id as option_id, "+
            "ov.price as price, "+
            "ov.stock as stock, "+
            "i.url as image_url"+
            "FROM products as p "+
            "INNER JOIN categories as c on c.id = p.category_id"+
            "INNER JOIN option_variant as ov ov.product_id = p.id"+
            "INNER JOIN image as i on p.image_id = i.id "+
            "WHERE c.id = :category_id "+
            "and ov.price = (SELECT MIN(price) "+
                            "FROM option_variant as ov "+
                            "WHERE p.id = ov. product_id "+
                            "and ov.stock >0" +
                            "and p.is_active = true)"+
            "order by p.no_of_views desc, p.no_of_sold desc"+
            "limit 3"
    )
    public List<Product> getTopProductByCategoryId(@Bind("category_id") int category_id, @Bind("limit") Integer limit);

    @SqlUpdate("UPDATE products SET is_active = false WHERE id = :id")
    boolean deactivateProduct(@Bind("id") int id);

    @SqlQuery(value = """
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  
                       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  
                       p.category_id as category_id, p.image_id as image_id,
                       ov.id as option_id, ov.price as price,
                       ov.stock as stock,  
                       img.url as image_url,
                       v.id as variant_id,
                       v.value as variant_value,
                       v.name as variant_name,
                       p.height as height,
                       p.length as length,
                       p.width as width,
                       p.weight as weight
                FROM products as p 
                    INNER JOIN categories as c on c.id = p.category_id 
                    INNER JOIN option_variant as ov on ov.product_id = p.id 
                    INNER JOIN image as img on p.image_id = img.id
                    INNER JOIN variant as v on v.category_id = c.id 
                WHERE p.id = :id 
                  AND ov.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ov 
                        WHERE p.id = ov.product_id AND ov.stock > 0
                  );
            """)
    @RegisterConstructorMapper(Product.class)
    Product editProduct(@Bind("id") int id);

    @SqlQuery("""
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  
                       p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  
                       p.category_id as category_id, p.image_id as image_id,
                       ov.id as option_id, ov.price as price,
                       ov.stock as stock,  
                       img.url as image_url,
                       v.id as variant_id,
                       v.value as variant_value,
                       v.name as variant_name 
                FROM products as p 
                    INNER JOIN categories as c on c.id = p.category_id 
                    INNER JOIN option_variant as ov on ov.product_id = p.id 
                    INNER JOIN image as img on p.image_id = img.id 
                    INNER JOIN variant as v on v.category_id = c.id 
                WHERE p.id = :id 
                  AND ov.price = (
                        SELECT MIN(price) 
                        FROM option_variant as ov 
                        WHERE p.id = ov.product_id AND ov.stock > 0
                );
            """)
    @RegisterConstructorMapper(OptionVariant.class)
    List<OptionVariant> getVariants(@Bind("id") int id);

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
            "       ov.id         as option_id,\n" +
            "       ov.price      as price,\n" +
            "       ov.stock      as stock\n" +
            "FROM products as p\n" +
            "         INNER JOIN option_variant as ov on ov.product_id = p.id\n" +
            "         inner join image as img on p.image_id = img.id\n" +
            "where p.is_active = true\n" +
            "  and ov.price = (SELECT MIN(price)\n" +
            "                   FROM option_variant as ov\n" +
            "                   WHERE p.id = ov.product_id\n" +
            "                     and ov.stock > 0)\n" +
            "order by p.no_of_sold desc, p.no_of_views desc\n" +
            "limit 12")
    List<Product> getTopProducts();

    @SqlQuery(
            """
                    SELECT\s
                            p.id           AS id,
                            p.name         AS name,
                            p.description  AS description,
                            p.sku          AS sku,
                            p.is_active     AS is_active,
                            p.brand_id      AS brand_id,
                            p.no_of_views    AS no_of_views,
                            p.no_of_sold     AS no_of_sold,
                            p.category_id   AS category_id,
                            p.image_id AS image_id,
                            ov.id         AS option_id,
                            ov.price      AS price,
                            ov.stock      AS stock,
                            img.url        AS image_url
                       \s
                        FROM products AS p
                            INNER JOIN option_variant AS ov ON p.id = opt.product_id
                            INNER JOIN image AS img ON img.id = p.image_id
                            INNER JOIN categories AS c ON p.categoryId = c.id
                            INNER JOIN variant AS v ON v.category_id = v.option_id 
                       \s
                        WHERE\s
                            v.variantValueId IN (<options_id>)
                            AND c.id = :category_id
                            AND ov.price >= COALESCE(:minPrice, 0)
                            AND ov.price <= COALESCE(:maxPrice, 999999999)
                       \s""")

    public List<Product> filterProduct(
            @Bind("category_id") int category_id,
            @BindList(value = "option_id") List<Integer> option_id,
            @Bind("minPrice") @Nullable Integer minPrice,
            @Bind("maxPrice") @Nullable Integer maxPrice);

    @SqlQuery("""
            SELECT
                    p.id           AS id,
                    p.name         AS name,
                    p.description  AS description,
                    p.sku          AS sku,
                    p.is_active     AS is_active,
                    p.brand_id      AS brand_id,
                    p.no_of_views    AS no_of_views,
                    p.no_of_sold     AS no_of_sold,
                    p.category_id   AS category_id,
                    p.image_id AS image_id,
                    ov.id         AS option_id,
                    ov.price      AS price,
                    ov.stock      AS stock,
                    img.url        AS image_url
                    FROM products AS p
                    INNER JOIN option_variant AS ov ON p.id = ov.product_id
                    INNER JOIN image AS img ON img.id = p.image_id
                    INNER JOIN categories AS c ON p.category_id = c.id
                    
                    WHERE 
                    c.id = :category_id
                    AND ov.price >= COALESCE(:minPrice, 0)
                    AND ov.price <= COALESCE(:maxPrice, 999999999)
    """)

    public List<Product> filterProductByPrice(
            @Bind("category_id") int category_id,
            @Bind("minPrice") @Nullable Integer minPrice,
            @Bind("maxPrice") @Nullable Integer maxPrice);

    @SqlQuery("SELECT p.id as id, \n" +
            "                                          p.name         as name, \n" +
            "                                           p.description  as description, \n" +
            "                                           p.sku          as sku,  \n" +
            "                                           p.is_active     as is_active, \n" +
            "                                           p.brand_id      as brand_id, \n" +
            "                                           p.no_of_views    as no_of_views, \n" +
            "                                           p.no_of_sold     as no_of_sold,  \n" +
            "                                          p.category_id   as category_id,  \n" +
            "                                           p.image_id as image_id,  \n" +
            "                                           ov.id         as optionId, \n" +
            "                                           ov.price      as price, \n" +
            "                                           ov.stock      as stock,  \n" +
            "                                           img.url        as image_url\n" +
            "                        FROM products as p  \n" +
            "                         INNER JOIN option_variant as opt ON p.id = ov.product_id\n" +
            "                        INNER JOIN image as img on img.id = p.image_id\n" +
            "                        INNER JOIN categories as c on p.category_id = c.id\n" +
            "                        INNER JOIN variant_value as v ON c.id = v.category_id\n" +
            "                         ORDER BY p.no_of_views DESC, p.no_of_sold DESC\n" +
            "                         LIMIT 3")

    public List<Product> suggestProduct();

    @SqlUpdate("""
            UPDATE products 
            SET name = :name,
                description = :description,
                sku = :sku,
                category_id = :category_id,
                brand_id = :brand_id,
                image_id = COALESCE(:image_id, image_id),
                height = :height,
                length = :length,
                width = :width,
                weight = :weight
            WHERE id = :id
            """)
    boolean updateProduct(@Bind("id") Integer id,
                          @Bind("name") String name,
                          @Bind("description") String description,
                          @Bind("sku") String sku,
                          @Bind("categoryId") Integer category_id,
                          @Bind("brandId") Integer brand_id,
                          @Bind("primaryImage") Integer image_id,
                          @Bind("height") Integer height,
                          @Bind("length") Integer length,
                          @Bind("width") Integer width,
                          @Bind("weight") Integer weight);

}
