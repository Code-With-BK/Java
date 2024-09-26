package controller;
import model.GameOf2048;
import view.GameOf2048View;

public class GameOf2048Controller {
	
	// the model
	private GameOf2048 gameOf2048;
	
	// the view - Output & Input classes have static functions
	private GameOf2048View gameOf2048View;
	
	// the constructor
	public GameOf2048Controller() {
		
	}
	
	// the method to initialize the model
	public void initModel(GameOf2048 gameOf2048) {
		this.gameOf2048 = gameOf2048;
	}
	
	// the method to initialize the view
	public void initView(GameOf2048View gameOf2048View) {
		this.gameOf2048View = gameOf2048View;
		gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
	}
	
	// the method to take input from the view
	public void handleUserInput(char move) {
		// System.out.println("input move: " + move);
		boolean moveMade = gameOf2048.processMove(move);
		
		// if the game is over
		if( gameOf2048.isGameOver() ) {
			
			// show the final board
			gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
			
			// if the user won
			if( gameOf2048.gameWon() ) {
				gameOf2048View.showGameResult("You won!");
			}
			// else if the game lost
			else {
				gameOf2048View.showGameResult("You lost!");
			}
		}
		// else if the game is not over
		else {
			if( moveMade ) {
				// add random 2/4
				gameOf2048.getBoard().addRandomDigit();
				// update the board
				gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
			}
		}
	}
	
	// the method to control the game
//	public void play() {
//		// play the game
//
//		// while the game is not over
//		while( !gameOf2048.isGameOver() ) {
//			// show the board
//			Output.showBoard(gameOf2048.getBoard());
//
//			// ask the user to make a move
//			char move = Input.getUserMove();
//
//			// process the move
//			boolean moveMade = gameOf2048.processMove(move);
//
//			// add random 2/4
//			if( moveMade ) {
//				gameOf2048.getBoard().addRandomDigit();
//			}
//		}
//		
//		// show the board
//		Output.showBoard(gameOf2048.getBoard());
//
//		if( gameOf2048.gameWon() ) {
//			Output.println("You WON!");
//		}
//		else {
//			Output.println("You LOST!");
//		}
//	}
}
