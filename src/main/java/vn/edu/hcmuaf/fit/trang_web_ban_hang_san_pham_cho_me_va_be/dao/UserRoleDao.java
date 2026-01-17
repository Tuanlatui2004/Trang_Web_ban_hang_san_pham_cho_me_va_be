package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.UserRole;

@RegisterConstructorMapper(UserRole.class)
public interface UserRoleDao {

    @SqlUpdate(value = """
            INSERT INTO user_role(userId, roleId)
            VALUES (:userId, :roleId)
            """)
    Integer addUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId);


    @SqlUpdate(value = """
            UPDATE user_role
            set roleId = :roleId
            where userId= :userId;
            """)
    Boolean updateUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId);
}
