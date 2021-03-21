package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import java.util.Collections;

public class Player extends Participant {
    private static final String SAMPLE_NAME = "a";

    public Player() {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), BettingMoney.ZERO);
    }

    public Player(String name) {
        this(name, new Cards(Collections.emptyList()), BettingMoney.ZERO);
    }

    public Player(String name, String bettingMoney) {
        this(name, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(long bettingMoney) {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(String name, Cards cards) {
        this(name, cards, BettingMoney.ZERO);
    }

    public Player(String name, Cards cards, BettingMoney bettingMoney) {
        super(name, cards, bettingMoney);
    }

    @Override
    public boolean isAbleToTake() {
        return !super.getState().isBurst();
    }





}
