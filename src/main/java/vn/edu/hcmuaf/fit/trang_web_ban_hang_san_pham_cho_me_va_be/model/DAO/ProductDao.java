package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.DAO;


import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterConstructorMapper(Product.class)
public interface ProductDao {
    @SqlQuery(value = " " +
            "SELECT p.id as id, p.name as name, p.description as description, " +
            "            p.sku as sku, p.is_active as is_active, p.brand_id as brand_id,  " +
            "            p.no_of_views as no_of_views, p.no_of_sold as no_of_sold,  " +
            "            p.category_id as categoryId, p.image_id as primaryImage, " +
            "            ov.id as option_id ,ov.price as price, " +
            "            ov.stock as stock,  " +
            "            img.url as imageUrl  " +
            "            FROM products as p " +
            "                INNER JOIN categories as cate on cate.id = p.category_id " +
            "                INNER JOIN `option_variant` as ov on ov.product_id = p.id " +
            "                inner join image as img on p.image_id = img.id " +
            "            WHERE cate.id= :category_id and ov.price = ( " +
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
            "inner join categories as cate on cate.id = p.category_id \n" +
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
                    "         INNER JOIN categories as cate on cate.id = p.category_id " +
                    "         INNER JOIN `option_variant` as ov on ov.product_id = p.id " +
                    "         inner join image as img on p.image_id = img.id " +
                    "WHERE p.id= :product_id and ov.id =:option_id ;")

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("productId") int productId,
                                      @Bind("optionId") int optionId);
}
