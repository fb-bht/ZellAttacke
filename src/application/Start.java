package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Stefanie S.
 *
 */
public class Start extends Application {

	@Override
	public void start(Stage stage) {
		try {
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("../img/startFX.fxml"));
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
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
