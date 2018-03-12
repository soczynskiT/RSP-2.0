package gamecode;

import enums.Moves;

import java.util.HashSet;
import java.util.Objects;

public final class RoundMove {
    private final HashSet<Moves> roundMove;

    public RoundMove(final Moves move, final Moves move2) {
        this.roundMove = roundMove(move, move2);
    }

    private HashSet<Moves> roundMove(Moves move, Moves move2) {
        HashSet<Moves> roundMove = new HashSet<>();
        roundMove.add(move);
        roundMove.add(move2);
        return roundMove;
    }

    public HashSet<Moves> getRoundMove() {
        return roundMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundMove)) return false;
        RoundMove roundMove1 = (RoundMove) o;
        return Objects.equals(getRoundMove(), roundMove1.getRoundMove());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getRoundMove());
    }
}
