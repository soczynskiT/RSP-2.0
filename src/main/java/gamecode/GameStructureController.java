package gamecode;

import players.computer.CompPlayer;
import players.user.UserController;

import java.util.Scanner;

public class GameStructureController {
    final private CompPlayer compPlayer = new CompPlayer();
    final private Scanner mainMenuScan = new Scanner(System.in);
    final private Scanner settingsMenuScan = new Scanner(System.in);
    final private Scanner compSetMenuScan = new Scanner(System.in);
    final private Scanner exitMenuScan = new Scanner(System.in);
    final private UserController userController;
    final private GameLogicController logicController;

    public GameStructureController(UserController userController, GameLogicController logicController) {
        this.userController = userController;
        this.logicController = logicController;
    }

    public void mainMenu() {
        System.out.println("[1] ~~ Start new game.");
        System.out.println("[2] ~~ Settings.");
        System.out.println("[3] ~~ Current players stats.");
        System.out.println("[X] ~~ Exit.");

        final String mainMenuChoice = mainMenuScan.nextLine().toUpperCase();
        switch (mainMenuChoice) {
            case "1":
                logicController.playNewGame(compPlayer, userController);
                mainMenu();
                break;
            case "2":
                settingsMenu();
                break;
            case "3":
                userController.getCurrentPlayerStats();
                mainMenu();
                break;
            case "X":
                System.out.println("Exit game. Are you sure [Y]/[N] ?");
                gameExit();
                break;
            default:
                System.out.println("! Wrong choice, please try again !\n");
                mainMenu();
        }
    }

    private void settingsMenu() {
        System.out.println("[1] ~~ Create new player.");
        System.out.println("[2] ~~ Change current player.");
        System.out.println("[3] ~~ Modify game difficulty.");
        System.out.println("[4] ~~ Back to main menu.");

        final String settingsChoice = settingsMenuScan.nextLine();
        switch (settingsChoice) {
            case "1":
                System.out.println("Please enter new player name.");
                userController.createNewPlayer();
                settingsMenu();
                break;
            case "2":
                userController.changeCurrentPlayer();
                settingsMenu();
                break;
            case "3":
                compSettingsMenu();
                break;
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("! Wrong choice, please try again !");
                settingsMenu();
        }
    }

    private void gameExit() {
        final String exitMenuChoice = exitMenuScan.nextLine().toUpperCase();
        switch (exitMenuChoice) {
            case "Y":
                break;
            case "N":
                mainMenu();
                break;
            default:
                System.out.println("! Wrong choice, please try again !");
                gameExit();
        }
    }

    private void compSettingsMenu() {
        System.out.println("[1] ~~ Modify computer chances to lose.");
        System.out.println("[2] ~~ Modify computer chances to win.");
        System.out.println("[3] ~~ Modify computer chances to draw.");
        System.out.println("[4] ~~ Check current settings in %.");
        System.out.println("[5] ~~ Back to settings menu.");

        final String compSetMenuChoice = compSetMenuScan.nextLine();
        switch (compSetMenuChoice) {
            case "1":
                System.out.println("Under construction");
                compSettingsMenu();
                break;
            case "2":
                System.out.println("Under construction");
                compSettingsMenu();
                break;
            case "3":
                System.out.println("Under construction");
                compSettingsMenu();
                break;
            case "4":
                System.out.println("Under construction");
                compSettingsMenu();
                break;
            case "5":
                settingsMenu();
                break;
            default:
                System.out.println("! Wrong choice, try again !");
                compSettingsMenu();
        }
    }
}
