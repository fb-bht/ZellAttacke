package de.bht.cellattack.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import de.bht.cellattack.controller.GameController;
import de.bht.cellattack.view.Renderer;

/**
 * CellAttack Game 
 * Main Class for starting the Gamefield
 * 
 * @author Fredi Benko
 */
public class Main extends Application {
	
	public final static int WIDTH = 600;
	public final static int HEIGHT = 700;
	public final static String SERVER_URL = "http://localhost:8080";


	
	/** 
	 * Starts the GameField
	 * 
	 * @param stage
	 * @throws IOException
	 */
	@Override
	public void start(Stage stage) throws IOException {

		// Creating a instance of the View
		Renderer gameView = new Renderer();

		// Creating a instance of the Controller passing the View
		new GameController(gameView);

		stage.setTitle("CellAttack");
		stage.setScene(new Scene(gameView.getViewPane()));
		stage.setResizable(false);
		stage.show();
	}

	
	/** 
	 * Entry Point for Gamefield - only used for development and testing
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
