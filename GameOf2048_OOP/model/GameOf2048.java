package model;

public class GameOf2048 {
	
	// the constants
	public static final char MOVE_LEFT = 'A';
	public static final char MOVE_RIGHT = 'D';
	public static final char MOVE_UP = 'W';
	public static final char MOVE_DOWN = 'S';
	
	// data members
	private Board board;
	
	// functions
	
	// constructor
	public GameOf2048() {
		board = new Board();
	}
	
	// getter for the board
	public Board getBoard() {
		return board;
	}
	
	// if the user won the game
	public boolean gameWon() {
		return board.searchOnBoard(Board.WINNING_TILE);
	}
	
	// if the game is over
	public boolean isGameOver() {
		// game is over is there is a 2048 tile on the board
		if( gameWon() ) {
			return true;
		}

		// game is not over, if there is a blank tile on the board
		if( board.searchOnBoard(Board.EMPTY_TILE) ) {
			return false;
		}

		// finally, game is not over if user can make a move
		return !userCanMakeAMove();
	}
	
	// if the user can make a move
	public boolean userCanMakeAMove() {
		int board[][] = this.board.getBoard();
		// check (n-1)x(n-1) board
		for( int i=0; i<(Board.SIZE-1); i++ ) {
			for( int j=0; j<(Board.SIZE-1); j++ ){
				// if two adjacent locations have equal value, return true
				if( board[i][j] == board[i][j+1] || 
					board[i][j] == board[i+1][j] 
					) {
					return true;
				}
			}
		}
		// check if two equal adjacent values in the last row
		for( int j=0; j<(Board.SIZE-1); j++ ) {
			if( board[(Board.SIZE-1)][j] == board[(Board.SIZE-1)][j+1] ) {
				return true;
			}
		}

		// check if two equal adjacent values in the last column
		for( int i=0; i<(Board.SIZE-1); i++ ) {
			if( board[i][(Board.SIZE-1)] == board[i+1][(Board.SIZE-1)] ) {
				return true;
			}
		}

		// finally, return false
		return false;
	}
	
	// functions to process the move made by the user
	public int[] processLeftMove(int row[]) {
		// copy non-0 values
		int newRow[] = new int[Board.SIZE];
		int j = 0;
		for( int i=0; i<Board.SIZE; i++ ) {
			if( row[i]!=0 ) {
				newRow[j++] = row[i];
			}
		}

		// merge the values in this new row
		for( int i=0; i<(Board.SIZE-1); i++ ) {
			if( newRow[i]!=0 && newRow[i]==newRow[i+1]) {
				newRow[i] = 2*newRow[i];	// a)
				// copy the remaining values  // b)
				for( j=i+1; j<(Board.SIZE-1); j++ ) {
					newRow[j] = newRow[j+1];
				}
				// c) set the last location of this row to 0
				newRow[(Board.SIZE-1)] = 0;
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
		int newRow[] = new int[Board.SIZE];
		int j = 0;
		for( int i=0; i<Board.SIZE; i++ ) {
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
	
	// to check if the move was made
	private boolean checkMoveMade(int [][]oldBoard, int [][]newBoard) {
		for( int i=0; i<Board.SIZE; i++ ) {
			for( int j=0; j<Board.SIZE; j++ ) {
				if( oldBoard[i][j] != newBoard[i][j] ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean processMove(char move) {
		int [][] board = this.board.getBoard();
		switch(move) {
			case MOVE_LEFT:
			{
				// for each row
				for( int i=0; i<Board.SIZE; i++ ){
					// get the new row
					int newRow[] = processLeftMove(board[i]);
					// copy values from the new row to the row
					for( int j=0; j<Board.SIZE; j++ ) {
						board[i][j] = newRow[j];
					}
				}
			}
			break;
			case MOVE_RIGHT:
			{
				// for each row
				for( int i=0; i<Board.SIZE; i++ ){
					// get the new row
					int newRow[] = processRightMove(board[i]);
					// copy values from the new row to the row
					for( int j=0; j<Board.SIZE; j++ ) {
						board[i][j] = newRow[j];
					}
				}
			}
			break;
			case MOVE_UP:
			{
				// for each column
				for( int j=0; j<Board.SIZE; j++ ) {
					// create a row from column values
					int row[] = new int[Board.SIZE];
					for( int i=0; i<Board.SIZE; i++ ) {
						row[i] = board[i][j];
					}

					// process left move on this row
					int newRow[] = processLeftMove(row);

					// copy the values back into the column
					for( int i=0; i<Board.SIZE; i++ ) {
						board[i][j] = newRow[i];
					}
				}
			}
			break;
			case MOVE_DOWN:
			{
				// for each column
				for( int j=0; j<Board.SIZE; j++ ) {
					// create a row from column values
					int row[] = new int[Board.SIZE];
					for( int i=0; i<Board.SIZE; i++ ) {
						row[i] = board[i][j];
					}

					// process right move on this row
					int newRow[] = processRightMove(row);

					// copy the values back into the column
					for( int i=0; i<Board.SIZE; i++ ) {
						board[i][j] = newRow[i];
					}
				}
			}
			break;
		}
		
		// check if the move was made
		boolean moveMade = checkMoveMade(this.board.getBoard(), board);
		
		// set back the board
		this.board.setBoard(board);
		
		// return the move made or not
		return moveMade;
	}

}
