package de.bht.cellattack.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import de.bht.cellattack.model.Arena;
import de.bht.cellattack.view.Renderer;

/**
 * Controller Class for handling UserInput and connecting view and model
 * 
 * @author Fredi Benko
 */
public class GameController {

	// Model
	private Arena model;
	
	// View
	private Renderer view;

	public GameController(Renderer view) {
		this.view = view;
		setView(view);
		play();
	}
	

	private void setView(Renderer view) {

		// Creating a instance of the Model
		model = new Arena();

		// Link View with Model
		Bindings.bindContent(view.getViewList(), model.getGameObjects());
		view.getScoreTextPlayer1().textProperty().bind(model.getScorePlayer1Property().asString());
		view.getScoreTextPlayer2().textProperty().bind(model.getScorePlayer2Property().asString());
	}
	
	private void play() {
		
		model.placeEntities();

		// Game Loop
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				view.render();
				Arena.box2d.step();
				handleInput();
			}
		}.start();
	}

	// handling user interaction
	private void handleInput() {

		view.getViewPane().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.C) {
					System.out.println("CKey");
					model.orbiterP1.removeJoint();
					model.startCountDown();
					model.createOrbiterP1();
				}
				if (event.getCode() == KeyCode.V) {
					System.out.println("VKey");
					model.orbiterP2.removeJoint();
					model.startCountDown();
					model.createOrbiterP2();
				}
			}
		});
	}

}
