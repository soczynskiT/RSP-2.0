package players.computer;

import players.Player;

import java.util.*;

public class CompPlayer implements Player {
    final private Random randomChoice = new Random();
    final private Scanner chanceModifierScanner = new Scanner(System.in);
    final private List<String> choiceList = new ArrayList<>();

    private int roundPoints;
    private int winChancesModifier;
    private int loseChancesModifier;
    private int drawChancesModifier;

    public void setComputerChances(String playerMove) {
        choiceList.clear();
        switch (playerMove) {
            case "Rock":
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add("Paper");
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add("Scissors");
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add("Rock");
                }
                break;
            case "Scissors":
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add("Rock");
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add("Paper");
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add("Scissors");
                }
                break;
            case "Paper":
                for (int i = 0; i < winChancesModifier; i++) {
                    choiceList.add("Scissors");
                }
                for (int i = 0; i < loseChancesModifier; i++) {
                    choiceList.add("Rock");
                }
                for (int i = 0; i < drawChancesModifier; i++) {
                    choiceList.add("Paper");
                }
                break;
        }
    }

    @Override
    public String makeMove() {
        final int compChoice = randomChoice.nextInt(choiceList.size());
        return choiceList.get(compChoice);
    }

    @Override
    public void addRoundPoint() {
        roundPoints++;
    }

    public String getName() {
        return "Computer";
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }
    public void setDefaultChancesModifiersValues() {
        this.winChancesModifier = 100;
        this.loseChancesModifier = 100;
        this.drawChancesModifier = 100;
    }

    public void setWinChancesModifier() {
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

    public void setLoseChancesModifier() {
        boolean isEntryCorrect = true;
        while(isEntryCorrect) {
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

    public void setDrawChancesModifier() {
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
}