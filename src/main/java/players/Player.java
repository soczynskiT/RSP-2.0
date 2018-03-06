package players;

import enums.Moves;

import java.util.Scanner;

public interface Player {

    Moves makeMove(Scanner scanner);

    void addRoundPoint();

}
