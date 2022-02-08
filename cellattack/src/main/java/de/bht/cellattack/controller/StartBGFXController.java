package de.bht.cellattack.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Stefanie S.
 *
 */
public class StartBGFXController implements Initializable {

	@FXML 
	private AnchorPane rootPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Timeline tl = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
			sceneFadeOut();
		}));
		
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
	}
	
	private void sceneFadeOut() {
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.millis(1000));
		ft.setNode(rootPane);
		ft.setFromValue(1);
		ft.setToValue(0.5);
		ft.setOnFinished(e -> {
			openNextScene();
		});
		ft.play();
	}

	private void openNextScene() {
		try {
			Parent nextView;
			nextView = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/startwinFX.fxml"));
			
			Scene newScene = new Scene(nextView);
			Stage current = (Stage) rootPane.getScene().getWindow();
			
			current.setScene(newScene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
