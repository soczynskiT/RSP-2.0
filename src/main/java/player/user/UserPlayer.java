package player.user;

import enums.Moves;
import player.Player;

import java.util.Objects;
import java.util.Scanner;

public class UserPlayer implements Player {
    private final Scanner scanner = new Scanner(System.in);

    private String name;
    /* needed for stats in future
    private int wonGames;
    private int lostGames;
    */
    private int roundPoints;

    public UserPlayer(String name) {
        this.name = name;
    }

    @Override
    public String move() {
        System.out.println("[R] ~~ " + Moves.R.getName());
        System.out.println("[P] ~~ " + Moves.P.getName());
        System.out.println("[S] ~~ " + Moves.S.getName());

        boolean wrong = true;
        String result = "";
        do {
            String moveCmd = scanner.nextLine().toUpperCase();
            try {
                switch (Moves.valueOf(moveCmd)) {
                    case R:
                        result = Moves.R.getName();
                        wrong = false;
                        break;
                    case P:
                        result = Moves.P.getName();
                        wrong = false;
                        break;
                    case S:
                        result = Moves.S.getName();
                        wrong = false;
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("raz jeszcze");
            }
        }
        while (wrong);
        return result;
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    public String getName() {
        return name;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPlayer)) return false;
        UserPlayer that = (UserPlayer) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName());
    }
}
