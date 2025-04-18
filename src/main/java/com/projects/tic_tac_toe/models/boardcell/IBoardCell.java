package com.projects.tic_tac_toe.models.boardcell;

import com.projects.tic_tac_toe.models.player.PlayerSymbol;

public interface IBoardCell {
    int getX();
    int getY();

    boolean isOccupied();
    PlayerSymbol getOccupyingPlayerSymbol();
    void setOccupied(boolean occupied);

    void setOccupyingPlayerSymbol(PlayerSymbol occupyingPlayerSymbol);
}
