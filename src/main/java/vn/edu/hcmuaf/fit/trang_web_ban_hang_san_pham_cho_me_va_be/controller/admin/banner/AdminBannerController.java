package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin.banner;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.ImageDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Banner;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.BannerService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet(name = "AdminBannerController", urlPatterns = {"/admin/banner"})
public class AdminBannerController extends HttpServlet {
    private final BannerService bannerService = new BannerService(DBConnection.getJdbi());
    private final ImageDao imageDao = DBConnection.getJdbi().onDemand(ImageDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách banner
        List<Banner> banners = bannerService.getAllBanners();

        Map<String, String> imageMap = new HashMap<>();
        for (Banner b : banners) {
            try {
                String url = imageDao.getImageUrlById(Integer.parseInt(b.getImage_id()));
                imageMap.put(b.getImage_id(), url);
            } catch (NumberFormatException e) {
                imageMap.put(b.getImage_id(), ""); // fallback nếu imageId lỗi
            }
        }

        // Gửi dữ liệu sang JSP
        request.setAttribute("banners", banners);
        request.setAttribute("imageMap", imageMap);

        // Forward tới trang JSP
        request.getRequestDispatcher("/admin/banner.jsp").forward(request, response);
    }
}
