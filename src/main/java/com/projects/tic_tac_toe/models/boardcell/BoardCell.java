package com.projects.tic_tac_toe.models.boardcell;

import com.projects.tic_tac_toe.models.player.PlayerSymbol;
import com.projects.tic_tac_toe.models.player.PlayerType;

import java.util.Objects;

public class BoardCell implements IBoardCell{
    private int x;
    private int y;
    private boolean isOccupied = false;
    private PlayerSymbol occupyingPlayerSymbol;

    public BoardCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupyingPlayerSymbol = PlayerSymbol.NONE;
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }
    @Override
    public PlayerSymbol getOccupyingPlayerSymbol() {
        return occupyingPlayerSymbol;
    }
    @Override
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    @Override
    public void setOccupyingPlayerSymbol(PlayerSymbol occupyingPlayerSymbol) {
        this.occupyingPlayerSymbol = occupyingPlayerSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BoardCell boardCell = (BoardCell) o;
        return x == boardCell.x && y == boardCell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
