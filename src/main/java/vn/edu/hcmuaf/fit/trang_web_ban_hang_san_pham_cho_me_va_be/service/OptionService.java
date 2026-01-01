package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.OptionVariantDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.OptionVariant;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;
public class OptionService {
    private final OptionVariantDao optionDao;

    public OptionService(Jdbi jdbi) {
        this.optionDao = jdbi.onDemand(OptionVariantDao.class);
    }

    public int createOptions(Integer product_id, Integer price, Integer stock) {
        return optionDao.createOption(product_id, price, stock);
    }


    public OptionVariant getOptionById(Integer id) {
        return optionDao.getOptionById(id);
    }


    public Boolean updateStock(Integer id, Integer stock) {
        return optionDao.updateStock(id, stock);
    }


    public List<OptionVariant> getVariantByOptionId(List<Integer> optionIds) {
        return optionDao.getVariantByOptionId(optionIds);
    }


    public List<OptionVariant> getOptionsByProductId(Integer product_id) {
        return optionDao.getOptionsByProductId(product_id);
    }


    public boolean updateOption(Integer id, Integer price, Integer stock) {
        return optionDao.updateOption(id, price, stock);
    }


    public static void main(String[] args) {
        OptionService  optionService = new OptionService(DBConnection.getJdbi());

        System.out.println(optionService.getOptionsByProductId(1));



    }
}
