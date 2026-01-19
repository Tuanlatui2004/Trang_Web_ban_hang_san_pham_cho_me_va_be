package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Role;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Mapper.UserWithRoleMapper;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(User.class)
@RegisterConstructorMapper(Role.class)
//chưa thêm mapper, đang còn thiếu
public interface UserDao {
    // xem lại nghe
    @SqlQuery("SELECT * FROM user")
    List<User> getAllUsers();

    @SqlQuery(value = "select u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phoneNumber,\n" +
            "        i.url as avatar_url"+
            "from user as u\n" +
            "     left join image as i on u.avatarId = i.id\n" +
            "where u.id  = :id")
    User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);


    @SqlUpdate("INSERT INTO user (fullName, displayName, email, passwordUsername, salt, status, confirmationToken, facebookId) " +
            "VALUES (:fullName, :displayName, :email, :passwordUsername, :salt, 'PENDING', :confirmationToken, :facebookId)")
    @GetGeneratedKeys("id")
    Integer createUser(@Bind("fullName") String fullName,
                       @Bind("displayName") String displayName,
                       @Bind("email") String email,
                       @Bind("passwordUsername") String passwordUsername,
                       @Bind("salt") String salt,
                       @Bind("confirmationToken") String confirmationToken,
                       @Bind("facebookId") String facebookId);

    // mới tạo bởi NV
    @SqlUpdate("INSERT INTO user (fullName, displayName, email, passwordUsername, roleId, salt, status, confirmationToken, facebookId) " +
            "VALUES (:fullName, :displayName, :email, :passwordUsername, :roleId, :salt, 'PENDING', :confirmationToken, :facebookId)")
    @GetGeneratedKeys("id")
    String createUserWithRole(@Bind("fullName") String fullName,
                              @Bind("displayName") String displayName,
                              @Bind("email") String email,
                              @Bind("passwordUsername") String passwordUsername,
                              @Bind("roleId") Integer roleId,
                              @Bind("salt") String salt,
                              @Bind("confirmationToken") String confirmationToken,
                              @Bind("facebookId") String facebookId);


    @SqlUpdate("UPDATE user SET fullname = :fullname, email = :email, password = :password WHERE id = :id")
    void updateUser(@Bind("id") Integer id,
                    @Bind("fullname") String fullname,
                    @Bind("email") String email,
                    @Bind("password") String password);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUser(@Bind("id") Integer id);

    @SqlUpdate("UPDATE user SET password = :password, salt = :salt WHERE id = :id")
    int updatePassword(@Bind("id") Integer id, @Bind("password") String password, @Bind("salt") String salt);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    User getPasswordByUserId(@Bind("id") Integer userId);

    @SqlQuery("SELECT url FROM image WHERE id = :avatarId")
    String getAvatarUrlById(@Bind("avatarId") Integer avatarId);

    @SqlUpdate(value ="UPDATE user\n" +
            "SET avatarId = :avatarId " +
            "where id = :userId")
    Boolean updateAvatar(@Bind("userId") Integer userId, @Bind("avatarId") Integer avatarId);

    @SqlUpdate(value = "UPDATE user\n" +
            "SET\n" +
            "    fullName = :fullName ,\n" +
            "    displayName = :displayName,\n" +
            "    dOB = :dOB, " +
            "    gender = :gender,\n" +
            "    phoneNumber = :phoneNumber " +
            "where id = :userId")
    Boolean updateUser(
            @Bind("userId") Integer userId,
            @Bind("fullName") String fullName,
            @Bind("displayName") String displayName,
            @Bind("dOB") LocalDate dOB,
            @Bind("gender") String gender,
            @Bind("phoneNumber") String phoneNumber
    );

    @SqlUpdate(value = """
            UPDATE user
            set needRefresh = :needRefresh
            where id = :userId
            """)
    Boolean updateNeedRefresh(@Bind("userId") Integer userId, @Bind("needRefresh") Boolean needRefresh);

    @SqlQuery("SELECT id, roleType, name, description, isActive FROM role WHERE roleType = 'USER' LIMIT 1")
    Role getDefaultUserRole();

    @SqlQuery(value = "SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phoneNumber,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.password, u.salt, u.facebookId , u.needRefresh , \n" +
            "        r.id as role_id, r.roleType as role_roleType, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "FROM user as u\n" +
            "    left join image as i on u.avatarId = i.id\n" +
            "    left join user_role as ur on u.id = ur.userId\n" +
            "    left join role as r on ur.roleId = r.id\n" +
            "WHERE u.confirmationToken = :token")
    User getUserByConfirmationToken(@Bind("token") String token);

    @SqlUpdate("UPDATE user SET status = :status WHERE confirmationToken = :token")
    void updateUserStatusByToken(@Bind("token") String token, @Bind("status") String status);

    @SqlUpdate("INSERT INTO user (fullName, displayName, email, passwordUsername, salt, status, confirmationToken, facebookId) " +
            "VALUES (:fullName, :displayName, :email, :passwordUsername, :salt, 'ACTIVE', :confirmationToken, :facebookId)")
    @GetGeneratedKeys("id")
    Integer createUserWithActiveStatus(@Bind("fullName") String fullName,
                                       @Bind("displayName") String displayName,
                                       @Bind("email") String email,
                                       @Bind("passwordUsername") String passwordUsername,
                                       @Bind("salt") String salt,
                                       @Bind("confirmationToken") String confirmationToken,
                                       @Bind("facebookId") String facebookId);

    @SqlUpdate("UPDATE user SET status = :status WHERE id = :id")
    void updateUserStatus(@Bind("id") Integer id, @Bind("status") String status);
}

