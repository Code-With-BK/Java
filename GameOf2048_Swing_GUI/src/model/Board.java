package model;
import java.util.Random;

public class Board {
	
	// the constants
	public static final int SIZE = 4;
	public static final Random RANDOM = new Random();
	
	// constants
	public static final int WINNING_TILE = 2048;
	public static final int EMPTY_TILE = 0;
		
	
	// data members
	private int board[][];
	
	// functions
	
	// constructor
	public Board() {
		// all locations of this 2D array is 0
		board = new int[SIZE][SIZE];
		
		// add a random 2 and a random 4
		addRandomDigit(2);
		addRandomDigit(4);
	}
	
	// set the board
	public void setBoard(int board[][]) {
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	// get the board
	public int[][] getBoard() {
		// create a copy of the board and return
		int [][] boardCopy = new int[SIZE][SIZE];
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				boardCopy[i][j] = this.board[i][j];
			}
		}
		return boardCopy;
	}
	
	// search for a number on the board
	public boolean searchOnBoard(int x) {
		for( int i=0; i<SIZE; i++ ) {
			for( int j=0; j<SIZE; j++ ) {
				if( board[i][j] == x ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addRandomDigit() {
		// add a 2 on the board, if the next random integer is even
		if( RANDOM.nextInt()%2==0 ) {
			addRandomDigit(2);
		}
		// else, add a 4 on the board, if the next random integer is odd
		else {
			addRandomDigit(4);
		}
	}
	
	// add a random digit on the board
	private void addRandomDigit(int digit) {
		// add random digit on the board

		// generate a pair of i,j
		int i = RANDOM.nextInt(SIZE);
		int j = RANDOM.nextInt(SIZE);

		// generate i,j as long as this location on the board is occupied
		while( board[i][j] != 0 ) {
			i = RANDOM.nextInt(SIZE);;
			j = RANDOM.nextInt(SIZE);
		}
		
		// set the digit at this location
		board[i][j] = digit;
	}

}
