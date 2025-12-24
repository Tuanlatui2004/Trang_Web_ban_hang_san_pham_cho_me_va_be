package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.contant.ERole;
public class Role {
    Integer id;
    ERole role_type;
    String name;
    String description;
    Boolean is_active;

    public Role(
            @ColumnName("id") Integer id,
            @ColumnName("role_type") ERole role_type,
            @ColumnName("name") String name,
            @ColumnName("description") @Nullable String description,
            @ColumnName("is_active") Boolean is_active
    ) {
        this.id = id;
        this.role_type = role_type;
        this.name = name;
        this.description = description;
        this.is_active = is_active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRole_type() {
        return role_type;
    }

    public void setRole_type(ERole role_type) {
        this.role_type = role_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_type=" + role_type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_active=" + is_active +
                '}';
    }
}
