package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ProductDao;

public class ProductService {
    Jdbi jdbi;
    private ProductDao productDao;

    public ProductService(Jdbi jdbi, ProductDao productDao) {
        this.jdbi = jdbi;
        this.productDao = productDao;
    }


}
