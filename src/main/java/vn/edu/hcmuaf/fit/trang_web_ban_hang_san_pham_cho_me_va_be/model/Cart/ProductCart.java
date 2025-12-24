package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;
public class ProductCart {
        Integer product_id;
        Integer option_id;
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
            this.product_id = product.getId();
            this.option_id = product.getOptionId();
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


        public Integer getProduct_id() {
            return product_id;
        }

        public void setProduct_id(Integer product_id) {
            this.product_id = product_id;
        }

        public Integer getOption_id() {
            return option_id;
        }

        public void setOption_id(Integer option_id) {
            this.option_id = option_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageURL(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "ProductCart{" +
                    "product_id=" + product_id +
                    ", option_id=" + option_id +
                    ", name='" + name + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", stock=" + stock +
                    ", height=" + height +
                    ", length=" + length +
                    ", width=" + width +
                    ", weight=" + weight +
                    '}';
        }
    }

