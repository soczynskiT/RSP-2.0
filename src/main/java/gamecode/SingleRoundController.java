package gamecode;

import enums.Moves;
import enums.RoundResults;
import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserMoveReader;
import players.user.UserPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SingleRoundController {
    private final HashMap<HashSet<Moves>, Moves> roundResultMap;

    public SingleRoundController() {
        this.roundResultMap = resultsMap();
    }

    private HashMap<HashSet<Moves>, Moves> resultsMap() {
        final HashMap<HashSet<Moves>, Moves> resultsMap = new HashMap<>();
        resultsMap.put(new RoundMove(Moves.R, Moves.S).getRoundMove(), Moves.R);
        resultsMap.put(new RoundMove(Moves.R, Moves.SP).getRoundMove(), Moves.SP);
        resultsMap.put(new RoundMove(Moves.R, Moves.L).getRoundMove(), Moves.R);
        resultsMap.put(new RoundMove(Moves.R, Moves.P).getRoundMove(), Moves.P);
        resultsMap.put(new RoundMove(Moves.P, Moves.SP).getRoundMove(), Moves.P);
        resultsMap.put(new RoundMove(Moves.P, Moves.S).getRoundMove(), Moves.S);
        resultsMap.put(new RoundMove(Moves.P, Moves.L).getRoundMove(), Moves.L);
        resultsMap.put(new RoundMove(Moves.S, Moves.L).getRoundMove(), Moves.S);
        resultsMap.put(new RoundMove(Moves.S, Moves.SP).getRoundMove(), Moves.SP);
        resultsMap.put(new RoundMove(Moves.L, Moves.SP).getRoundMove(), Moves.L);
        return resultsMap;
    }

    public void playSingleRound(CompPlayer compPlayer, UserController userController, UserMoveReader userMoveReader) {
        final Moves playerMove = userController.getCurrentPlayer().makeMove(userMoveReader);
        compPlayer.setComputerChances(playerMove);
        final Moves compMove = compPlayer.makeMove();

        System.out.println(userController.getCurrentPlayer().getName() + " " + playerMove.getName() +
                " : " + compMove.getName() + " " + compPlayer.getName());
        final Moves winningMove = getWinningMove(playerMove, compMove);
        final RoundResults roundResult = getRoundResult(winningMove, playerMove);

        addOneRoundPointToRoundWinner(userController.getCurrentPlayer(), compPlayer, roundResult);
    }

    private Moves getWinningMove(Moves playerMove, Moves compMove) {
        final RoundMove roundMove = new RoundMove(playerMove, compMove);
        Moves winningMove = null;
        for (Map.Entry entry : roundResultMap.entrySet()) {
            if (roundMove.getRoundMove().equals(entry.getKey())) {
                winningMove = (Moves) entry.getValue();
            }
        }
        return winningMove;
    }

    private RoundResults getRoundResult(Moves winningMove, Moves playerMove) {
        RoundResults roundResult;
        if (winningMove != null) {
            if (playerMove.equals(winningMove)) {
                roundResult = RoundResults.WIN;
            } else {
                roundResult = RoundResults.LOSE;
            }
        } else {
            roundResult = RoundResults.TIE;
        }
        return roundResult;
    }

    private void addOneRoundPointToRoundWinner(UserPlayer player, CompPlayer compPlayer, RoundResults roundResult) {
        switch (roundResult) {
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
}