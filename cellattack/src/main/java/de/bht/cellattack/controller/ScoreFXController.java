package de.bht.cellattack.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.bht.cellattack.model.dto.HighscoreApiResponse;
import de.bht.cellattack.model.dto.RestApi;
import de.bht.cellattack.model.dto.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller for highscore scene
 * 
 * @author Stefanie S.
 * @author Fredi Benko
 */
public class ScoreFXController implements Initializable {
	
	private Node node;
	private Stage stage;
	private Scene scene;
	private FXMLLoader fxmlLoader;
	private Parent root;

	@FXML private TableView<Score> table;
	@FXML private TableColumn<Score, Integer> columnOne;
	@FXML private TableColumn<Score, String> columnTwo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		HighscoreApiResponse response = RestApi.getHighscoreFormAPI();
		if(response == null) {
			throw new RuntimeException("Es ist etwas schiefgelaufen. Bitte noch einmal versuchen.");
		}
        columnOne.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<Score, String>("date"));
		table.getItems().add(response.getHighscore());
	}
	
	
	@FXML
	protected void goBackToStart(ActionEvent e) {
		try {
			node = (Node) e.getSource();
			stage = (Stage) node.getScene().getWindow();
			scene = stage.getScene();
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menueFX.fxml"));
			root = (Parent) fxmlLoader.load();
			scene.setRoot(root);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }         
	}
}