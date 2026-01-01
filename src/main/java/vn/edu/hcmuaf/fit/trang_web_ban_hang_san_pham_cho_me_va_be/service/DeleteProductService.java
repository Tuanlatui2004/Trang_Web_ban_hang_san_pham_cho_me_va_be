package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ProductDao;
import org.jdbi.v3.core.Jdbi;
public class DeleteProductService {
    private final ProductDao productDao;

    public DeleteProductService(Jdbi jdbi) {
        this.productDao = jdbi.onDemand(ProductDao.class);
    }

    public boolean  deactivateProduct(int productId) {
        return productDao.deactivateProduct(productId);
    }
}
