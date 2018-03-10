package players.user;

import enums.Moves;
import players.Player;

import java.util.Objects;

public class UserPlayer implements Player {
    private String name;
    private int wonGames;
    private int lostGames;
    private int roundPoints;

    public UserPlayer(final String name) {
        this.name = name;
    }

    public Moves makeMove(UserMoveReader userMoveReader) {
        System.out.println("[R] ~~ " + Moves.R.getName());
        System.out.println("[P] ~~ " + Moves.P.getName());
        System.out.println("[S] ~~ " + Moves.S.getName());

        return getPlayerMove(userMoveReader);
    }

    private Moves getPlayerMove(UserMoveReader userMoveReader) {
        boolean isMoveCorrect = true;
        Moves result = null;
        while (isMoveCorrect) {
            final String moveChoice = userMoveReader.readMove().toUpperCase();
            switch (moveChoice) {
                case "R":
                    result = Moves.R;
                    isMoveCorrect = false;
                    break;
                case "P":
                    result = Moves.P;
                    isMoveCorrect = false;
                    break;
                case "S":
                    result = Moves.S;
                    isMoveCorrect = false;
                    break;
                default:
                    System.out.println("Wrong choice, try again");
                    return getPlayerMove(userMoveReader);
            }
        }
        return result;
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRoundPoints() {
        return roundPoints;
    }

    @Override
    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }

    public void incWonGames() {
        this.wonGames++;
    }

    public void incLostGames() {
        this.lostGames++;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void setName(String name) {
        this.name = name;
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