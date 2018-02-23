package player.user;

import player.Player;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserProcessor {
    final private Scanner scanner = new Scanner(System.in);
    final private Set<Player> playersSet = new HashSet<>();
    private UserPlayer currentPlayer;

    public UserProcessor(UserPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void createNewPlayer() {
        final String name = scanner.nextLine();
        final UserPlayer tempPlayer = new UserPlayer(name);

        if (!name.equals("") && !name.equals(" ") && !playersSet.contains(tempPlayer)) {
            UserPlayer newPlayer = new UserPlayer(name);
            setCurrentPlayer(newPlayer);
            playersSet.add(newPlayer);
            System.out.println("New player created - " + currentPlayer.getName());
        } else {
            System.out.println("Wrong name, or player already exist.");
        }
    }

    public void addPlayerToDatabase(UserPlayer player) {
        playersSet.add(player);
    }

    private void setCurrentPlayer(UserPlayer newPlayer) {
        this.currentPlayer = newPlayer;
    }

    public UserPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
