package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.GameOf2048Controller;
import model.Board;
import model.GameOf2048;

public class GameOf2048View extends JFrame {
	
	// constants
	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 100;
	private static final int WINDOW_WIDTH = TILE_WIDTH * Board.SIZE + 50;
	private static final int WINDOW_HEIGHT = TILE_HEIGHT * Board.SIZE + 100;
	
	// colors
	private static final Color COLOR_BLANK_TILE = new Color(197, 183, 170);
	private static final Color COLOR_2 = new Color(240, 240, 240);
	private static final Color COLOR_4 = new Color(237, 224, 200);
	private static final Color COLOR_8 = new Color(242, 177, 121);
	private static final Color COLOR_16 = new Color(245, 149, 99);
	private static final Color COLOR_32 = new Color(246, 124, 95);
	private static final Color COLOR_64 = new Color(246, 94, 59);
	private static final Color COLOR_128 = new Color(237, 207, 114);
	private static final Color COLOR_256 = new Color(237, 204, 97);
	private static final Color COLOR_512 = new Color(237, 200, 80);
	private static final Color COLOR_1024 = new Color(237, 197, 63);
	private static final Color COLOR_2048 = new Color(237, 194, 46);
	
	private class GameOf2048Tile extends JLabel {
		
		// the constructor
		public GameOf2048Tile() {
			super("", SwingConstants.CENTER);
			
			// set opaque
			setOpaque(true);
			
			// set the size 
			setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
			
			// set the border
			setBorder(BorderFactory.createLineBorder(new Color(147, 133, 120), 3));
			
			// set the background color to blank
			setBackground(COLOR_BLANK_TILE);
			
			// set font
			setFont(new Font("Serif", Font.BOLD, 40));
		}
		
		public void setNumber(int n) {
			// set the number on the label
			if( n==0 ) {
				setText("");
			}
			else {
				setText("" + n);
			}
			
			// set the background color
			switch(n) {
			case 0:
				setBackground(COLOR_BLANK_TILE);
				break;
			case 2:
				setBackground(COLOR_2);
				break;
			case 4:
				setBackground(COLOR_4);
				break;
			case 8:
				setBackground(COLOR_8);
				break;
			case 16:
				setBackground(COLOR_16);
				break;
			case 32:
				setBackground(COLOR_32);
				break;
			case 64:
				setBackground(COLOR_64);
				break;
			case 128:
				setBackground(COLOR_128);
				break;
			case 256:
				setBackground(COLOR_256);
				break;
			case 512:
				setBackground(COLOR_512);
				break;
			case 1024:
				setBackground(COLOR_1024);
				break;
			case 2048:
				setBackground(COLOR_2048);
				break;
			
			}
		}
	}
	
	// the label to show the score
	private JLabel labelScore;
	
	// the 2D array of tiles
	private GameOf2048Tile tiles[][];
	
	// the reference to the controller
	private GameOf2048Controller gameOf2048Controller;
	
	public GameOf2048View() {
		
		// setup the view
		setup();
				
		// set size
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		// set title
		setTitle("Game of 2048");
		
		// set default operation on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set resizeable
		setResizable(false);
		
		// set visible
		setVisible(true);
	}
	
	// to init the controller
	public void initController(GameOf2048Controller gameOf2048Controller) {
		this.gameOf2048Controller =  gameOf2048Controller;
	}
	
	private void setup() {
		// the main panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		// the top panel
		JPanel topPanel = new JPanel(new FlowLayout());
		labelScore = new JLabel("Score: 0");
		topPanel.add(labelScore);
		
		// the center panel
		JPanel centerPanel = new JPanel(new GridLayout(Board.SIZE, Board.SIZE));
		tiles = new GameOf2048Tile[Board.SIZE][Board.SIZE];
		for( int i=0; i<Board.SIZE; i++ ) {
			for( int j=0; j<Board.SIZE; j++ ) {
				tiles[i][j] = new GameOf2048Tile();
				centerPanel.add(tiles[i][j]);
			}
		}
		
		// just to test the bg colors
//		tiles[0][0].setNumber(2);
//		tiles[0][1].setNumber(4);
//		tiles[0][2].setNumber(8);
//		tiles[0][3].setNumber(16);
//		tiles[1][0].setNumber(32);
//		tiles[1][1].setNumber(64);
//		tiles[1][2].setNumber(128);
//		tiles[1][3].setNumber(256);
//		tiles[2][1].setNumber(512);
//		tiles[2][2].setNumber(1024);
//		tiles[2][3].setNumber(2048);
//		
//		tiles[1][2].setNumber(0);
		
		// add the top and the center panels to main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		// pass the input from the user to the controller
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO: Add for keys W/S/A/D
				// on up arrow key pressed
				if( e.getKeyCode()==KeyEvent.VK_UP ) {
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_UP);
				}
				
				// on down arrow key pressed
				if( e.getKeyCode()==KeyEvent.VK_DOWN ) {
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_DOWN);
				}
				
				// on left arrow key pressed
				if( e.getKeyCode()==KeyEvent.VK_LEFT ) {
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_LEFT);
				}
				
				// on right arrow key pressed
				if( e.getKeyCode()==KeyEvent.VK_RIGHT ) {
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_RIGHT);
				}
				
			}
		});
		
		// set this main panel as the content of this JFrame
		setContentPane(mainPanel);
		
	}
	
	// to update the board
	public void updateBoard(Board boardObject, int score) {
		int [][] board = boardObject.getBoard();
		for( int i=0; i<Board.SIZE; i++ ) {
			for( int j=0; j<Board.SIZE; j++ ) {
				tiles[i][j].setNumber(board[i][j]);
			}
		}
		labelScore.setText("Score: " + score);
	}
	
	// to show game result
	public void showGameResult(String message) {
		JOptionPane.showMessageDialog(this, message, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
