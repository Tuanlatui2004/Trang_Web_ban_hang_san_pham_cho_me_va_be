package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.VariantDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Variant;

import java.util.List;

public class VariantService {
    private final VariantDao variantDao;
//cos khẳ năng lỗi, chưa fix
    public VariantService(Jdbi jdbi) {
        this.variantDao = jdbi.onDemand(VariantDao.class);
        jdbi.registerRowMapper(ConstructorMapper.factory(Variant.class));
    }

    public List<Variant> getAllVariants() {
        return variantDao.getAllVariants();
    }

    public Variant getVariantById(Integer id) {
        return variantDao.getVariantById(id);
    }

    public Variant createVariant(String name, Integer category_id) {
        int id = variantDao.createVariant(name, category_id);
        return variantDao.getVariantById(id);
    }



    public List<Variant> getVariantsByCategory(Integer category_id) {
        if (category_id == null) {
            // Trả về toàn bộ danh sách nếu không có categoryId
            return variantDao.getAllVariants();
        }
        return variantDao.getVariantsByCategoryId(category_id);
    }
    // haianh thêm bên DAO
    public List<Variant> getVariantValuesByVariantId(Integer id) {
        return variantDao.getVariantValuesByVariantId(id);
    }

//    public void updateVariant(Integer id, String name, Integer categoryId) {
//        variantDAO.updateVariant(id, name, categoryId);
//    }
//
//    public void deleteVariant(Integer id) {
//        variantDAO.deleteVariant(id);
//    }

    public int addOptionVariantValue(Integer optionId, Integer variantId) {
        return variantDao.addOptionVariantValue(optionId, variantId);
    }


    public Variant getOptionById(Integer id) {
        return variantDao.getOptionVariantValueId(id);
    }
}
