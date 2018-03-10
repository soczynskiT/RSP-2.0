package gamecode;

import players.computer.CompPlayer;
import players.user.UserMoveReader;
import players.user.UserPlayer;
import players.user.UserController;

public class NewGameController {

    private final SingleRoundController singleRoundController;

    public NewGameController() {
        this.singleRoundController = new SingleRoundController();
    }

    public void playNewGame(CompPlayer compPlayer, UserController userController, UserMoveReader userMoveReader) {
        System.out.println("Pleas enter rounds - to win number: (min 1 - max 10):");

        final int numberOfRoundsToWin = setNumberOfRoundsToWin(userMoveReader);
        while (userController.getCurrentPlayer().getRoundPoints() < numberOfRoundsToWin && compPlayer.getRoundPoints() < numberOfRoundsToWin) {
            singleRoundController.playSingleRound(compPlayer, userController, userMoveReader);
            System.out.println(showCurrentGameScore(userController.getCurrentPlayer(), compPlayer));
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

    private int setNumberOfRoundsToWin(UserMoveReader userMoveReader) {
        final int roundsToWinNumber = userMoveReader.readNumber();

        if (roundsToWinNumber > 0 && roundsToWinNumber <= 10) {
            return roundsToWinNumber;
        } else {
            System.out.println("Incorrect digit, please try again.");
            return setNumberOfRoundsToWin(userMoveReader);
        }
    }

    private String showCurrentGameScore(UserPlayer player, CompPlayer compPlayer) {
        return "\n" + player.getName() + " " + player.getRoundPoints() +
                " : " + compPlayer.getRoundPoints() + " " + compPlayer.getName() + "\n";
    }

    private void clearCurrentGameRoundPoints(CompPlayer compPlayer, UserPlayer player) {
        player.setRoundPoints(0);
        compPlayer.setRoundPoints(0);
    }
}