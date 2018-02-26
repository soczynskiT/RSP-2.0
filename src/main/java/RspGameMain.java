import gamecode.GameStructureController;
import gamecode.GameLogicController;
import players.user.UserController;
import players.user.UserPlayer;

public class RspGameMain {
    public static void main(String args[]) {

        final UserController userController = new UserController(new UserPlayer("temp"));
        userController.createFirstPlayer();

        final GameLogicController logicController = new GameLogicController();
        final GameStructureController structureController = new GameStructureController(userController, logicController);

        structureController.mainMenu();
    }
}
