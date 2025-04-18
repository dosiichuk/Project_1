package com.projects.tic_tac_toe.services;

import com.projects.tic_tac_toe.models.board.Board;
import com.projects.tic_tac_toe.models.board.IBoard;
import com.projects.tic_tac_toe.models.boardcell.IBoardCell;
import com.projects.tic_tac_toe.models.player.*;
import com.projects.tic_tac_toe.models.round.Round;

import java.util.ArrayList;
import java.util.List;

public class GameService implements IGameService {
    private IPlayer player1;
    private IPlayer player2;
    private IPlayer currentPlayer;
    private IPlayer winner;
    private IBoard board;
    private boolean isGameOver = false;
    private List<Round> rounds = new ArrayList<>();

    @Override
    public boolean initializePlayers(PlayerType adversaryType) {
        try {
            player1 = new HumanPlayer(0, PlayerType.HUMAN, PlayerSymbol.X);
            if (adversaryType == PlayerType.HUMAN) {
                player2 = new HumanPlayer(1, PlayerType.HUMAN, PlayerSymbol.O);
            } else {
                player2 = new AIPlayer(1, PlayerType.COMPUTER, PlayerSymbol.O);
            }
            currentPlayer = player1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean initializeBoard(int boardSize) {
        try {
            board = new Board(boardSize);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean processPlayerMove(int x, int y, int playerId) {
        if (x > board.getBoardSize() - 1 && y > board.getBoardSize() - 1) {
            return false;
        }
        IBoardCell cell = board.getBoardCell(x, y);
        if (cell.isOccupied()) {
            return false;
        }
        cell.setOccupied(true);
        cell.setOccupyingPlayerSymbol(playerId == 1 ? PlayerSymbol.X : PlayerSymbol.O);
        IPlayer currentPlayer = playerId == 1 ? player1 : player2;
        boolean isWinnerKnown = checkIsGameOverAfterPlayerMove(currentPlayer, board);
        if (isWinnerKnown) {
            winner = currentPlayer;
            isGameOver = true;
        }
        return true;
    }

    @Override
    public boolean checkIsGameOverAfterPlayerMove(IPlayer player, IBoard board) {
        int size = board.getBoardSize();
        PlayerSymbol playerSymbol = player.getPlayerSymbol();

        // Track player's occupied positions
        boolean[][] playerBoard = new boolean[size][size];

        for (IBoardCell cell : board.getBoardCells()) {
            if (cell.isOccupied() && cell.getOccupyingPlayerSymbol() == playerSymbol) {
                playerBoard[cell.getX()][cell.getY()] = true;
            }
        }

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int j = 0; j < size; j++) {
                if (!playerBoard[i][j]) rowWin = false;
                if (!playerBoard[j][i]) colWin = false;
            }

            if (rowWin || colWin) return true;
        }

        // Check diagonals
        boolean mainDiagonalWin = true;
        boolean antiDiagonalWin = true;

        for (int i = 0; i < size; i++) {
            if (!playerBoard[i][i]) mainDiagonalWin = false;
            if (!playerBoard[i][size - 1 - i]) antiDiagonalWin = false;
        }

        return mainDiagonalWin || antiDiagonalWin;
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public IPlayer getPlayer1() {
        return player1;
    }

    @Override
    public IPlayer getPlayer2() {
        return player2;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


}
