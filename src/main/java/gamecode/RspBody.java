package gamecode;

import player.computer.CompPlayer;
import player.user.UserProcessor;

import java.util.Scanner;

public class RspBody {
    final private CompPlayer compPlayer = new CompPlayer();
    final private Scanner scanner = new Scanner(System.in);
    final private UserProcessor processor;
    final private RspLogic rspLogic;

    public RspBody(UserProcessor processor, RspLogic rspLogic) {
        this.processor = processor;
        this.rspLogic = rspLogic;
    }

    public void mainMenu() {
        System.out.println("[1] ~~ Start new game.");
        System.out.println("[2] ~~ Settings.");
        System.out.println("[3] ~~ Current player stats.");
        System.out.println("[X] ~~ Exit.");

        final String menuChoice = scanner.nextLine().toUpperCase();
        switch (menuChoice) {
            case "1":
                rspLogic.playNewGame(compPlayer, processor);
                mainMenu();
                break;
            case "2":
                settingsMenu();
                break;
            case "3":
                System.out.println("In progress - shows stats of current player");
                break;
            case "X":
                System.out.println("Exit game. Are you sure [Y]/[N] ?");
                gameExit();
                break;
            default:
                System.out.println("Wrong choice, try again.\n");
                mainMenu();
        }
    }

    private void settingsMenu() {
        System.out.println("[1] ~~ Create new player.");
        System.out.println("[2] ~~ Change current player.");
        System.out.println("[3] ~~ Modify game difficulty.");
        System.out.println("[4] ~~ Back to main menu.");

        final int settingsChoice = scanner.nextInt();
        switch (settingsChoice) {
            case 1:
                System.out.println("Please enter new player name.");
                processor.createNewPlayer();
                System.out.println("New player created - " + processor.getCurrentPlayer().getName());
                settingsMenu();
                break;
            case 2:
                System.out.println("In progress - switch between players in database");
                break;
            case 3:
                System.out.println("In progress - modifies comp chances");
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Wrong choice, try again.");
                settingsMenu();
        }
    }

    private void gameExit() {
        final String exitConfirmation = scanner.nextLine().toUpperCase();
        switch (exitConfirmation) {
            case "Y":
                break;
            case "N":
                mainMenu();
                break;
            default:
                System.out.println("Wrong choice, try again");
                gameExit();
        }
    }

    /*
    public void compSetMenu() {
        System.out.println("1 - Modify computer chances to lose");
        System.out.println("2 - Modify computer chances to win");
        System.out.println("3 - Modify computer chances to draw");
        System.out.println("4 - Check current settings in %");
        System.out.println("5 - Back to settings menu");

        final int compSetMenuChoice = scanner.nextInt();
        switch (compSetMenuChoice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                settingsMenu();
                break;
            default:
                System.out.println("Wrong choice, try again.");
                compSetMenu();

        }
    }*/
}
