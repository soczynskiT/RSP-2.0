package players;

import enums.Moves;

public interface Player {

    Moves makeMove();

    void addRoundPoint();

}
