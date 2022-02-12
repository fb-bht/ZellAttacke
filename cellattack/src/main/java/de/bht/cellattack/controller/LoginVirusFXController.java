package de.bht.cellattack.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    void openLoginVirus(ActionEvent e) throws IOException {
    	stage = (Stage) btnLoginVirus.getScene().getWindow();
        stage.close();
		
        root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CellAttack");
        stage.show();  
    }

}
