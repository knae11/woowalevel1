package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";

    private static final Score MINIMUM_SCORE_OF_TAKING_CARD = Score.of(16);

    public Dealer() {
        this(new Cards(Collections.emptyList()));
    }

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards, BettingMoney.ZERO);
    }

    @Override
    public boolean isAbleToTake() {
        final Score score = getState().calculateScore();
        return score.isEqualAndLessThan(MINIMUM_SCORE_OF_TAKING_CARD);
    }

}
