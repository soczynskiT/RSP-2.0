package gamecode;

import enums.Moves;
import enums.RoundResults;
import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserMoveReader;
import players.user.UserPlayer;

import java.util.HashMap;
import java.util.Map;

public class SingleRoundController {
    private final HashMap<RoundMove, RoundResults> roundResultMap;

    public SingleRoundController() {
        this.roundResultMap = generateResultsMap();
    }

    private HashMap<RoundMove, RoundResults> generateResultsMap() {
        final HashMap<RoundMove, RoundResults> resultsMap = new HashMap<>();
        resultsMap.put(new RoundMove(Moves.R, Moves.R), RoundResults.TIE);
        resultsMap.put(new RoundMove(Moves.R, Moves.P), RoundResults.LOSE);
        resultsMap.put(new RoundMove(Moves.R, Moves.S), RoundResults.WIN);
        resultsMap.put(new RoundMove(Moves.P, Moves.P), RoundResults.TIE);
        resultsMap.put(new RoundMove(Moves.P, Moves.R), RoundResults.WIN);
        resultsMap.put(new RoundMove(Moves.P, Moves.S), RoundResults.LOSE);
        resultsMap.put(new RoundMove(Moves.S, Moves.S), RoundResults.TIE);
        resultsMap.put(new RoundMove(Moves.S, Moves.R), RoundResults.LOSE);
        resultsMap.put(new RoundMove(Moves.S, Moves.P), RoundResults.WIN);
        return resultsMap;
    }

    public void playSingleRound(CompPlayer compPlayer, UserController userController, UserMoveReader userMoveReader) {

        final Moves playerMove = userController.getCurrentPlayer().makeMove(userMoveReader);
        compPlayer.setComputerChances(playerMove);
        final Moves compMove = compPlayer.makeMove();

        System.out.println(userController.getCurrentPlayer().getName() + " " + playerMove.getName() +
                " : " + compMove.getName() + " " + compPlayer.getName());
        addOneRoundPointToRoundWinner(userController.getCurrentPlayer(), playerMove, compPlayer, compMove);
    }

    private void addOneRoundPointToRoundWinner(UserPlayer player, Moves playerMove, CompPlayer compPlayer, Moves compMove) {
        final RoundResults value = getRoundResult(playerMove, compMove);

        switch (value) {
            case WIN:
                player.addRoundPoint();
                break;
            case LOSE:
                compPlayer.addRoundPoint();
                break;
            case TIE:
                break;
        }
    }

    private RoundResults getRoundResult(Moves playerMove, Moves compMove) {
        final RoundMove roundMove = new RoundMove(playerMove, compMove);
        RoundResults roundResult = null;
        for (Map.Entry entry : roundResultMap.entrySet()) {
            if (roundMove.equals(entry.getKey())) {
                roundResult = (RoundResults) entry.getValue();
            }
        }
        return roundResult;
    }
}