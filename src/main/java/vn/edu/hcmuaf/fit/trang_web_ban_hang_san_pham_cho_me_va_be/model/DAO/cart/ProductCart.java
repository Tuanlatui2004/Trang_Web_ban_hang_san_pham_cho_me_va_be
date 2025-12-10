package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.DAO.cart;

public class ProductCart {
    Integer productId;
    Integer optionId;
    String name;
    String imageUrl;
    Integer quantity;
    Integer price;
    Integer stock;
    Integer height;
    Integer length;
    Integer width;
    Integer weight;




    public ProductCart(Product product) {
        this.productId = product.getId();
        this.optionId = product.getOptionId();
        this.name = product.getName();
        this.imageUrl = product.getImageUrl();
        this.quantity = 1;
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.height = product.getHeight();
        this.length = product.getLength();
        this.width = product.getWidth();
        this.weight = product.getWeight();


    }

}
