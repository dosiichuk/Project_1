package com.projects.tic_tac_toe.models.player;

public class HumanPlayer implements IPlayer{
    private int id;
    private PlayerType playerType;
    private PlayerSymbol playerSymbol;

    public HumanPlayer(int id, PlayerType playerType, PlayerSymbol playerSymbol) {
        this.id = id;
        this.playerType = playerType;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }
    @Override
    public PlayerSymbol getPlayerSymbol() {
        return playerSymbol;
    }
}
