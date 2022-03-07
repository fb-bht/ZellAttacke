// package de.bht.cellattack.controller;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.stage.Stage;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ResourceBundle;

// import de.bht.cellattack.model.Arena;

// public class AvatarChoiceFXController implements Initializable {

// 	private Stage stage;
// 	private Scene scene;
// 	private Parent root;

// 	@FXML
// 	private Button btnCell;
// 	@FXML
// 	private Button btnVirus;

// 	@FXML
// 	protected void chooseCell(ActionEvent e) throws IOException {
// 		Node node = (Node) e.getSource();
// 		stage = (Stage) node.getScene().getWindow();
// 		scene = stage.getScene();
// 		FXMLLoader fxmlLoader = null;
// 		if (Arena.readyPlayer1 == null) {
// 			LoginController ctrl = new LoginController("p1");
// 			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
// 			fxmlLoader.setController(ctrl);
// 		} else {
// 			DashboardFXController ctrl = new DashboardFXController("token Reference");
// 			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardView.fxml"));
// 			fxmlLoader.setController(ctrl);
// 		}
// 		root = (Parent) fxmlLoader.load();
// 		scene.setRoot(root);
// 		// if Arena.player1 == null; then LoginView.fxml; else dashboard wit
// 	}

// 	@FXML
// 	protected void chooseVirus(ActionEvent e) throws IOException {
// 		Node node = (Node) e.getSource();
// 		stage = (Stage) node.getScene().getWindow();
// 		scene = stage.getScene();
// 		FXMLLoader fxmlLoader = null;
// 		if (Arena.readyPlayer1 == null) {
// 			LoginController ctrl = new LoginController("p2");
// 			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
// 			fxmlLoader.setController(ctrl);
// 		} else {
// 			DashboardFXController ctrl = new DashboardFXController("token Reference");
// 			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardView.fxml"));
// 			fxmlLoader.setController(ctrl);
// 		}
// 		root = (Parent) fxmlLoader.load();
// 		scene.setRoot(root);
// 	}

// 	@Override
// 	public void initialize(URL location, ResourceBundle resources) {
// 		if(Arena.readyPlayer1 == null) {
// 			btnCell.setVisible(false);
// 		}	
// 	}
// }
