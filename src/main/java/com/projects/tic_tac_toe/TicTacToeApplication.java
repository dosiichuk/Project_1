package com.projects.tic_tac_toe;

import com.projects.tic_tac_toe.services.GameService;
import com.projects.tic_tac_toe.services.IGameService;
import com.projects.tic_tac_toe.view.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args) {

		IGameService gameService = new GameService();
		new View(gameService).run();
	}

}
