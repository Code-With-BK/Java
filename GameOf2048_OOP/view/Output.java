package view;
import model.Board;

public class Output {
	
	// show the board
	public static void showBoard(Board boardObject) {
		int [][] board = boardObject.getBoard();
		// print the top separator
		for( int i=0; i<Board.SIZE; i++ ) {
			System.out.print("-------");
		}
		System.out.println();

		// print each row
		for( int i=0; i<Board.SIZE; i++ ) {
			// print the blank space before the row
			System.out.print("|");
			for( int j=0; j<Board.SIZE; j++ ) {
				System.out.print("      |");
			}
			System.out.println();

			// print the first pipe symbol
			System.out.print("|");
			for( int j=0; j<Board.SIZE; j++ ) {
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
			for( int j=0; j<Board.SIZE; j++ ) {
				System.out.print("      |");
			}
			System.out.println();

			// print the bottom separator
			for( int j=0; j<Board.SIZE; j++ ) {
				System.out.print("-------");
			}
			System.out.println();
		}
	}
	
	public static void print(String string) {
		System.out.print(string);
	}
	
	public static void println(String string) {
		System.out.println(string);
	}
}
