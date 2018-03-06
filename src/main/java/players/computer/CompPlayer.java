package players.computer;

import enums.Moves;

import java.util.*;

public class CompPlayer {
    final private Random randomChoice = new Random();
    final private List<Moves> choiceList = new ArrayList<>();

    private int roundPoints;
    private int winChancesModifier;
    private int loseChancesModifier;
    private int drawChancesModifier;

    public void setComputerChances(Moves playerMove, List<Moves> choiceList) {
        choiceList.clear();

        switch (playerMove) {
            case R:
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add(Moves.P);
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add(Moves.S);
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add(Moves.R);
                }
                break;
            case S:
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add(Moves.R);
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add(Moves.P);
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add(Moves.S);
                }
                break;
            case P:
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add(Moves.S);
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add(Moves.R);
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add(Moves.P);
                }
                break;
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

    public void setWinChancesModifier(Scanner chanceModifierScanner) {
        boolean isEntryCorrect = true;
        while (isEntryCorrect) {
            try {
                System.out.println("Enter new value (1 - 100)");
                final int newWinChancesValue = chanceModifierScanner.nextInt();
                if (newWinChancesValue > 0 && newWinChancesValue <= 100) {
                    System.out.println("Value correct, new value of " + newWinChancesValue + " chances to win has been set.\n");
                    this.winChancesModifier = newWinChancesValue;
                    isEntryCorrect = false;
                } else {
                    System.out.println("Value out of requested bounds. Try different value.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong digit format, please try again.");
                chanceModifierScanner.nextLine();
            }
        }
    }

    public void setLoseChancesModifier(Scanner chanceModifierScanner) {
        boolean isEntryCorrect = true;
        while (isEntryCorrect) {
            try {
                System.out.println("Enter new value (1 - 100)");
                final int newLoseChancesValue = chanceModifierScanner.nextInt();
                if (newLoseChancesValue > 0 && newLoseChancesValue <= 100) {
                    System.out.println("Value correct, new value of " + newLoseChancesValue + " chances to lose has been set.\n");
                    this.loseChancesModifier = newLoseChancesValue;
                    isEntryCorrect = false;
                } else {
                    System.out.println("Value out of requested bounds. Try different value.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong digit format, please try again.");
                chanceModifierScanner.nextLine();
            }
        }
    }

    public void setDrawChancesModifier(Scanner chanceModifierScanner) {
        boolean isEntryCorrect = true;
        while (isEntryCorrect) {
            try {
                System.out.println("Enter new value (1 - 100)");
                final int newDrawChancesValue = chanceModifierScanner.nextInt();
                if (newDrawChancesValue > 0 && newDrawChancesValue <= 100) {
                    System.out.println("Value correct, new value of " + newDrawChancesValue + " chances to draw has been set.\n");
                    this.drawChancesModifier = newDrawChancesValue;
                    isEntryCorrect = false;
                } else {
                    System.out.println("Value out of requested bounds. Try different value.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong digit format, please try again.");
                chanceModifierScanner.nextLine();
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

    public void addRoundPoint() {
        roundPoints++;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }

    public String getName() {
        return "Computer";
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public int getWinChancesModifier() {
        return winChancesModifier;
    }

    public int getLoseChancesModifier() {
        return loseChancesModifier;
    }

    public int getDrawChancesModifier() {
        return drawChancesModifier;
    }

    public List<Moves> getChoiceList() {
        return choiceList;
    }
}