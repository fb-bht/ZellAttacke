package de.bht.cellattack.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Scene of the highscore
 * 
 * @author Stefanie S.
 *
 */
public class Score extends Application {

	@Override
	public void start(Stage stage) {
		try {
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scoreFX.fxml"));
	        final Parent root = (Parent) loader.load();
	        Scene scene = new Scene(root);
	        stage.setTitle("CellAttack");
	        stage.setScene(scene);           
	        stage.show();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
