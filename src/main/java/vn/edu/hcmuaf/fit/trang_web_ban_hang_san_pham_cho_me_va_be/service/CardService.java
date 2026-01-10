package vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.service;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.connection.DBConnection;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.dao.CardDao;
import vn.edu.hcmuaf.fit.trang_web_ban_hang_san_pham_cho_me_va_be.model.Card;

import java.time.LocalDate;
import java.util.List;

public class CardService {
    CardDao cardDao;

    public CardService(Jdbi jdbi) {
        this.cardDao = jdbi.onDemand(CardDao.class);
    }

    public List<Card> getCartByUserId(Integer user_id) {
        return cardDao.getCardByUserId(user_id);
    }

    public Card getCardById(Integer card_id) {
        return cardDao.getCardById(card_id);
    }

    public Boolean addCard(Card card) {
        return cardDao.addCard(
                card.getUserId(),
                card.getDuration(),
                card.getType(),
                card.getDefault()

        );
    }


    public static void main(String[] args) {
        CardService cardService = new CardService(DBConnection.getJdbi());
        Card card = new Card();
        card.setUserId(35);
        card.setDuration(LocalDate.of(2027,1,1));
        card.setType("visa");
        card.setDefault(true);

        System.out.println(cardService.addCard(card));
//        System.out.println(cardService.getCartByUserId(1));
//        System.out.println(cardService.getCardById(1));
//        System.out.println(cardService.getCardById(1));
//        System.out.println(cardService.getCardById(1));
    }

}

