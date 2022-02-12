package de.bht.cellattack.controller;

import java.io.IOException;

import de.bht.cellattack.view.Renderer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginCellFXController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnLoginCell;
    
    @FXML
    private Button btnBackCell;

    @FXML
    private Button btnPlayCell;

    @FXML
    private Button btnVirus;

    @FXML
    protected void openLoginCell(ActionEvent e) throws IOException {
    	stage = (Stage) btnLoginCell.getScene().getWindow();
        stage.close();
		
        root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CellAttack");
        stage.show();  
    }

    @FXML
    protected void backToAvatarChoice(ActionEvent e) throws IOException {
    	Node node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/avatarchoiceFX.fxml"));
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root); 
    }

    @FXML
    protected void openGameCell(ActionEvent e) {
    	// Creating an instance of the View
		Renderer gameView = new Renderer();

		// Creating an instance of the Controller passing the View
		new GameController(gameView);

		stage.setTitle("CellAttack");
		stage.setScene(new Scene(gameView.getViewPane()));
		stage.setResizable(false);
		stage.show();
    }
}
