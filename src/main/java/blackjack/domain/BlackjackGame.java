package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Playable;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.CardDeck;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Players players;
    private final Playable dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(final Map<String, String> participantsInfo, CardDeck cardDeck) {
        List<Player> playersValue = getPlayerList(participantsInfo);
        this.dealer = new Dealer();
        this.players = new Players(playersValue);
        this.cardDeck = cardDeck;
        initGame();
    }

    public BlackjackGame(final Playable dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
        initGame();
    }

    private List<Player> getPlayerList(final Map<String, String> participantsInfo) {
        List<Player> playersValue = new ArrayList<>();

        for (Map.Entry<String, String> info : participantsInfo.entrySet()) {
            playersValue.add(new Player(info.getKey(), info.getValue()));
        }

        return playersValue;
    }

    private void initGame() {
        dealer.takeCard(cardDeck.pop());
        dealer.takeCard(cardDeck.pop());

        players.takeTwoCards(cardDeck);
    }

    public ProcessDto getProcessDto() {
        return new ProcessDto(players, dealer);
    }

    public List<Playable> getPlayers() {
        return players.getUnmodifiableList();
    }

    public void turnForDealer() {
        if (dealer.isAbleToTake()) {
            turnFor(dealer);
            return;
        }
        dealer.stay();
    }

    public void turnFor(Playable participant) {
        participant.takeCard(cardDeck.pop());
    }

    public ResultDto getResultDto() {
        return new ResultDto(players.resultOfPlayers(dealer.finalScore()));
    }

}