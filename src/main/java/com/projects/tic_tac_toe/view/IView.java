package com.projects.tic_tac_toe.view;

import java.util.Scanner;

import com.projects.tic_tac_toe.models.board.IBoard;

public interface IView {
    boolean determineBoardSize();
    boolean determineAdversaryType();
    void showTheCurrentBoard();
    boolean takeHumanPlayerMove(int playerId) throws Exception;
    boolean displayGameOverMessage();
    void run();
    Scanner getScanner();
    void setScanner(Scanner scanner);
}
