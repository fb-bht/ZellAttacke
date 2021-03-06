package de.bht.cellattack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Cell-Attack: Game Application
 * 
 * @author Stefanie S.
 */
public class Start extends Application {

	@Override
	public void start(Stage stage) {
		try {
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startFX.fxml"));
	        final Parent root = (Parent) loader.load();
	        Scene scene = new Scene(root);
	        stage.setTitle("CellAttack");
	        stage.setScene(scene);           
	        stage.show();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Entry point for the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
