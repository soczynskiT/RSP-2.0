package gamecode;

import enums.RoundResults;
import players.computer.CompPlayer;
import players.user.UserPlayer;
import players.user.UserController;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GameLogicController {
    final private Scanner gameLogicScanner = new Scanner(System.in);

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

        final String playerMove = userController.getCurrentPlayer().makeMove().toUpperCase();
        final String compMove = compPlayer.makeMove().toUpperCase();

        System.out.println(userController.getCurrentPlayer().getName() + " " + playerMove + " : " + compMove + " " + compPlayer.getName());

        addOneRoundPointToRoundWinner(userController.getCurrentPlayer(), playerMove, compPlayer, compMove);
    }

    private void addOneRoundPointToRoundWinner(UserPlayer player, String playerMove, CompPlayer compPlayer, String compMove) {
        switch (RoundResults.valueOf(playerMove + compMove)) {
            case ROCKROCK:
                System.out.println("It's a draw");
                break;
            case PAPERPAPER:
                System.out.println("It's a draw");
                break;
            case SCISSORSSCISSORS:
                System.out.println("It's a draw");
                break;
            case PAPERROCK:
                System.out.println(player.getName() + " WINS !!!");
                player.addRoundPoint();
                break;
            case ROCKSCISSORS:
                System.out.println(player.getName() + " WINS !!!");
                player.addRoundPoint();
                break;
            case SCISSORSPAPER:
                System.out.println(player.getName() + " WINS !!!");
                player.addRoundPoint();
                break;
            case ROCKPAPER:
                System.out.println(compPlayer.getName() + " WINS !!!");
                compPlayer.addRoundPoint();
                break;
            case PAPERSCISSORS:
                System.out.println(compPlayer.getName() + " WINS !!!");
                compPlayer.addRoundPoint();
                break;
            case SCISSORSROCK:
                System.out.println(compPlayer.getName() + " WINS !!!");
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