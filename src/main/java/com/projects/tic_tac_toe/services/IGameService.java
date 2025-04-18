package com.projects.tic_tac_toe.services;

import com.projects.tic_tac_toe.models.board.IBoard;
import com.projects.tic_tac_toe.models.player.IPlayer;
import com.projects.tic_tac_toe.models.player.PlayerType;

public interface IGameService {
    boolean initializePlayers(PlayerType adversaryType);
    boolean initializeBoard(int boardSize);
    boolean processPlayerMove(int x, int y, int playerId);
    boolean checkIsGameOverAfterPlayerMove(IPlayer player, IBoard board);
    IBoard getBoard();
    boolean isGameOver();
    IPlayer getPlayer1();
    IPlayer getPlayer2();
    IPlayer getCurrentPlayer();
    void setGameOver(boolean gameOver);

}
