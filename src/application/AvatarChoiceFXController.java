package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.Renderer;
import controller.GameController;

public class AvatarChoiceFXController {

	private Node node;
	private Stage stage;
	
	@FXML
	private Button btnCell;
	@FXML
	private Button btnVirus;
	
	@FXML
	protected void chooseCell(ActionEvent e) {
		// Creating an instance of the view
		Renderer gameView = new Renderer();

		// Creating an instance of the controller passing the view
		new GameController(gameView);
	
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		stage.getScene();
		stage.setScene(new Scene(gameView.getViewPane()));
		stage.sizeToScene();
	      
	}
	
	@FXML
	protected void chooseVirus(ActionEvent e) {
		// Creating an instance of the view
		Renderer gameView = new Renderer();

		// Creating an instance of the controller passing the view
		new GameController(gameView);
		
		node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		stage.getScene();
		stage.setScene(new Scene(gameView.getViewPane()));
		stage.sizeToScene();         
	}
}
