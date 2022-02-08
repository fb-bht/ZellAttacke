package de.bht.cellattack.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * @author Stefanie S.
 *
 */
public class StartWinFXController implements Initializable {

	@FXML 
	private AnchorPane rootPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootPane.setOpacity(0.5);
		sceneFadeIn();
	}
	
	private void sceneFadeIn() {
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.millis(1000));
		ft.setNode(rootPane);
		ft.setFromValue(0.5);
		ft.setToValue(1);
		ft.play();
	}
}
