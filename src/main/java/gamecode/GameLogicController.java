package gamecode;

import enums.Moves;
import enums.RoundResults;
import players.computer.CompPlayer;
import players.user.UserPlayer;
import players.user.UserController;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GameLogicController {
    final private Scanner gameLogicScanner = new Scanner(System.in);
    final private RoundResults[][] resultLabel = new RoundResults[3][3];

    public void createResultLabel() {
        resultLabel[0][0] = RoundResults.TIE;
        resultLabel[0][1] = RoundResults.WIN;
        resultLabel[0][2] = RoundResults.LOSE;
        resultLabel[1][0] = RoundResults.LOSE;
        resultLabel[1][1] = RoundResults.TIE;
        resultLabel[1][2] = RoundResults.WIN;
        resultLabel[2][0] = RoundResults.WIN;
        resultLabel[2][1] = RoundResults.LOSE;
        resultLabel[2][2] = RoundResults.TIE;
    }

    public void playNewGame(CompPlayer compPlayer, UserController userController) {
        System.out.println("Pleas enter rounds - to win number: (min 1 - max 10):");

        final int numberOfRoundsToWin = setNumberOfRoundsToWIn();

        while (userController.getCurrentPlayer().getRoundPoints() < numberOfRoundsToWin && compPlayer.getRoundPoints() < numberOfRoundsToWin) {
            playSingleRound(compPlayer, userController);
            showCurrentGameScore(userController.getCurrentPlayer(), compPlayer);
        }
        if (compPlayer.getRoundPoints() == numberOfRoundsToWin) {
            System.out.println("This time " + compPlayer.getName() + " wins.\n");
            userController.addOneLostPointToStats();
        } else {
            System.out.println("Congratulations, you have won " + userController.getCurrentPlayer().getName() + ".\n");
            userController.addOneWinPointToStats();
        }

        clearCurrentGameRoundPoints(compPlayer, userController.getCurrentPlayer());
    }

    private int setNumberOfRoundsToWIn() {
        try {
            final int roundsToWinNumber = gameLogicScanner.nextInt();
            if (roundsToWinNumber > 0 && roundsToWinNumber <= 10) {
                return roundsToWinNumber;
            } else {
                System.out.println("Incorrect digit, please try again.");
                return setNumberOfRoundsToWIn();
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect digit format, try again.");
            gameLogicScanner.nextLine();
            return setNumberOfRoundsToWIn();
        }
    }

    private void playSingleRound(CompPlayer compPlayer, UserController userController) {

        final Moves playerMove = userController.getCurrentPlayer().makeMove();

        compPlayer.setComputerChances(playerMove);
        final Moves compMove = compPlayer.makeMove();

        System.out.println(userController.getCurrentPlayer().getName() + " " + playerMove.getName() + " : " + compMove.getName() + " " + compPlayer.getName());

        addOneRoundPointToRoundWinner(userController.getCurrentPlayer(), playerMove, compPlayer, compMove);
    }

    private void addOneRoundPointToRoundWinner(UserPlayer player, Moves playerMove, CompPlayer compPlayer, Moves compMove) {
        RoundResults value = resultLabel[playerMove.getResultLabelPosition()][compMove.getResultLabelPosition()];
        switch (value) {
            case TIE:
                break;
            case WIN:
                player.addRoundPoint();
                break;
            case LOSE:
                compPlayer.addRoundPoint();
                break;
        }
    }

    private void showCurrentGameScore(UserPlayer player, CompPlayer compPlayer) {
        System.out.println("\n" + player.getName() + " " + player.getRoundPoints() + " : " + compPlayer.getRoundPoints() + " " + compPlayer.getName() + "\n");
    }

    private void clearCurrentGameRoundPoints(CompPlayer compPlayer, UserPlayer player) {
        player.setRoundPoints(0);
        compPlayer.setRoundPoints(0);
    }
}