package de.bht.cellattack.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.bht.cellattack.application.Main;
import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.model.Arena;
import de.bht.cellattack.model.dto.User;


/**
 * AvatarFXController
 * 
 * @author Fredi Benko
 */
public class AvatarFXController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML private Button btnLoginPlayer1;
	@FXML private Button btnLoginPlayer2;
	@FXML private Button btnLogoutPlayer1;
	@FXML private Button btnLogoutPlayer2;
	@FXML private Button btnDetailPlayer1;
	@FXML private Button btnDetailPlayer2;
	@FXML private Button btnPlayGame;
	@FXML private Label header;

	
	
	/** 
	 * Initialzes the View
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initilize Method called");
		
		if(Arena.refPlayer1.getToken() != null) {
			btnLoginPlayer1.setVisible(false);
			btnLogoutPlayer1.setVisible(true);
			btnDetailPlayer1.setVisible(true);
		}
		if(Arena.refPlayer2.getToken() != null) {
			btnLoginPlayer2.setVisible(false);
			btnLogoutPlayer2.setVisible(true);
			btnDetailPlayer2.setVisible(true);
		}
		if(Arena.refPlayer1.getToken() != null && Arena.refPlayer2.getToken() != null) {
			btnPlayGame.setVisible(true);
			header.setVisible(false);
		}	
	}
	
	@FXML protected void loginPlayer1(ActionEvent e) throws IOException {
		loadView(e, Arena.refPlayer1, "/fxml/LoginView.fxml");
	}

	@FXML protected void loginPlayer2(ActionEvent e) throws IOException {
		loadView(e, Arena.refPlayer2, "/fxml/LoginView.fxml");
	}
	
	@FXML protected void getDetailsPlayer1(ActionEvent e) throws IOException {
		loadView(e, Arena.refPlayer1, "/fxml/DashboardView.fxml");
	}
	
	@FXML protected void getDetailsPlayer2(ActionEvent e) throws IOException {
		loadView(e, Arena.refPlayer2, "/fxml/DashboardView.fxml");
	}
	
	
	/** 
	 * View-Loader
	 * 
	 * @param e
	 * @param player
	 * @param path
	 * @throws IOException
	 */
	private void loadView(ActionEvent e, User player, String path) throws IOException {
		Node node = (Node) e.getSource();
		stage = (Stage) node.getScene().getWindow();
		scene = stage.getScene();
		FXMLLoader fxmlLoader = null;
		if(path.substring(6,11).equals("Login")) {
			LoginController ctrl = new LoginController(player);
			fxmlLoader = new FXMLLoader(getClass().getResource(path));
			fxmlLoader.setController(ctrl);
		} else if(path.substring(6, 11).equals("Dashb")) {
			DashboardFXController ctrl = new DashboardFXController(player);
			fxmlLoader = new FXMLLoader(getClass().getResource(path));
			fxmlLoader.setController(ctrl);
		} else {
			fxmlLoader = new FXMLLoader(getClass().getResource(path));
		}
		root = (Parent) fxmlLoader.load();
		scene.setRoot(root);
	}

	/** 
	 * Button Action
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML protected void logoutPlayer1(ActionEvent e) throws IOException {
		Arena.refPlayer1.setToken(null);
		// show info message
		Node node = (Node) e.getSource();
		Window window = node.getScene().getWindow();
        System.out.println("Logout Player1 erfolgreich");
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information", "Logout Player1 erfolgreich");
		// hide buttons
		Platform.runLater(()->{
    		btnLoginPlayer1.setVisible(true);
			btnLogoutPlayer1.setVisible(false);
			btnDetailPlayer1.setVisible(false);
			btnPlayGame.setVisible(false);
			header.setVisible(true);
		});	
	}

	/** 
	 * Button Action
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML protected void logoutPlayer2(ActionEvent e) throws IOException {
		Arena.refPlayer2.setToken(null); 
		// show info message
		Node node = (Node) e.getSource();
		Window window = node.getScene().getWindow();
        System.out.println("Logout Player2 erfolgreich");
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information", "Logout Player2 erfolgreich");
		// hide buttons
		Platform.runLater(()->{
    		btnLoginPlayer2.setVisible(true);
			btnLogoutPlayer2.setVisible(false);
			btnDetailPlayer2.setVisible(false);
			btnPlayGame.setVisible(false);
			header.setVisible(true);
		});	
	}

	/** 
	 * Button Action
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML protected void playGame(ActionEvent e) throws IOException {
		Main game = new Main();
		Stage stage = new Stage();
		game.start(stage);
	}
	
	/** 
	 * Button Action
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML protected void backToMenueFX( ActionEvent e) throws IOException {
		Node node = (Node) e.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Scene scene = stage.getScene();
		FXMLLoader	fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menueFX.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		scene.setRoot(root);
	}

}
