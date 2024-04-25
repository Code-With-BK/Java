import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java code to create a Game Of 2048 on console/command line
 */

public class GameOf2048ConsoleApp {

	public static final int SIZE = 4;
	public static final char MOVE_LEFT = 'A';
	public static final char MOVE_RIGHT = 'D';
	public static final char MOVE_UP = 'W';
	public static final char MOVE_DOWN = 'S';

	private int board[][];
	private Random random;
	private Scanner scanner;

	public GameOf2048ConsoleApp() {
		// all locations of this 2D array is 0
		board = new int[4][4];

		// create a random object
		random = new Random();

		// create the Scanner object to read from the user
		scanner = new Scanner(System.in);
	}

	public void showBoard() {
		// print the top separator
		for( int i=0; i<4; i++ ) {
			System.out.print("-------");
		}
		System.out.println();

		// print each row
		for( int i=0; i<4; i++ ) {
			// print the blank space before the row
			System.out.print("|");
			for( int j=0; j<4; j++ ) {
				System.out.print("      |");
			}
			System.out.println();

			// print the first pipe symbol
			System.out.print("|");
			for( int j=0; j<4; j++ ) {
				if( board[i][j]==0 ) {
					System.out.printf("  %-3s |", "");
				}
				else {
					System.out.printf("  %-3s |", "" + board[i][j]);
				}
			}
			System.out.println();

			// print the blank space after the row
			System.out.print("|");
			for( int j=0; j<4; j++ ) {
				System.out.print("      |");
			}
			System.out.println();

			// print the bottom separator
			for( int j=0; j<4; j++ ) {
				System.out.print("-------");
			}
			System.out.println();
		}
	}

	public void addRandomDigit(int digit) {
		// add random digit on the board

		// generate a pair of i,j
		int i = random.nextInt(4);
		int j = random.nextInt(4);

		// generate i,j as long as this location on the board is occupied
		while( board[i][j] != 0 ) {
			i = random.nextInt(4);;
			j = random.nextInt(4);
		}
		
		// set the digit at this location
		board[i][j] = digit;
	}

	public boolean searchOnBoard(int x) {
		for( int i=0; i<4; i++ ) {
			for( int j=0; j<4; j++ ) {
				if( board[i][j] == x ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean gameWon() {
		return searchOnBoard(2048);
	}

	public boolean userCanMakeAMove() {
		// check 3x3 board
		for( int i=0; i<3; i++ ) {
			for( int j=0; j<3; j++ ){
				// if two adjacent locations have equal value, return true
				if( board[i][j] == board[i][j+1] || 
					board[i][j] == board[i+1][j] 
					) {
					return true;
				}
			}
		}
		// check if two equal adjacent values in the last row
		for( int j=0; j<3; j++ ) {
			if( board[3][j] == board[3][j+1] ) {
				return true;
			}
		}

		// check if two equal adjacent values in the last column
		for( int i=0; i<3; i++ ) {
			if( board[i][3] == board[i+1][3] ) {
				return true;
			}
		}

		// finally, return false
		return false;
	}

	public boolean isGameOver() {
		// game is over is there is a 2048 tile on the board
		if( gameWon() ) {
			return true;
		}

		// game is not over, if there is a blank tile on the board
		if( searchOnBoard(0) ) {
			return false;
		}

		// finally, game is not over if user can make a move
		return !userCanMakeAMove();
	}

	public char getUserMove() {
		// show all possible moves
		System.out.println("Choose a move: ");
		System.out.println("W/w: Up");
		System.out.println("S/s: Down");
		System.out.println("A/a: Left");
		System.out.println("D/d: Right");
		System.out.print("Enter move: ");

		// read the move from the user
		String moveInput = scanner.nextLine();
		if( moveInput.equalsIgnoreCase("a") || 
		moveInput.equalsIgnoreCase("w") || 
		moveInput.equalsIgnoreCase("s") || 
		moveInput.equalsIgnoreCase("d")
			) { 
			return moveInput.toUpperCase().charAt(0);
		}

		// if the input is invalid
		System.out.println("Invalid Input!");
		System.out.println();

		// show the board
		showBoard(); 

		// recur
		return getUserMove();
	}

	public int[] processLeftMove(int row[]) {
		// copy non-0 values
		int newRow[] = new int[4];
		int j = 0;
		for( int i=0; i<4; i++ ) {
			if( row[i]!=0 ) {
				newRow[j++] = row[i];
			}
		}

		// merge the values in this new row
		for( int i=0; i<3; i++ ) {
			if( newRow[i]!=0 && newRow[i]==newRow[i+1]) {
				newRow[i] = 2*newRow[i];	// a)
				// copy the remaining values  // b)
				for( j=i+1; j<3; j++ ) {
					newRow[j] = newRow[j+1];
				}
				// c) set the last location of this row to 0
				newRow[3] = 0;
			}
		}
		return newRow;
	}

	public int[] reverseArray(int arr[]) {
		int[] reverseArr = new int[arr.length];
		for( int i=arr.length-1; i>=0; i-- ) {
			reverseArr[i] = arr[arr.length - i - 1];
		}
		return reverseArr;
	}

	public int[] processRightMove(int row[]) {
		// copy all the non-0 values 
		int newRow[] = new int[4];
		int j = 0;
		for( int i=0; i<4; i++ ) {
			if( row[i]!=0 ) {
				newRow[j++] = row[i];
			}
		}

		// reverse the row
		newRow = reverseArray(newRow);

		// process left move
		newRow = processLeftMove(newRow);

		// reverse the row
		return reverseArray(newRow);
	}

	public void processMove(char move) {
		switch(move) {
			case MOVE_LEFT:
			{
				// for each row
				for( int i=0; i<4; i++ ){
					// get the new row
					int newRow[] = processLeftMove(board[i]);
					// copy values from the new row to the row
					for( int j=0; j<4; j++ ) {
						board[i][j] = newRow[j];
					}
				}
			}
			break;
			case MOVE_RIGHT:
			{
				// for each row
				for( int i=0; i<4; i++ ){
					// get the new row
					int newRow[] = processRightMove(board[i]);
					// copy values from the new row to the row
					for( int j=0; j<4; j++ ) {
						board[i][j] = newRow[j];
					}
				}
			}
			break;
			case MOVE_UP:
			{
				// for each column
				for( int j=0; j<4; j++ ) {
					// create a row from column values
					int row[] = new int[4];
					for( int i=0; i<4; i++ ) {
						row[i] = board[i][j];
					}

					// process left move on this row
					int newRow[] = processLeftMove(row);

					// copy the values back into the column
					for( int i=0; i<4; i++ ) {
						board[i][j] = newRow[i];
					}
				}
			}
			break;
			case MOVE_DOWN:
			{
				// for each column
				for( int j=0; j<4; j++ ) {
					// create a row from column values
					int row[] = new int[4];
					for( int i=0; i<4; i++ ) {
						row[i] = board[i][j];
					}

					// process right move on this row
					int newRow[] = processRightMove(row);

					// copy the values back into the column
					for( int i=0; i<4; i++ ) {
						board[i][j] = newRow[i];
					}
				}
			}
			break;
		}
	}

	public void play() {
		// play the game

		// setup the board - add random 2 and add random 4
		addRandomDigit(2);
		addRandomDigit(4);

		// while the game is not over
		while( !isGameOver() ) {
			// show the board
			showBoard();

			// ask the user to make a move
			char move = getUserMove();

			// process the move
			processMove(move);

			// add random 2/4
			int r = random.nextInt(100);
			if( r%2==0 ) {
				addRandomDigit(2);
			}
			else {
				addRandomDigit(4);
			}
		}

		if( gameWon() ) {
			System.out.println("You WON!");
		}
		else {
			System.out.println("You LOST!");
		}
	}

	static void showArray(int arr[]) {
		System.out.print("[");
		for( int i: arr) {
			System.out.print(i + " ");
		}
		System.out.print("]");
	}

	public static void main(String args[]) {
		GameOf2048ConsoleApp gameOf2048ConsoleApp = new GameOf2048ConsoleApp();
		// gameOf2048ConsoleApp.showBoard();
		// char move = gameOf2048ConsoleApp.getUserMove();
		// System.out.println("User move: " + move);

		// int arr[] = {0,4,4,4};
		// showArray(arr);
		// System.out.print(" on right move is ");
		// showArray(gameOf2048ConsoleApp.processRightMove(arr));
		// System.out.println();
		gameOf2048ConsoleApp.play();
	}
}
