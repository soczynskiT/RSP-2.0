package enums;

public enum Moves {
    R("Rock"),
    S("Scissors"),
    P("Paper");

    private String name;

    Moves(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
