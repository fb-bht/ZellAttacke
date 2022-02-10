package de.bht.cellattack.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartFXController {

	private Node node;
	private Stage stage;
	private Scene scene;
	private FXMLLoader fxmlLoader;
	private Parent root;
	
    @FXML
    private Button btnPlay;

    @FXML
    void openMenue(ActionEvent e) throws IOException {
    	node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menueFX.fxml"));
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root);
    }

}
