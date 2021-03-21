package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.gamer.Participant;
import blackjack.domain.utils.RandomCardDeck;
import blackjack.dto.ResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final BlackjackGame blackjackGame = new BlackjackGame(InputView.requestNameAndMoney(), new RandomCardDeck());
        OutputView.printInitialCards(blackjackGame.getProcessDto());

        simulate(blackjackGame);

        printFinalView(blackjackGame);
    }

    private static void simulate(BlackjackGame blackjackGame) {
        for (Participant player : blackjackGame.getPlayers()) {
            turnForPlayer(blackjackGame, player);
        }

        blackjackGame.turnForDealer();
        OutputView.printDealerGetCard();
    }

    private static void turnForPlayer(BlackjackGame blackjackGame, Participant player) {
        while (checkCondition(player)) {
            blackjackGame.turnFor(player);
            OutputView.printPlayerCards(player);
        }
    }

    private static boolean checkCondition(Participant player) {
        if (player.isAbleToTake()) {
            return requestTakingCard(player);
        }
        return false;
    }

    private static boolean requestTakingCard(Participant player) {
        if (InputView.requestOneMoreCard(player.getName())) {
            return true;
        }
        player.stay();
        return false;
    }

    private static void printFinalView(BlackjackGame blackjackGame) {
        OutputView.printCardsResult(blackjackGame.getProcessDto());

        final ResultDto resultDto = blackjackGame.getResultDto();
        OutputView.printOutcome(resultDto);
        OutputView.printProfit(resultDto);
    }

}
