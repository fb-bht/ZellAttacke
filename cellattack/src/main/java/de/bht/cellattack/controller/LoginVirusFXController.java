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

public class LoginVirusFXController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnCell;

    @FXML
    private Button btnLoginVirus;

    @FXML
    private Button btnBackVirus;

    @FXML
    private Button btnPlayVirus;
    
    @FXML
    void openLoginVirus(ActionEvent e) throws IOException {
    	stage = (Stage) btnLoginVirus.getScene().getWindow();
        stage.close();
		
        root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CellAttack");
        stage.show();  
    }

    @FXML
    void backToAvatarChoice(ActionEvent e) throws IOException {
    	Node node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/avatarchoiceFX.fxml"));
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root); 
    }

    @FXML
    void openGameVirus(ActionEvent e) {
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