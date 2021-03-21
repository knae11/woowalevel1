package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;

public abstract class Participant implements Playable {
    private final Name name;
    private final BettingMoney bettingMoney;
    private State state;

    public Participant(String name, Cards cards, BettingMoney bettingMoney) {
        this.name = new Name(name);
        this.bettingMoney = bettingMoney;
        this.state = new Hit(cards);
    }

    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public void takeCard(Card card) {
        state = state.takeCard(card);
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    @Override
    public boolean isBurst() {
        return state.isBurst();
    }

    @Override
    public Score finalScore() {
        return state.calculateScore();
    }

    @Override
    public Cards getCards() {
        return state.getCards();
    }

    @Override
    public int sizeOfCards() {
        return state.size();
    }

    @Override
    public void stay() {
        this.state = new Stay(state.getCards());
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public State getState() {
        return state;
    }

}
