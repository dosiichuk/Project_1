package com.projects.tic_tac_toe.models.player;

public class AIPlayer implements IPlayer{
    private int id;
    private PlayerType playerType;
    private PlayerSymbol playerSymbol;

    public AIPlayer(int id, PlayerType playerType, PlayerSymbol playerSymbol) {
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

    public int[] generateRandomMove(int boardSize) {
        int x = (int) (Math.random() * boardSize);
        int y = (int) (Math.random() * boardSize);
        return new int[]{x, y};
    }

}
