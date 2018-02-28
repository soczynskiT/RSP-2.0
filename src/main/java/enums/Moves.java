package enums;

public enum Moves {
    R("Rock", 0),
    S("Scissors", 1),
    P("Paper", 2);

    private String name;
    private int resultLabelPosition;

    Moves(String s, int resultLabelPosition) {
        this.name = s;
        this.resultLabelPosition = resultLabelPosition;
    }

    public String getName() {
        return name;
    }

    public int getResultLabelPosition() {
        return resultLabelPosition;
    }
}
