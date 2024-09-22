package controller;
import model.GameOf2048;
import view.Input;
import view.Output;

public class GameOf2048Controller {
	
	// the model
	private GameOf2048 gameOf2048;
	
	// the view - Output & Input classes are static
	
	// the constructor
	public GameOf2048Controller() {
		
	}
	
	// the method to initialize the model
	public void initModel(GameOf2048 gameOf2048) {
		this.gameOf2048 = gameOf2048;
	}
	
	// the method to control the game
	public void play() {
		// play the game

		// while the game is not over
		while( !gameOf2048.isGameOver() ) {
			// show the board
			Output.showBoard(gameOf2048.getBoard());

			// ask the user to make a move
			char move = Input.getUserMove();

			// process the move
			boolean moveMade = gameOf2048.processMove(move);

			// add random 2/4
			if( moveMade ) {
				gameOf2048.getBoard().addRandomDigit();
			}
		}
		
		// show the board
		Output.showBoard(gameOf2048.getBoard());

		if( gameOf2048.gameWon() ) {
			Output.println("You WON!");
		}
		else {
			Output.println("You LOST!");
		}
	}
}
