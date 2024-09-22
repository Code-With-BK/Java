import controller.GameOf2048Controller;
import model.GameOf2048;

/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java code to create a Game Of 2048 on console/command line
 * - Using OOP Approach
 */


public class Main {

	public static void main(String[] args) {
//		Board board = new Board();
//		Output.showBoard(board);
		
		// the model
		GameOf2048 gameOf2048 = new GameOf2048();
		
		// the view - Input & Output
		
		// the controller
		GameOf2048Controller gameOf2048Controller = new GameOf2048Controller();
		
		// set the model
		gameOf2048Controller.initModel(gameOf2048);
		
		// call the play method 
		gameOf2048Controller.play();
	}

}
