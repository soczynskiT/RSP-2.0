package enums;

public enum Moves {
    R("Rock") {
        public RoundResults getRoundResult(Moves compMove) {
                if (compMove == Moves.S) {
                    return RoundResults.WIN;
                }
                return RoundResults.LOSE;
        }
    },
    S("Scissors"){
        public RoundResults getRoundResult(Moves compMove) {
            if (compMove == Moves.P) {
                return RoundResults.WIN;
            }
            return RoundResults.LOSE;
        }
    },
    P("Paper") {
        public RoundResults getRoundResult(Moves compMove) {
            if (compMove == Moves.R) {
                return RoundResults.WIN;
            }
            return RoundResults.LOSE;
        }
    };

    private String name;

    Moves(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public abstract RoundResults getRoundResult(Moves compMove);
}
