package gamecode;

import enums.RoundResult;
import player.computer.CompPlayer;
import player.user.UserPlayer;
import player.user.UserProcessor;

import java.util.InputMismatchException;
import java.util.Scanner;


public class RspLogic {
    final private Scanner scanner = new Scanner(System.in);

    public void playNewGame(CompPlayer compPlayer, UserProcessor processor) {
        System.out.println("Pleas enter rounds-to win no: (1 - 10)");

        final int roundsToWinNo = setRoundsNo();

        while (processor.getCurrentPlayer().getRoundPoints() < roundsToWinNo && compPlayer.getRoundPoints() < roundsToWinNo) {
            gameSimpleRound(compPlayer, processor);
            showCurrentScore(processor.getCurrentPlayer(), compPlayer);
        }
        if (compPlayer.getRoundPoints() == roundsToWinNo) {
            System.out.println("This time " + compPlayer.getName() + " wins\n");
        } else {
            System.out.println("Congratulations, you have won " + processor.getCurrentPlayer().getName() + "\n");
        }
    }

    private void gameSimpleRound(CompPlayer compPlayer, UserProcessor processor) {

        final String playerMove = processor.getCurrentPlayer().move().toUpperCase();
        final String compMove = compPlayer.move().toUpperCase().toUpperCase();

        System.out.println(processor.getCurrentPlayer().getName() + " " + playerMove + " : " + compMove + " " + compPlayer.getName());

        addScoreToWinner(processor.getCurrentPlayer(), playerMove, compPlayer, compMove);
    }

    private int setRoundsNo() {
        try {
            int rounds = scanner.nextInt();
            if (rounds > 0 && rounds <= 10) {
                return rounds;
            } else {
                return setRoundsNo();
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return setRoundsNo();
        }
    }

    private void showCurrentScore(UserPlayer player, CompPlayer compPlayer) {
        System.out.println("\n" + player.getName() + " " + player.getRoundPoints() + " : " + compPlayer.getRoundPoints() + " " + compPlayer.getName() + "\n");
    }

    private void addScoreToWinner(UserPlayer player, String playerMove, CompPlayer compPlayer, String compMove) {
        switch (RoundResult.valueOf(playerMove + compMove)) {
            case ROCKROCK:
                System.out.println("It's a draw");
                break;
            case PAPERPAPER:
                System.out.println("It's a draw");
                break;
            case SCISSORSSCISSOR:
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
}
