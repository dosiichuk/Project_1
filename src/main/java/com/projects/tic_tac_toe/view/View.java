package com.projects.tic_tac_toe.view;

import com.projects.tic_tac_toe.models.board.IBoard;
import com.projects.tic_tac_toe.models.boardcell.IBoardCell;
import com.projects.tic_tac_toe.models.player.AIPlayer;
import com.projects.tic_tac_toe.models.player.IPlayer;
import com.projects.tic_tac_toe.models.player.PlayerType;
import com.projects.tic_tac_toe.services.IGameService;

import java.util.Scanner;

public class View implements IView {
    private Scanner scanner = new Scanner(System.in);
    private IGameService gameService;

    public View(IGameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run() {
        determineBoardSize();
        determineAdversaryType();
        showTheCurrentBoard();
        System.out.println("Let's start the game!");
        while (!gameService.isGameOver()) {
            System.out.println("Player 1 move:");
            boolean player1MadeAvalidMove = false;
            do {
                try {
                    player1MadeAvalidMove = takeHumanPlayerMove(1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(!player1MadeAvalidMove);
            showTheCurrentBoard();
            boolean isGameOver = gameService.checkIsGameOverAfterPlayerMove(gameService.getPlayer1(), gameService.getBoard());
            if (isGameOver) {
                displayGameOverMessage();
                gameService.setGameOver(true);
                break;
            }
            System.out.println("Player 2 move:");
            boolean player2MadeAValidMove = false;

            if (gameService.getPlayer2().getPlayerType() == PlayerType.HUMAN) {
                do {
                    try {
                        player2MadeAValidMove = takeHumanPlayerMove(2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while(!player2MadeAValidMove);
                isGameOver = gameService.checkIsGameOverAfterPlayerMove(gameService.getPlayer2(), gameService.getBoard());
                if (isGameOver) {
                    displayGameOverMessage();
                    gameService.setGameOver(true);
                    break;
                }
            } else {
                do {
                    try {
                        AIPlayer player = (AIPlayer) gameService.getPlayer2();
                        int[] move = player.generateRandomMove(gameService.getBoard().getBoardSize());
                        player2MadeAValidMove = gameService.processPlayerMove(move[0], move[1], 2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while(!player2MadeAValidMove);
                isGameOver = gameService.checkIsGameOverAfterPlayerMove(gameService.getPlayer2(), gameService.getBoard());
                if (isGameOver) {
                    displayGameOverMessage();
                    gameService.setGameOver(true);
                    break;
                }
            }
            showTheCurrentBoard();
        }
    }

    @Override
    public boolean determineBoardSize() {
        boolean boardSizeDetermined = false;
        do {
            System.out.println("What is the size of the board you would like to play on? It cannot be less than 3.");
            String userInput = scanner.nextLine();
            try {
                int boardSize = Integer.parseInt(userInput);
                gameService.initializeBoard(boardSize);
                boardSizeDetermined = true;
                return true;
            } catch (Exception e) {
                System.out.println("Invalid input for board size!");
            }
        } while (!boardSizeDetermined);
        return false;
    }

    @Override
    public boolean determineAdversaryType() {
        boolean isAdversaryTypeDetermined = false;
        do {
            System.out.println("Would you like to play with a Human (1) or AI (2)?");
            String userInput = scanner.nextLine();
            try {
                int adversaryType = Integer.parseInt(userInput);
                if (adversaryType != 1 && adversaryType !=2) {
                    throw new Exception();
                }
                PlayerType playerType = adversaryType == 1 ? PlayerType.HUMAN : PlayerType.COMPUTER;
                isAdversaryTypeDetermined = true;
                gameService.initializePlayers(playerType);
                return true;
            } catch (Exception e) {
                System.out.println("Invalid input for adversary type!");
            }
        } while (!isAdversaryTypeDetermined);
        return false;
    }

    @Override
    public void showTheCurrentBoard() {
        IBoard board = gameService.getBoard();
        int n = board.getBoardSize();
        String[][] displayBoard = new String[n][n];
        // Initialize the board with placeholders
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                displayBoard[i][j] = " ";
            }
        }
        for (IBoardCell cell : board.getBoardCells()) {
            switch (cell.getOccupyingPlayerSymbol()) {
                case X -> displayBoard[cell.getX()][cell.getY()] = "X";
                case O -> displayBoard[cell.getX()][cell.getY()] = "O";
                case NONE -> displayBoard[cell.getX()][cell.getY()] = " ";
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(displayBoard[i][j]);
                if (j < n - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < n - 1) {
                System.out.println("-".repeat(n * 4 - 3));
            }
        }
    }

    @Override
    public boolean takeHumanPlayerMove(int playerId) throws Exception {
        System.out.println("Enter x and y coordinates of the cell you'd like to take:");
        String userInput = scanner.nextLine();
        int boardSize = gameService.getBoard().getBoardSize();
        try {
            String[] coordinates = userInput.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            boolean isValidMove = gameService.processPlayerMove(x, y, playerId);
            if (!isValidMove) {
                throw new Exception("Your move is invalid. Try again.");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean displayGameOverMessage() {
        IPlayer winner = gameService.getCurrentPlayer();
        System.out.println("Game over! The winner is Player " + (winner.getId() + 1));
        showTheCurrentBoard();
        return true;
    }
}
