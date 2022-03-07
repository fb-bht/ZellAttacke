package de.bht.cellattack.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.bht.cellattack.animation.GameLoopTimer;
import de.bht.cellattack.model.Arena;
import de.bht.cellattack.model.dto.RestApi;
import de.bht.cellattack.model.dto.User;
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

	private GameLoopTimer timer;
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(2);
	private DoubleProperty progressPlayer1 = new SimpleDoubleProperty(1); //TODO
	private Task<Void> taskCountDownPlayer1;
	private DoubleProperty progressPlayer2 = new SimpleDoubleProperty(1); //TODO
	private Task<Void> taskCountDownPlayer2;


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
		view.getGameOver().setOnAction(event -> { //TODO
			timer.stop();
			// write winning score to database
			saveWinningScore(Arena.refPlayer1);
			saveWinningScore(Arena.refPlayer2);
			threadPool.shutdown();
			closeWindow();	
		});


		
		
		view.getCountDownPlayer1().progressProperty().bind(progressPlayer1); //TODO
		view.getCountDownPlayer2().progressProperty().bind(progressPlayer2); //TODO
	}
	


	private void play() {

		
		model.placeEntities();
//////////////////////////////////
		timer = new GameLoopTimer() {
			@Override
			public void tick(float secondsSinceLastFrame) {
				if(model.getGameState()) {
					view.render();
					Arena.box2d.step();
					model.update();
					handleInput();
				}
				if (!model.getGameState()) {			
					view.getGameOver().setVisible(true);
				}
			}

		};
		timer.start();

/////////////////////////////////
		// // Game Loop
		// new AnimationTimer() {
		// 	public void handle(long currentNanoTime) {
		// 		if(model.getGameState()) {  //TODO
		// 			view.render();
		// 			Arena.box2d.step();
		// 			model.update();
		// 			handleInput();
		// 		} else {
		// 			// show gameOver Label in center of screen
		// 			view.getGameOver().setVisible(true);
		// 			// try {
		// 			// 	TimeUnit.SECONDS.sleep(2);
		// 			// } catch (InterruptedException ie) {
		// 			// 	Thread.currentThread().interrupt();
		// 			// }
		// 			this.stop();
					
		// 		}
		// 	}
		// }.start();
	}

	// handling user interaction
	private void handleInput() {
		view.getViewPane().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (model.getGameState()) {
					if (event.getCode() == KeyCode.C && !Arena.refPlayer1.isBlocked()) {
						System.out.println("CKey");
						model.orbiterP1.removeJoint();
						taskCountDownPlayer1 = startCountDown(Arena.refPlayer1);
						progressPlayer1.bind(taskCountDownPlayer1.progressProperty());
						threadPool.execute(taskCountDownPlayer1);
						model.createOrbiterP1();
					}
					if (event.getCode() == KeyCode.V && !Arena.refPlayer2.isBlocked()) {
						System.out.println("VKey");
						model.orbiterP2.removeJoint();
						// model.countDownThreadExecutorPlayer2();
						taskCountDownPlayer2 = startCountDown(Arena.refPlayer2);
						progressPlayer2.bind(taskCountDownPlayer2.progressProperty());
						threadPool.execute(taskCountDownPlayer2);
						model.createOrbiterP2();
					}
				}
			}
		});
	}

	private Task<Void> startCountDown(User player) {
		return new Task<Void>() {
			@Override
			public Void call() throws Exception {
				player.setBlocked(true);
				final int max = 100;
				final int interval = 25;
				for (int i = max; i >= 0; i--) {
					Thread.sleep(interval);
					updateProgress(i, max);
				}
				player.setBlocked(false);
				return null;
			}
		};		
	}

	public void closeWindow() {
			Stage stagecurrent = (Stage) view.getGameOver().getScene().getWindow();
			stagecurrent.close();
	}

	private void saveWinningScore(User playerRef) {
		String userResponse = RestApi.sentScoreToAPI(playerRef.getGameScore(), playerRef.getToken());
        if (userResponse == null) {
            throw new RuntimeException("Es ist etwas schiefgelaufen. Bitte noch einmal versuchen.");
        }
		System.out.println(userResponse);
	}

}


