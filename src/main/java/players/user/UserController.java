package players.user;

import java.util.HashSet;
import java.util.Set;

public class UserController {
    final private Set<UserPlayer> playersSet = new HashSet<>();
    final private UserPlayer tempPlayer = new UserPlayer("tempName");
    private UserPlayer currentPlayer;

    public UserController() {
        this.currentPlayer = new UserPlayer("name");
    }

    public void createFirstPlayer(UserMoveReader userMoveReader) {
        System.out.println("Enter your name:");
        boolean isCorrect = true;
        String name = null;
        while (isCorrect) {
            final String firstPlayerName = userMoveReader.readMove();
            if (!firstPlayerName.trim().isEmpty() && !firstPlayerName.equals("1")) {
                name = firstPlayerName;
                isCorrect = false;
            } else {
                System.out.println("Wrong name, please try again.");
            }
        }
        currentPlayer.setName(name);
        addPlayerToAllPlayersSet(currentPlayer);
    }

    public void createNewPlayer(UserMoveReader userMoveReader) {
        final String newPlayerName = userMoveReader.readMove();
        tempPlayer.setName(newPlayerName);
        if (!newPlayerName.trim().isEmpty() && !newPlayerName.equals("1") && !playersSet.contains(tempPlayer)) {
            final UserPlayer newPlayer = new UserPlayer(newPlayerName);
            setCurrentPlayer(newPlayer);
            playersSet.add(newPlayer);
            System.out.println("New player created ~~ " + currentPlayer.getName() + " ~~\n");
        } else {
            System.out.println("Wrong name, or player already exist.");
        }
    }

    public void changeCurrentPlayer(UserMoveReader userMoveReader) {
        System.out.println("Please choose existing player by typing full name (uppercase matters !), \"ENTER\" to confirm.");
        allPlayersSetDisplay();
        System.out.println("\n[1] ~~ Abort");

        final String playerToChangeName = userMoveReader.readMove();
        switch (playerToChangeName) {
            case "1":
                break;
            default:
                tempPlayer.setName(playerToChangeName);
                if (!playerToChangeName.equals("1")) {
                    if (playersSet.contains(tempPlayer)) {
                        for (UserPlayer player : playersSet) {
                            if (player.getName().equals(playerToChangeName)) {
                                setCurrentPlayer(player);
                                System.out.println("Current player set to ~~ " + currentPlayer.getName() + " ~~\n");
                            }
                        }
                    } else {
                        System.out.println("Player " + playerToChangeName +
                                " does not exist at database. Try again or create new player.\n");
                    }
                }
                break;
        }
    }

    private void allPlayersSetDisplay() {
        for (UserPlayer player : playersSet) {
            if (player.getName().equals(currentPlayer.getName())) {
                System.out.println(player.toString() + " // CURRENT PLAYER //");
            } else {
                System.out.println(player);
            }
        }
    }

    public void addOneWinPointToStats() {
        currentPlayer.incWonGames();
    }

    public void addOneLostPointToStats() {
        currentPlayer.incLostGames();
    }

    private void addPlayerToAllPlayersSet(UserPlayer player) {
        playersSet.add(player);
    }

    public void getCurrentPlayerStats() {
        System.out.println("Player ~~ " + currentPlayer.getName() + " ~~");
        System.out.println("Won: " + currentPlayer.getWonGames() + " : " + currentPlayer.getLostGames() + " Lost\n");
    }

    public Set<UserPlayer> getPlayersSet() {
        return playersSet;
    }

    public UserPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(UserPlayer newPlayer) {
        this.currentPlayer = newPlayer;
    }
}