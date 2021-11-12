package application;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;

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
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("startfx.fxml"));
		VBox vbox = new VBox(root);
		Scene scene = new Scene(vbox);
		stage.setTitle("CellAttack");
		
		//StartFXController startcont = new StartFXController();
		
		stage.setScene(scene);
		stage.show();
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
