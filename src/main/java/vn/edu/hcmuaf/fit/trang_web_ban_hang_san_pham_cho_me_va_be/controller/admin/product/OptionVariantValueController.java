package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.controller.admin.product;

import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service.VariantService;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Variant;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// chưa sửa xong

@WebServlet("/admin/addOptionVariantValue")
public class OptionVariantValueController  extends HttpServlet {
    private final VariantService variantService = new VariantService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            if (!"application/json".equals(request.getContentType())) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Invalid Content-Type, expected application/json", null));
                return;
            }

            StringBuilder payload = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }
            }
            System.out.println("Received payload: " + payload.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestData;
            try {
                requestData = objectMapper.readValue(payload.toString(), Map.class);
            } catch (Exception e) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Invalid JSON format", null));
                return;
            }

            Integer optionId = getIntegerValue(requestData.get("option_id"));
            List<Integer> variantValueIds = (List<Integer>) requestData.get("variantValueIds");

            if (optionId == null || variantValueIds == null || variantValueIds.isEmpty()) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Missing or invalid optionId or variantValueIds", null));
                return;
            }

            List<Variant> addedValues = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (Integer variantValueId : variantValueIds) {
                try {
                    System.out.println("Inserting optionId: " + optionId + ", variantValueId: " + variantValueId);
                    int result = variantService.addOptionVariantValue(optionId, variantValueId);

                    if (result > 0) {
                        Variant newOptionVariantValue = variantService.getOptionById(result);
                        if (newOptionVariantValue != null) {
                            addedValues.add(newOptionVariantValue);
                        }
                    } else {
                        errors.add("Failed to add OptionVariantValue for variantValueId: " + variantValueId);
                    }
                } catch (Exception e) {
                    errors.add("Error processing variantValueId " + variantValueId + ": " + e.getMessage());
                }
            }

            if (!addedValues.isEmpty()) {
                String message = errors.isEmpty()
                        ? "All OptionVariantValues added successfully."
                        : "Some OptionVariantValues added with errors: " + String.join(", ", errors);
                writeResponse(response, new ResponseWrapper<>(200, "success", message, addedValues));
            } else {
                writeResponse(response, new ResponseWrapper<>(500, "error", "Failed to add any OptionVariantValues. Errors: " + String.join(", ", errors), null));
            }

        } catch (Exception e) {
            writeResponse(response, new ResponseWrapper<>(500, "error", "An error occurred: " + e.getMessage(), null));
        }
    }

    private Integer getIntegerValue(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (value instanceof Long) {
            return ((Long) value).intValue();
        } else if (value instanceof Double) {
            return ((Double) value).intValue();
        }
        return null;
    }

    private void writeResponse(HttpServletResponse response, ResponseWrapper<?> responseWrapper) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseWrapper.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
    }
}
