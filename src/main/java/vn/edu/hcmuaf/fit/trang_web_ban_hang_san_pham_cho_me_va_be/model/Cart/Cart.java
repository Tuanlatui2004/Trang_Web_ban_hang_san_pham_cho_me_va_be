package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Cart;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    Map<Integer, ProductCart> data = new HashMap<>();

    public boolean addProduct(Product product) {
        if (data.containsKey(product.getId())) {
            return update(product, data.get(product.getId()).getQuantity() + 1);
        }
        else {
            data.put(product.getId(), new ProductCart(product));
            return true;
        }

    }

    public List<ProductCart> getProducts() {
        return new ArrayList<>(data.values());
    }




    public boolean update(Product product, int quantity) {
        if (data.containsKey(product.getId())) {
            ProductCart productCart = data.get(product.getId());
            productCart.setQuantity(quantity);
            data.put(product.getId(), productCart);
            return true;
        }
        return false;
    }



    public void delete(Integer productId) {
        data.remove(productId);
    }


    public Map<Integer, ProductCart> getData() {
        return data;
    }

    public void setData(Map<Integer, ProductCart> data) {
        this.data = data;
    }
}
