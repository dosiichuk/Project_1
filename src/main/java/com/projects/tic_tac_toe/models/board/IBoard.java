package com.projects.tic_tac_toe.models.board;

import com.projects.tic_tac_toe.models.boardcell.IBoardCell;

import java.util.List;

public interface IBoard {
    int getBoardSize();
    void initializeBoardCells(int boardSize);
    List<IBoardCell> getBoardCells();
    IBoardCell getBoardCell(int x, int y);
}
