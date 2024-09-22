package view;
import java.util.Scanner;

import model.GameOf2048;

public class Input {
	
	// scanner object
	private static final Scanner SCANNER = new Scanner(System.in);
	
	// method to read a character input from the user
	private static char readCharacter(String message) {
		Output.print(message);
		String input = SCANNER.nextLine();
		while( input.length()!=1 ) {
			// show invalid input
			System.out.println("Input is not a single character! Try again!");
			Output.println("");
			Output.print(message);
			input = SCANNER.nextLine();
		}
		
		return input.charAt(0);
	}
	
	// method to get the user move
	public static char getUserMove() {
		char userMove = readCharacter(
				"Choose a move: \n" + 
						"W/w: left\n" + 
						"S/s: down\n" + 
						"A/a: up\n" + 
						"D/d: right\n" + 
						"Enter move: "
			);
		
		userMove = Character.toUpperCase(userMove);
		// as long as this is not a valid move
		while( !(userMove==GameOf2048.MOVE_LEFT || userMove==GameOf2048.MOVE_RIGHT || 
				userMove==GameOf2048.MOVE_UP || userMove==GameOf2048.MOVE_DOWN) ) {
			// show invalid input
			Output.println("Invalid Move!");
			Output.println("");
			
			userMove = readCharacter(
					"Choose a move: \n" + 
							"W/w: left\n" + 
							"S/s: down\n" + 
							"A/a: up\n" + 
							"D/d: right\n" + 
							"Enter move: "
				);
			userMove = Character.toUpperCase(userMove);
		}
		
		// return the move
		return userMove;
	}

}
