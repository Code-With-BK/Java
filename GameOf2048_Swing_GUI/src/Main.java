import controller.GameOf2048Controller;
import model.GameOf2048;
import view.GameOf2048View;


/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java code to create a Game Of 2048 on Java Swing GUI
 */


public class Main {
	public static void main(String[] args) {
		// create the model
		GameOf2048 gameOf2048 = new GameOf2048();
		
		// create view
		GameOf2048View gameOf2048View = new GameOf2048View();
		
		// create controller
		GameOf2048Controller gameOf2048Controller = new GameOf2048Controller();
		gameOf2048Controller.initModel(gameOf2048);
		gameOf2048Controller.initView(gameOf2048View);
		
		// set the controller to the view
		gameOf2048View.initController(gameOf2048Controller);
	}
}
