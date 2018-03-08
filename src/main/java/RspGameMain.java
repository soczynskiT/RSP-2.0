import gamecode.GameStructureController;
import gamecode.GameLogicController;
import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserPlayer;

import java.util.Scanner;

public class RspGameMain {
    public static void main(String args[]) {


        final UserController userController = new UserController(new UserPlayer("temp"));
        userController.createFirstPlayer(new Scanner(System.in));

        final CompPlayer compPlayer = new CompPlayer();
        compPlayer.setDefaultChancesModifiersValues();

        final GameLogicController logicController = new GameLogicController();
        final GameStructureController structureController = new GameStructureController(compPlayer, userController, logicController);

        structureController.mainMenu();
    }
}
