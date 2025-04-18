package com.projects.tic_tac_toe.models.board;

import com.projects.tic_tac_toe.models.boardcell.BoardCell;
import com.projects.tic_tac_toe.models.boardcell.IBoardCell;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    private int boardSize;
    private List<IBoardCell> boardCells = new ArrayList<>();

    public Board(int boardSize) {
        this.boardSize = boardSize;
        initializeBoardCells(boardSize);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public void initializeBoardCells(int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boardCells.add(new BoardCell(i, j));
            }
        }
    }

    @Override
    public List<IBoardCell> getBoardCells() {
        return boardCells;
    }

    @Override
    public IBoardCell getBoardCell(int x, int y) {

        return boardCells.stream()
                .filter(boardCell -> boardCell.getX() == x && boardCell.getY() == y)
                .findFirst()
                .orElse(null);
    }
}
