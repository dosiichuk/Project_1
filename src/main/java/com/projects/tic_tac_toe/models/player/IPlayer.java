package com.projects.tic_tac_toe.models.player;

public interface IPlayer {
    int getId();

    PlayerType getPlayerType();

    PlayerSymbol getPlayerSymbol();
}
