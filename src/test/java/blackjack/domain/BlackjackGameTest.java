package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Playable;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.FixedCardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    @DisplayName("초기 카드 2장 배부 확인")
    void create() {
        final Playable dealer = new Dealer();
        final Player player = new Player();
        List<Player> playersValue = Arrays.asList(player);
        final Players players = new Players(playersValue);

        new BlackjackGame(dealer, players, new FixedCardDeck());

        assertThat(dealer.sizeOfCards()).isEqualTo(2);
        assertThat(player.sizeOfCards()).isEqualTo(2);
    }

    @Test
    @DisplayName("추가 카드 배부 여부 확인")
    void giveCard() {
        final Playable dealer = new Dealer();
        final Player player = new Player();
        List<Player> playersValue = Arrays.asList(player);
        final Players players = new Players(playersValue);

        final BlackjackGame blackjackGame = new BlackjackGame(dealer, players, new FixedCardDeck());
        blackjackGame.turnFor(player);

        assertThat(player.sizeOfCards()).isEqualTo(3);


    }

}