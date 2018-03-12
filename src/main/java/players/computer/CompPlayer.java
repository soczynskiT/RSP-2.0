package players.computer;

import enums.Moves;
import players.Player;
import players.user.UserMoveReader;

import java.util.*;

public class CompPlayer implements Player {
    private int roundPoints;
    private int winChancesModifier;
    private int loseChancesModifier;
    private int drawChancesModifier;

    private final Random randomChoice;
    private final List<Moves> choiceList;

    public CompPlayer() {
        this.randomChoice = new Random();
        this.choiceList = new ArrayList<>();
    }

    public void setComputerChances(Moves playerMove) {
        choiceList.clear();
        switch (playerMove) {
            case R:
                addChoicesToChoiceList(Moves.P, Moves.SP, winChancesModifier);
                addChoicesToChoiceList(Moves.S, Moves.L, loseChancesModifier);
                addChoicesToChoiceList(Moves.R, Moves.R, drawChancesModifier);
                break;
            case S:
                addChoicesToChoiceList(Moves.R, Moves.SP, winChancesModifier);
                addChoicesToChoiceList(Moves.P, Moves.L, loseChancesModifier);
                addChoicesToChoiceList(Moves.S, Moves.S, drawChancesModifier);
                break;
            case P:
                addChoicesToChoiceList(Moves.S, Moves.L, winChancesModifier);
                addChoicesToChoiceList(Moves.R, Moves.SP, loseChancesModifier);
                addChoicesToChoiceList(Moves.P, Moves.P, drawChancesModifier);
                break;
            case L:
                addChoicesToChoiceList(Moves.S, Moves.R, winChancesModifier);
                addChoicesToChoiceList(Moves.P, Moves.SP, loseChancesModifier);
                addChoicesToChoiceList(Moves.L, Moves.L, drawChancesModifier);
                break;
            case SP:
                addChoicesToChoiceList(Moves.P, Moves.L, winChancesModifier);
                addChoicesToChoiceList(Moves.R, Moves.S, loseChancesModifier);
                addChoicesToChoiceList(Moves.SP, Moves.SP, drawChancesModifier);
                break;
        }
    }

    private void addChoicesToChoiceList(Moves move, Moves move2, int modifier) {
        for (int i = 0; i < modifier; i++) {
            final int modulo = i % 2;
            if (modulo != 0) {
                choiceList.add(move);
            } else {
                choiceList.add(move2);
            }
        }
    }

    public Moves makeMove() {
        final int compChoice = randomChoice.nextInt(choiceList.size());
        return choiceList.get(compChoice);
    }

    public void setDefaultChancesModifiersValues() {
        this.winChancesModifier = 100;
        this.loseChancesModifier = 100;
        this.drawChancesModifier = 100;
    }

    public void setWinChancesModifier(UserMoveReader userMoveReader) {
        System.out.println("Enter new value (1 - 100)");
        boolean isValueCorrect = true;
        while (isValueCorrect) {
            final int newWinChancesValue = userMoveReader.readNumber();
            if (newWinChancesValue > 0 && newWinChancesValue <= 100) {
                System.out.println("Value correct, new value of " + newWinChancesValue + " chances to win has been set.\n");
                this.winChancesModifier = newWinChancesValue;
                isValueCorrect = false;
            } else {
                System.out.println("Value out of requested bounds. Try different value.");
            }
        }
    }

    public void setLoseChancesModifier(UserMoveReader userMoveReader) {
        System.out.println("Enter new value (1 - 100)");
        boolean isValueCorrect = true;
        while (isValueCorrect) {
            final int newLoseChancesValue = userMoveReader.readNumber();
            if (newLoseChancesValue > 0 && newLoseChancesValue <= 100) {
                System.out.println("Value correct, new value of " + newLoseChancesValue + " chances to lose has been set.\n");
                this.loseChancesModifier = newLoseChancesValue;
                isValueCorrect = false;
            } else {
                System.out.println("Value out of requested bounds. Try different value.");
            }
        }
    }

    public void setDrawChancesModifier(UserMoveReader userMoveReader) {
        System.out.println("Enter new value (1 - 100)");
        boolean isValueCorrect = true;
        while (isValueCorrect) {
            final int newDrawChancesValue = userMoveReader.readNumber();
            if (newDrawChancesValue > 0 && newDrawChancesValue <= 100) {
                System.out.println("Value correct, new value of " + newDrawChancesValue + " chances to draw has been set.\n");
                this.drawChancesModifier = newDrawChancesValue;
                isValueCorrect = false;
            } else {
                System.out.println("Value out of requested bounds. Try different value.");
            }
        }
    }

    public void displayCurrentChanceSettings() {
        final double sum = winChancesModifier + loseChancesModifier + drawChancesModifier;
        System.out.println("Current settings for computer opponent:\n" +
                "Win chance: " + String.format("%.2f", winChancesModifier / sum * 100) + "%\n" +
                "Lose chance: " + String.format("%.2f", loseChancesModifier / sum * 100) + "%\n" +
                "Draw chance: " + String.format("%.2f", drawChancesModifier / sum * 100) + "%\n");
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    @Override
    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }

    @Override
    public String getName() {
        return "computer";
    }

    @Override
    public int getRoundPoints() {
        return roundPoints;
    }

    public List<Moves> getChoiceList() {
        return choiceList;
    }
}