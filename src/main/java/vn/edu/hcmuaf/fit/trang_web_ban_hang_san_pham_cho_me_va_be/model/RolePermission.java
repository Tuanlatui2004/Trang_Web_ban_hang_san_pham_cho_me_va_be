package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class RolePermission {
    Integer id;
    Integer role_id;
    Integer permission_id;

    public RolePermission(
            @ColumnName("id") Integer id,
            @ColumnName("roleId") Integer roleId,
            @ColumnName("permissionId") Integer permissionId

    ) {
        this.id = id;
        this.role_id = role_id;
        this.permission_id = permission_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }
}
