import gamecode.RspBody;
import gamecode.RspLogic;
import player.user.UserProcessor;
import player.user.UserPlayer;

import java.util.Scanner;

public class RspGameMain {
    public static void main(String args[]) {
        final Scanner scanner = new Scanner(System.in);
        final RspLogic rspLogic = new RspLogic();

        /*Creating first player*/
        System.out.println("Enter your name:");
        boolean isCorrect = true;
        String name = "";
        while (isCorrect) {
            final String entry = scanner.nextLine();
            if (!entry.equals("")) {
                name = entry;
                isCorrect = false;
            } else {
                System.out.println("Wrong name, try again.");
            }
        }
        final UserPlayer newPlayer = new UserPlayer(name);
        final UserProcessor processor = new UserProcessor(newPlayer);
        processor.addPlayerToDatabase(newPlayer);

        final RspBody rspBody = new RspBody(processor, rspLogic);
        rspBody.mainMenu();
    }
}
