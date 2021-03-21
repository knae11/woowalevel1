package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stay extends Finished {
    public static final String EXCEPTION_STAY = "stay 하였으므로 카드를 더 받을 수 없습니다.";

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public State takeCard(Card card) {
        throw new IllegalArgumentException(EXCEPTION_STAY);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return false;
    }

}
