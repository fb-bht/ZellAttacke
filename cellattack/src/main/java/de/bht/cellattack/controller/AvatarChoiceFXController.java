package de.bht.cellattack.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	private Button btnPlayCell;
	@FXML
	private Button btnPlayVirus;
	
	@FXML
	protected void chooseCell(ActionEvent e) throws IOException {
		stage = (Stage) btnCell.getScene().getWindow();
        stage.close();
		
        root = FXMLLoader.load(getClass().getResource("/fxml/DashCellFX.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CellAttack");
        stage.show();     
	}
	
	@FXML
	protected void chooseVirus(ActionEvent e) throws IOException {
		stage = (Stage) btnVirus.getScene().getWindow();
        stage.close();
		
        root = FXMLLoader.load(getClass().getResource("/fxml/DashVirusFX.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CellAttack");
        stage.show();         
	}
	
	 @FXML
	 protected void showGameFieldCell(ActionEvent e) {
		 btnPlayCell.setVisible(true);
	 }

	 @FXML
	 protected void showGameFieldVirus(ActionEvent e) {
		 btnPlayVirus.setVisible(true);
	 }

}
