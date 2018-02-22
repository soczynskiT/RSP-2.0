package player.computer;

import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CompPlayer implements Player {
    final private Random random = new Random();
    final private List<String> choiceList = new ArrayList<>(Arrays.asList("Rock", "Paper", "Scissors"));
    private int roundPoints;

    @Override
    public String move() {
        final int compChoice = random.nextInt(3);
        return choiceList.get(compChoice);
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public String getName() {
        return "Computer";
    }
}
