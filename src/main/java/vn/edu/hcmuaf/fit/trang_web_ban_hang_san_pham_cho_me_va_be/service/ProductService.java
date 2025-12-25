package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ProductDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;

import java.util.List;

public class ProductService {
    Jdbi jdbi;
    private ProductDao productDao;

    public ProductService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.productDao = jdbi.onDemand(ProductDao.class);
    }
    public Product getProductById(int product_id){
        return jdbi.withExtension(ProductDao.class, dao -> dao.getProductById(product_id));
    }
    public List<Product> getProductsByCategory(int category_id){
        return jdbi.withExtension(ProductDao.class, dao -> dao.getProductsByCategory(category_id));
    }

    public Product getProductByIdAndOptionId(int product_id, int option_id){
        return jdbi.withExtension(ProductDao.class, dao -> dao.getProductByIdAndOptionId(product_id,option_id));
    }

    public List<Product> getAllProducts() {
        List<Product> products = jdbi.withExtension(ProductDao.class, dao -> dao.getAllProducts());

        return products;
    }

    public Integer getMinimumPriceForProduct(int product_id) {
        return productDao.getMinimumPriceForProduct(product_id);
    }

    public Integer getPriceForOption(int option_id) {
        return productDao.getPriceForOption(option_id);
    }

    public Product addProduct(Product product) {

        String generatedSku = "HKS-" + System.currentTimeMillis();
        product.setSku(generatedSku);

        int product_id = productDao.addProduct(
                product.getName(), product.getDescription(),
                product.getIs_active(), product.getCategory_id(),
                product.getBrand_id(), product.getImage_id(), product.getSku(),
                product.getHeight(), product.getLength(), product.getWidth(), product.getWeight()

        );


        if (product_id > 0) {
            product.setId(product_id);
            return product;
        }
//        if (rowsAffected > 0) {
//            return productDao.getProductById(product.getId());  // Trả về sản phẩm đã được thêm vào
//        }

        return null;
    }

    public List<Product> searchProducts(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        String keyword = "%" + name + "%";
        return productDao.searchProducts(keyword);
    }

    public List<Product> searchProducts(String name, int limit, int offset) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        String keyword = "%" + name + "%";
        return productDao.searchProducts(keyword, limit, offset);
    }
    public List<Product> getTopProductsByCategory(Integer category_id, Integer limit) {
        if (category_id <= 0 || limit <= 0) {
            throw new IllegalArgumentException("Bad request");
        } else {
            return productDao.getTopProductsByCategoryId(category_id, limit);
        }
    }

    public Boolean increaseNoOfViews(Integer product_id) {
        return productDao.increaseNoOfViews(product_id);
    }

    public Boolean increaseNoOfSold(Integer product_id, Integer quantity) {
        return productDao.increaseNoOfSold(product_id, quantity );
    }

    public List<Product> getTop10(){
        return productDao.getTopProducts();
    }
// option_variant_id đặt tên biến để gọi DAO
    public List<Product> filterProduct(Integer category_id, List<Integer> option_variant_id,
            Integer minPrice, Integer maxPrice) {
        return productDao.filterProduct(category_id, option_variant_id,
                minPrice, maxPrice);
    }

    public List<Product> filterProductsByPrice(Integer categoryId,
                                              Integer minPrice, Integer maxPrice) {
        return productDao.filterProductByPrice(categoryId, minPrice, maxPrice);
    }

    public List<Product> suggestProducts( ) {
        return productDao.suggestProduct();
    }

    public boolean updateProduct(Integer id, String name, String description, String sku,
                                 Integer categoryId, Integer brandId, Integer primaryImage,
                                 Integer height, Integer length, Integer width, Integer weight) {
        return jdbi.withExtension(ProductDao.class, dao ->
                dao.updateProduct(id, name, description, sku, categoryId, brandId, primaryImage,
                        height, length, width, weight));
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService(DBConnection.getJdbi());
//        System.out.println(productService.suggestProducts( ).size());
//        System.out.println(productService.getProductByIdAndOptionId(211, 85 ));
        System.out.println(productService.getProductById(1 ));

    }
}
