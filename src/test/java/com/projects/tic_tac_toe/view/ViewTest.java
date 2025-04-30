package com.projects.tic_tac_toe.view;

import com.projects.tic_tac_toe.models.board.IBoard;
import com.projects.tic_tac_toe.models.player.PlayerType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.projects.tic_tac_toe.services.GameService;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;

class ViewTest {

    private View view;
    private GameService gameService;
    private InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        gameService = Mockito.mock(GameService.class);
        view = new View(gameService);
    }

    @AfterEach
    void resetInputStream() {
        System.setIn(originalIn);
        Mockito.reset(gameService);
    }

    @Test
    void run() {
    }

    @Test
    void determineBoardSize() {
        // Given
        String input = "3";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int expectedSize = 3;
        
        //When
        view.setScanner(testScanner);
        IBoard board = Mockito.mock(IBoard.class);
        Mockito.when(gameService.getBoard()).thenReturn(board);
        Mockito.when(board.getBoardSize()).thenReturn(expectedSize);
        boolean result = view.determineBoardSize();

        // Then
        assertTrue(result);
        assertEquals(expectedSize, gameService.getBoard().getBoardSize());
    }

    @Test
    void determineBoardSizeLoopsUntilAValidInputIsProvided() {
        // Given
        String input = "abc\n2\n5\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        //When
        view.setScanner(testScanner);
        boolean result = view.determineBoardSize();
        // Then
        assertTrue(result);
    }

    @Test
    void determineAdversaryType() {
        // Given
        String input = "1";
        InputStream originalIn = System.in;
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        // When
        view.setScanner(testScanner);
        boolean result = view.determineAdversaryType();

        // Then
        assertTrue(result);
        System.setIn(originalIn);
    }

    @Test
    void determineAdversaryTypeWithInvalidUserInput() {
        // Given
        String input = "a\n4\n1\n";
        InputStream originalIn = System.in;
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        // When
        view.setScanner(testScanner);
        boolean result = view.determineAdversaryType();

        // Then
        assertTrue(result);
        System.setIn(originalIn);
    }

    

    @Test
    void showTheCurrentBoard() {
        // Given
        IBoard board = Mockito.mock(IBoard.class);
        Mockito.when(gameService.getBoard()).thenReturn(board);
        int boardSize = 3;
        Mockito.when(board.getBoardSize()).thenReturn(boardSize);
        
        // When
        view.showTheCurrentBoard();

        // Then
        Mockito.verify(gameService).getBoard();
        Mockito.verify(board).getBoardSize();
    }

    @Test
    void takeHumanPlayerMove() throws Exception {
        // Given
        String input = "1 1\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        // When
        view.setScanner(testScanner);
        Mockito.when(gameService.getBoard()).thenReturn(Mockito.mock(IBoard.class));
        Mockito.when(gameService.getBoard().getBoardSize()).thenReturn(3);
        Mockito.when(gameService.processPlayerMove(1, 1, 1)).thenReturn(true);
        boolean result = view.takeHumanPlayerMove(1);

        // Then
        assertTrue(result);
    }

    @Test
    void takeHumanPlayerMoveWithInvalidInput() throws Exception {
        // Given
        String input = "4 4\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        
        // When
        view.setScanner(testScanner);
        Mockito.when(gameService.getBoard()).thenReturn(Mockito.mock(IBoard.class));
        Mockito.when(gameService.getBoard().getBoardSize()).thenReturn(3);
        Mockito.when(gameService.processPlayerMove(4, 4, 1)).thenReturn(false);

        boolean result = view.takeHumanPlayerMove(1);

        // Then
        assertFalse(result);
    }
}