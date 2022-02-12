package de.bht.cellattack.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AvatarChoiceFXController {

	private Stage stage;
	private Scene scene;
	private Parent root;
		
	@FXML
	private Button btnCell;
	@FXML
	private Button btnVirus;
		
	@FXML
	protected void chooseCell(ActionEvent e) throws IOException {
		Node node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginCellFX.fxml"));
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root); 
	}
	
	@FXML
	protected void chooseVirus(ActionEvent e) throws IOException {
		Node node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginVirusFX.fxml"));
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root); 
	}
}
