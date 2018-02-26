package players.computer;

import players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CompPlayer implements Player {
    final private Random randomChoice = new Random();
    final private List<String> choiceList = new ArrayList<>(Arrays.asList("Rock", "Paper", "Scissors"));

    final String name = "Computer";
    private int roundPoints;


    @Override
    public String makeMove() {
        final int compChoice = randomChoice.nextInt(3);
        return choiceList.get(compChoice);
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

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }
}



