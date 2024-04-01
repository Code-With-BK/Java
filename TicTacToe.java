import java.util.Scanner;

/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java code to create a TicTacToe Game on console/command line
 */

public class TicTacToe {
	
	public static final int SIZE = 3;
	public static final char PLAYER_X = 'X';
	public static final char PLAYER_O = 'O';
	
	private char board[][];
	
	private char currentPlayer;
	
	private Scanner scanner;
	
	public TicTacToe() {
		board = new char[SIZE][SIZE];
		
		// we intialize the board from digits 0-8
		int boxNumber = 0;
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				// 3 -> '3'
				board[i][j] = (char)('0' + boxNumber++);
			}
		}
		
		// player X starts
		currentPlayer = PLAYER_X;
		
		// create the scanner
		scanner = new Scanner(System.in);
	}
	
	/*
	 * Method to print the board
	 * 
	 *  0 | 1 | 2 
	 *  ---------
	 *  3 | 4 | 5
	 *  ---------
	 *  6 | 7 | 8
	 */
	public void print() {
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				System.out.printf(" %s ", "" + board[i][j]);
				if( j+1<SIZE ) {
					System.out.print("|");
				}
				
			}
			System.out.println();
			// print separator
			if( i+1<SIZE ) {
				System.out.println("-----------");
			}
		}
	}
	
	/*
	 * Method to check if any player won
	 * 
	 */
	public boolean checkWinner(char player) {
		// check for horizontal
		for( int i=0; i<SIZE; i++ ) {
			if( board[i][0]==player && 
				board[i][1]==player && 
				board[i][2]==player ) {
				return true;
			}
		}
		
		// check for vertical
		for( int j=0; j<SIZE; j++ ) {
			if( board[0][j]==player && 
				board[1][j]==player && 
				board[2][j]==player ) {
					return true;
			}
		}
		
		// check diagonals
		if( board[0][0]==player && 
			board[1][1]==player && 
			board[2][2]==player) {
			return true;
		}
		if( board[2][0]==player && 
			board[1][1]==player && 
			board[0][2]==player) {
				return true;
		}
		
		// finally,return false
		return false;
	}
	
	/*
	 * Method to check if the board is full
	 */
	public boolean isBoardFull() {
		// board is full, if there is not digit left on the board
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				if( Character.isDigit(board[i][j]) ) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Method to check if game is over
	 * 
	 */
	public boolean isGameOver() {
		return checkWinner(PLAYER_X) || checkWinner(PLAYER_O) || 
				isBoardFull();
	}
	
	public int makeMove() {
		// making a move is asking the user to enter a box number
		// where the current player will place their marker
		System.out.print("Player " + currentPlayer + " turn. Choose a box number: ");
		int boxNumber = scanner.nextInt();
		
		// validate the box number
		if( boxNumber<0 || boxNumber>8 ) {
			System.out.println("Input box number is invalid! Try again!");
			return makeMove();
		}
		
		// check if the input box number is empty
		int i = boxNumber/SIZE;
		int j = boxNumber%SIZE;
		if( board[i][j]==PLAYER_X || board[i][j]==PLAYER_O ) {
			System.out.println("Input box already occupied! Try again!");
			return makeMove();
		}
		
		// return the box number
		return boxNumber;
	}
	
	/*
	 * Method to play the game
	 */
	public void play() {
		
		// we play as long as the game is not over
		while( !isGameOver() ) {
			// print the board
			print();
			
			// change line
			System.out.println();
			
			// ask the current player to make a move
			int boxNumber = makeMove();
			// place the marker for the current player here
			int i = boxNumber/SIZE;
			int j = boxNumber%SIZE;
			board[i][j] = currentPlayer;
			
			// switch the player
			currentPlayer = currentPlayer==PLAYER_X? PLAYER_O : PLAYER_X;
		}
		
		// finally print the winner
		if( checkWinner(PLAYER_X)) {
			System.out.println("Player X won!");
		}
		else if( checkWinner(PLAYER_O) ) {
			System.out.println("Player O won!");
		}
		else {
			System.out.println("Game drawn!");
		}
	}
	
	public static void main(String args[]) {
		TicTacToe ticTacToe = new TicTacToe();
		ticTacToe.play();
	}
	
}
