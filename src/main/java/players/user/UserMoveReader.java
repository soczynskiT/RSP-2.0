package players.user;

import players.MoveReader;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMoveReader implements MoveReader {
    private final Scanner scanner;

    public UserMoveReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readMove() {
        return scanner.nextLine();
    }

    @Override
    public int readNumber() {
        Scanner scan = new Scanner(System.in);
        boolean isEntryCorrect = true;
        int number = 0;
        do {
            try {
                number = scan.nextInt();
                isEntryCorrect = false;

            } catch (InputMismatchException e) {
                System.out.println("Wrong digit format, please try again.");
                scan.next();
            }
        }
        while (isEntryCorrect);
        return number;
    }
}
