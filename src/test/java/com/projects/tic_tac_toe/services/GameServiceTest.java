package com.projects.tic_tac_toe.services;
import com.projects.tic_tac_toe.models.player.PlayerType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameServiceTest {

    @Test
    void initializePlayers() {
        //Given
        GameService gameService = new GameService();
        PlayerType adversaryType = PlayerType.HUMAN;

        // 
        gameService.initializePlayers(adversaryType);

        // Then
        assertNotNull(gameService.getPlayer1());
        assertNotNull(gameService.getPlayer2());
        assertEquals(gameService.getPlayer1().getPlayerType(), PlayerType.HUMAN);
    }

    @Test
    void initializeBoard() {
        // Given
        GameService gameService = new GameService();
        int boardSize = 3;

        // When
        boolean result = gameService.initializeBoard(boardSize);

        // Then
        assertTrue(result);
        assertNotNull(gameService.getBoard());
        assertEquals(gameService.getBoard().getBoardSize(), boardSize);
    }

    @Test
    void processPlayerMove() {
        // Given
        GameService gameService = new GameService();
        gameService.initializePlayers(PlayerType.HUMAN);
        gameService.initializeBoard(3);

        // When
        boolean result = gameService.processPlayerMove(0, 0, 1);

        // Then
        assertTrue(result);
        assertEquals(gameService.getBoard().getBoardCell(0, 0).getOccupyingPlayerSymbol(), gameService.getPlayer1().getPlayerSymbol());
    }

    @Test
    void processPlayerMoveForAnOccupiedCell() {
        // Given
        GameService gameService = new GameService();
        gameService.initializePlayers(PlayerType.HUMAN);
        gameService.initializeBoard(3);

        // When
        boolean result = gameService.processPlayerMove(0, 0, 1);
        result = gameService.processPlayerMove(0, 0, 2);
        // Then
        assertFalse(result);
        assertEquals(gameService.getBoard().getBoardCell(0, 0).getOccupyingPlayerSymbol(), gameService.getPlayer1().getPlayerSymbol());
    }

    @Test
    void checkIsGameOverAfterPlayerMove() {
        // Given
        GameService gameService = new GameService();
        gameService.initializePlayers(PlayerType.HUMAN);
        gameService.initializeBoard(3);

        // When
        gameService.processPlayerMove(0, 0, 1);
        gameService.processPlayerMove(1, 1, 2);
        gameService.processPlayerMove(0, 1, 1);
        gameService.processPlayerMove(2, 2, 2);
        boolean result = gameService.checkIsGameOverAfterPlayerMove(gameService.getCurrentPlayer(), gameService.getBoard());

        // Then
        assertFalse(result);
    }

    @Test
    void checkIsGameOverAfterTheWinningMove() {
        // Given
        GameService gameService = new GameService();
        gameService.initializePlayers(PlayerType.HUMAN);
        gameService.initializeBoard(3);

        // When
        gameService.processPlayerMove(0, 0, 1);
        gameService.processPlayerMove(1, 1, 2);
        gameService.processPlayerMove(0, 1, 1);
        gameService.processPlayerMove(2, 2, 2);
        gameService.processPlayerMove(0, 2, 1);
        boolean result = gameService.checkIsGameOverAfterPlayerMove(gameService.getCurrentPlayer(), gameService.getBoard());

        // Then
        assertTrue(result);
    }
}