package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Card;
import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(Card.class)
public interface CardDao {
    @SqlQuery(value = "SELECT *\n" +
            "FROM card\n" +
            "WHERE user_id = :user_id;")
    List<Card> getCardByUserId(@Bind("user_id") Integer user_id);

    @SqlQuery(value = "SELECT *\n" +
            "FROM card\n" +
            "WHERE id = :card_id;")
    Card getCardById(@Bind("card_id") Integer card_id);



    @SqlUpdate(value = "insert into card( user_id, duration, type, is_default) VALUE (\n" +
            "  :userId, :duration , :type , :isDefault  \n" +
            " );")
    Boolean addCard(
            @Bind("userId") Integer user_id,
            @Bind("duration") LocalDate duration,
            @Bind("type") String type,
            @Bind("isDefault") Boolean is_default
    );
}
