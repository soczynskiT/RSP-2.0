import gamecode.GameStructureController;
import gamecode.NewGameController;
import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserMoveReader;

public class RspGameMain {
    public static void main(String args[]) {
        final UserMoveReader userMoveReader = new UserMoveReader();

        final UserController userController = new UserController();
        userController.createFirstPlayer(userMoveReader);

        final CompPlayer compPlayer = new CompPlayer();
        compPlayer.setDefaultChancesModifiersValues();

        final NewGameController newGameController = new NewGameController();

        final GameStructureController structureController = new GameStructureController(compPlayer, userController, newGameController, userMoveReader);

        structureController.mainMenu();
    }
}