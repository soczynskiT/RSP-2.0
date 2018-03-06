package players.user;

import enums.Moves;
import players.Player;

import java.util.Objects;
import java.util.Scanner;

public class UserPlayer implements Player {

    private String name;
    private int wonGames;
    private int lostGames;
    private int roundPoints;

    public UserPlayer(String name) {
        this.name = name;
    }

    @Override
    public Moves makeMove(Scanner userScanner) {
        System.out.println("[R] ~~ " + Moves.R.getName());
        System.out.println("[P] ~~ " + Moves.P.getName());
        System.out.println("[S] ~~ " + Moves.S.getName());

        boolean isChoiceWrong = true;
        Moves result = null;
        do {
            final String moveChoice = userScanner.nextLine().toUpperCase();
            try {
                switch (Moves.valueOf(moveChoice)) {
                    case R:
                        result = Moves.R;
                        isChoiceWrong = false;
                        break;
                    case P:
                        result = Moves.P;
                        isChoiceWrong = false;
                        break;
                    case S:
                        result = Moves.S;
                        isChoiceWrong = false;
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("! Wrong choice, please try again !");
            }
        }
        while (isChoiceWrong);
        return result;
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    public void incWonGames() {
        this.wonGames++;
    }

    public void incLostGames() {
        this.lostGames++;
    }

    public String getName() {
        return name;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
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

    @Override
    public String toString() {
        return "~~ " + name + " Won games > " + wonGames + " : " + lostGames + " < Lost games";
    }
}