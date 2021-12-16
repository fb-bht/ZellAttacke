package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameRulesFXController {

	private Node node;
	private Stage stage;
	private Scene scene;
	private FXMLLoader fxmlLoader;
	private Parent root;
	
	@FXML
	private Button btnOK;
	
	@FXML
	protected void goBackToStart(ActionEvent e) {
		try {
			node = (Node) e.getSource();
			stage = (Stage) node.getScene().getWindow();
			scene = stage.getScene();
			fxmlLoader = new FXMLLoader(getClass().getResource("../img/startFX.fxml"));
			root = (Parent) fxmlLoader.load();
			scene.setRoot(root);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }         
	}
}
