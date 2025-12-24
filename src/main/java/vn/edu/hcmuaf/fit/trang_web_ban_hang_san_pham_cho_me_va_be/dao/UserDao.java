package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.User;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(User.class)
//chưa thêm mapper, đang còn thiếu
public interface UserDao {

    @SqlQuery("SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone, " +
            "u.password, u.avatar_id, u.salt, i.url as avatar_url, u.status, u.confirmationToken, u.facebook_id,  u.need_refresh , " +
            "r.id as 'role.id', r.role_type as 'role.role_type', r.name as 'role.name', " +
            "r.description as 'role.description', r.isActive as 'role.is_active' " +
            "FROM user u " +
            "LEFT JOIN image i ON u.avatar_id = i.id " +
            "LEFT JOIN user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN role r ON ur.role_id = r.id")
    List<User> getAllUsers();

    @SqlQuery(value = "select u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone,\n" +
            "        i.url as avatar_url, u.status, u.confirmationToken, u.password, u.salt, u.facebook_id,  u.need_refresh ,\n" +
            "        r.id as role_id, r.role_type as role_role_type, r.name as role_name, r.description as role_description, r.is_active as role_is_active\n" +
            "from user as u\n" +
            "    left join image as i on u.avatar_id = i.id\n" +
            "    left join user_role as ur on u.id = ur.user_id\n" +
            "    left join role as r on ur.role_id = r.id\n" +
            "where u.id  = :id")
    User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone, " +
            "u.password, u.salt, u.avatar_id, u.status, u.confirmationToken, u.facebook_id, u.need_refresh ," +
            "i.url as avatar_url, " +
            "r.id as role_id, r.role_type as role_role_type, r.name as role_name, " +
            "r.description as role_description, r.is_active as role_is_active " +
            "FROM user as u " +
            "LEFT JOIN image as i ON u.avatar_id = i.id " +
            "LEFT JOIN user_role as ur ON u.id = ur.user_id " +
            "LEFT JOIN role as r ON ur.role_id = r.id " +
            "WHERE u.email = :email")
    @RegisterRowMapper(UserWithRoleMapper.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery(value = "SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone,\n" +
            "        i.url as avatar_url, u.status, u.confirmationToken, u.password, u.salt, u.facebook_id , u.need_refresh , \n" +
            "        r.id as role_id, r.role_type as role_role_type, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "FROM user as u\n" +
            "    left join image as i on u.avatar_id = i.id\n" +
            "    left join user_role as ur on u.id = ur.user_id\n" +
            "    left join role as r on ur.role_id = r.id\n" +
            "WHERE u.confirmationToken = :token")
    User getUserByConfirmationToken(@Bind("token") String token);

    @SqlQuery("""
    SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone,
           i.url AS avatarUrl,
           u.status, u.confirmationToken, u.password, u.salt, u.facebook_id,
           r.id as role_id, r.role_type as role_role_type, r.name as role_name, 
           r.description as role_description, r.is_active as role_isActive
    FROM user u
    LEFT JOIN image i ON u.avatar_id = i.id
    LEFT JOIN user_role ur ON u.id = ur.user_id
    LEFT JOIN role r ON ur.role_id = r.id
    where r.role_type = "USER"
    """)
    List<User> getCustomers();



    @SqlQuery("""
            SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone,   \s
             u.password, u.salt, u.avatar_id, u.status, u.confirmationToken, u.facebook_id , u.need_refresh ,   \s
             i.url as avatar_url,   \s
             r.id as role_id, r.role_type as role_role_type, r.name as role_name,   \s
             r.description as role_description, r.is_active as role_isActive   \s
             FROM user as u   \s
             LEFT JOIN image as i ON u.avatar_id = i.id   \s
             LEFT JOIN user_role as ur ON u.id = ur.user_id   \s
             LEFT JOIN role as r ON ur.role_id = r.id   \s
      
            where r.role_type  != "USER"
    """)
    @RegisterRowMapper(UserWithRoleMapper.class)
    List<User> getMembers();





    @SqlUpdate("INSERT INTO user (fullName, displayName, email, password, salt, status, confirmationToken, facebook_id) " +
            "VALUES (:fullName, :displayName, :email, :password, :salt, 'PENDING', :confirmationToken, :facebook_id)")
    @GetGeneratedKeys("id")
    Integer createUser(@Bind("fullName") String fullName,
                       @Bind("displayName") String displayName,
                       @Bind("email") String email,
                       @Bind("password") String password,
                       @Bind("salt") String salt,
                       @Bind("confirmationToken") String confirmationToken,
                       @Bind("facebook_id") String facebook_id);

    @SqlUpdate("INSERT INTO user (fullName, displayName, email, password, role_id, salt, status, confirmationToken, facebook_id) " +
            "VALUES (:fullName, :displayName, :email, :password, :role_id, :salt, 'PENDING', :confirmationToken, :facebook_id)")
    @GetGeneratedKeys("id")
    String createUserWithRole(@Bind("fullName") String fullName,
                              @Bind("displayName") String displayName,
                              @Bind("email") String email,
                              @Bind("password") String password,
                              @Bind("roleId") Integer role_id,
                              @Bind("salt") String salt,
                              @Bind("confirmationToken") String confirmationToken,
                              @Bind("facebook_id") String facebook_id);

    @SqlUpdate("UPDATE user SET status = :status WHERE id = :id")
    void updateUserStatus(@Bind("id") Integer id, @Bind("status") String status);

    @SqlUpdate("UPDATE user SET status = :status WHERE confirmationToken = :token")
    void updateUserStatusByToken(@Bind("token") String token, @Bind("status") String status);

    @SqlUpdate("UPDATE user SET fullname = :fullname, email = :email, password = :password WHERE id = :id")
    void updateUser(@Bind("id") Integer id,
                    @Bind("fullname") String fullname,
                    @Bind("email") String email,
                    @Bind("password") String password);

    @SqlUpdate("UPDATE user SET roleId = :roleId WHERE id = :id")
    void updateUserRole(@Bind("id") Integer id, @Bind("roleId") Integer roleId);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUser(@Bind("id") Integer id);

    @SqlUpdate("UPDATE user SET password = :password, salt = :salt WHERE id = :id")
    int updatePassword(@Bind("id") Integer id, @Bind("password") String password, @Bind("salt") String salt);

    @SqlQuery(value = "SELECT u.id, u.fullName, u.displayName, u.dOB, u.gender, u.email, u.phone,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.password, u.salt, u.facebook_id, u.needRefresh , \n" +
            "        r.id as role_id, r.role_type as role_role_type, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "FROM user as u\n" +
            "    left join image as i on u.avatar_id = i.id\n" +
            "    left join user_role as ur on u.id = ur.user_id\n" +
            "    left join role as r on ur.roleId = r.id\n" +
            "WHERE u.id = :id")
    User getPasswordByuser_id(@Bind("id") Integer user_id);

    @SqlQuery("SELECT url FROM image WHERE id = :avatar_id")
    String getAvatarUrlById(@Bind("avatar_id") Integer avatar_id);

    @SqlUpdate(value ="UPDATE user\n" +
            "SET avatar_id = :avatar_id " +
            "where id = :user_id")
    Boolean updateAvatar(@Bind("user_id") Integer user_id, @Bind("avatar_id") Integer avatar_id);

    @SqlUpdate(value = "UPDATE user\n" +
            "SET\n" +
            "    fullName = :fullName ,\n" +
            "    displayName = :displayName,\n" +
            "    dOB = :dOB, " +
            "    gender = :gender,\n" +
            "    phone = :phone " +
            "where id = :user_id")
    Boolean updateUser(
            @Bind("user_id") Integer user_id,
            @Bind("fullName") String fullName,
            @Bind("displayName") String displayName,
            @Bind("dOB") LocalDate dOB,
            @Bind("gender") String gender,
            @Bind("phone") String phone
    );

    // Helper method to get default user role
    @SqlQuery("SELECT id, role_type, name, description, isActive FROM role WHERE role_type = 'USER' LIMIT 1")
    Role getDefaultUserRole();

    // Helper method to get role by name
    @SqlQuery("SELECT id, role_type, name, description, isActive FROM role WHERE name = :name")
    Role getRoleByName(@Bind("name") String name);

    // Helper method to get role by type
    @SqlQuery("SELECT id, role_type, name, description, isActive FROM role WHERE role_type = :role_type")
    Role getRoleByType(@Bind("role_type") String role_type);

    @SqlUpdate("INSERT INTO user (fullName, displayName, email, password, salt, status, confirmationToken, facebook_id) " +
            "VALUES (:fullName, :displayName, :email, :password, :salt, 'ACTIVE', :confirmationToken, :facebook_id)")
    @GetGeneratedKeys("id")
    Integer createUserWithActiveStatus(@Bind("fullName") String fullName,
                                       @Bind("displayName") String displayName,
                                       @Bind("email") String email,
                                       @Bind("password") String password,
                                       @Bind("salt") String salt,
                                       @Bind("confirmationToken") String confirmationToken,
                                       @Bind("facebook_id") String facebook_id);

    @SqlUpdate(value = """
            UPDATE user
            set need_refresh = :need_refresh
            where id = :user_id
            """)
    Boolean updateNeedRefresh(@Bind("user_id") Integer user_id, @Bind("need_refresh") Boolean need_refresh);



}

