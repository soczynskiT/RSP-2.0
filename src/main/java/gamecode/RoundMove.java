package gamecode;

import enums.Moves;

import java.util.Objects;

public class RoundMove {
    private Moves playerMove;
    private Moves compMove;

    RoundMove(final Moves playerMove, final Moves compMove) {
        this.playerMove = playerMove;
        this.compMove = compMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundMove)) return false;
        RoundMove roundMove = (RoundMove) o;
        return playerMove == roundMove.playerMove &&
                compMove == roundMove.compMove;
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerMove, compMove);
    }
}
