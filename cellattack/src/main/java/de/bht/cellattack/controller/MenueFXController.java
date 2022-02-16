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

/**
 * Controller for function scene
 * 
 * @author Stefanie S.
 *
 */
public class MenueFXController {

	private Node node;
	private Stage stage;
	private Scene scene;
	private FXMLLoader fxmlLoader;
	private Parent root;
	
	@FXML 
	private Button btnNewGame;
	@FXML 
	private Button btnGamerules;
	@FXML 
	private Button btnScore;
		
	@FXML
	protected void openNewGame(ActionEvent e) {
		try {
			node = (Node) e.getSource();
			stage = (Stage) node.getScene().getWindow();
			scene = stage.getScene();
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/avatarFX.fxml"));
			root = (Parent) fxmlLoader.load();
			scene.setRoot(root);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }         
	}
	
	@FXML
	protected void howToPlay(ActionEvent e) {
		try {
			node = (Node) e.getSource();
			stage = (Stage) node.getScene().getWindow();
			scene = stage.getScene();
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gamerulesFX.fxml"));
			root = (Parent) fxmlLoader.load();
			scene.setRoot(root);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
	
	@FXML
	protected void openScore(ActionEvent e) {
		try {
			node = (Node) e.getSource();
			stage = (Stage) node.getScene().getWindow();
			scene = stage.getScene();
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scoreFX.fxml"));
			root = (Parent) fxmlLoader.load();
			scene.setRoot(root);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
}
