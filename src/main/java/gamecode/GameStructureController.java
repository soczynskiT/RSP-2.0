package gamecode;

import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserMoveReader;

public class GameStructureController {
    private final UserMoveReader userMoveReader;
    private final CompPlayer compPlayer;
    private final UserController userController;
    private final NewGameController logicController;

    public GameStructureController(CompPlayer compPlayer, UserController userController, NewGameController logicController, UserMoveReader userMoveReader) {
        this.compPlayer = compPlayer;
        this.userController = userController;
        this.logicController = logicController;
        this.userMoveReader = userMoveReader;
    }

    public void mainMenu() {
        System.out.println("[1] ~~ Start new game.");
        System.out.println("[2] ~~ Settings.");
        System.out.println("[3] ~~ Current players stats.");
        System.out.println("[X] ~~ Exit.");

        final String mainMenuChoice = userMoveReader.readMove().toUpperCase();
        switch (mainMenuChoice) {
            case "1":
                logicController.playNewGame(compPlayer, userController, userMoveReader);
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

        final String settingsChoice = userMoveReader.readMove();
        switch (settingsChoice) {
            case "1":
                System.out.println("Please enter new player name.");
                userController.createNewPlayer(userMoveReader);
                settingsMenu();
                break;
            case "2":
                userController.changeCurrentPlayer(userMoveReader);
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
        final String exitMenuChoice = userMoveReader.readMove().toUpperCase();
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

        final String compSetMenuChoice = userMoveReader.readMove();
        switch (compSetMenuChoice) {
            case "1":
                compPlayer.setWinChancesModifier(userMoveReader);
                compSettingsMenu();
                break;
            case "2":
                compPlayer.setLoseChancesModifier(userMoveReader);
                compSettingsMenu();
                break;
            case "3":
                compPlayer.setDrawChancesModifier(userMoveReader);
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
