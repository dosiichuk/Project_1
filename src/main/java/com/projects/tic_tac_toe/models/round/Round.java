package com.projects.tic_tac_toe.models.round;

import com.projects.tic_tac_toe.models.boardcell.IBoardCell;

public class Round {
    private static int id = 0;
    private int roundId;
    private IBoardCell player1Move;
    private IBoardCell player2Move;

    public Round(IBoardCell player1Move, IBoardCell player2Move) {
        this.player1Move = player1Move;
        this.player2Move = player2Move;
        this.roundId = id;
        id++;
    }
}
