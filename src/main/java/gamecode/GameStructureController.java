package gamecode;

import players.computer.CompPlayer;
import players.user.UserController;

import java.util.Scanner;

public class GameStructureController {

    final private Scanner mainMenuScan = new Scanner(System.in);
    final private Scanner settingsMenuScan = new Scanner(System.in);
    final private Scanner compSetMenuScan = new Scanner(System.in);
    final private Scanner exitMenuScan = new Scanner(System.in);
    final private Scanner chanceModifierScanner = new Scanner(System.in);
    final private Scanner newPLayerNameScan = new Scanner(System.in);
    final private Scanner existingPlayerNameScan = new Scanner(System.in);
    final private Scanner gameLogicScanner = new Scanner(System.in);
    final private CompPlayer compPlayer;
    final private UserController userController;
    final private GameLogicController logicController;

    public GameStructureController(CompPlayer compPlayer, UserController userController, GameLogicController logicController) {
        this.compPlayer = compPlayer;
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
                logicController.playNewGame(compPlayer, userController, gameLogicScanner);
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
                userController.createNewPlayer(newPLayerNameScan);
                settingsMenu();
                break;
            case "2":
                userController.changeCurrentPlayer(existingPlayerNameScan);
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
        System.out.println("[1] ~~ Modify computer chances to win.");
        System.out.println("[2] ~~ Modify computer chances to lose.");
        System.out.println("[3] ~~ Modify computer chances to draw.");
        System.out.println("[4] ~~ Restore default settings.");
        System.out.println("[5] ~~ Check current settings in %.");
        System.out.println("[6] ~~ Back to settings menu.");

        final String compSetMenuChoice = compSetMenuScan.nextLine();
        switch (compSetMenuChoice) {
            case "1":
                compPlayer.setWinChancesModifier(chanceModifierScanner);
                compSettingsMenu();
                break;
            case "2":
                compPlayer.setLoseChancesModifier(chanceModifierScanner);
                compSettingsMenu();
                break;
            case "3":
                compPlayer.setDrawChancesModifier(chanceModifierScanner);
                compSettingsMenu();
                break;
            case "4":
                compPlayer.setDefaultChancesModifiersValues();
                System.out.println("Default settings restored.\n");
                compSettingsMenu();
                break;
            case "5":
                compPlayer.displayCurrentChanceSettings();
                compSettingsMenu();
                break;
            case "6":
                settingsMenu();
                break;
            default:
                System.out.println("! Wrong choice, try again !");
                compSettingsMenu();
        }
    }
}
