package de.bht.cellattack.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jbox2d.dynamics.BodyType;

import de.bht.cellattack.model.dto.User;
import de.bht.cellattack.application.Main;
import de.bht.cellattack.box2D.Box2dUtils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * The Arena Class holds the Objects and the game-logic. Represents the
 * Model in MVC Pattern
 * 
 * @Author Fredi Benko
 */
public class Arena {

	private ObservableList<GameObject> gameObjects;
	private SimpleIntegerProperty scorePlayer1 = new SimpleIntegerProperty();
	private SimpleIntegerProperty scorePlayer2 = new SimpleIntegerProperty();
	private Sprite player1;
	private Sprite player2;
	public Sprite orbiterP1;
	public Sprite orbiterP2;
	// -----------------Start Dev
	ExecutorService singleThreadPoolPlayer1 = Executors.newSingleThreadExecutor();
	ExecutorService singleThreadPoolPlayer2 = Executors.newSingleThreadExecutor();
	public boolean blockedPlayer1 = false;
	public boolean blockedPlayer2 = false;
	// -----------------End Dev
	public static User tokenPlayer1 = new User();
	public static User tokenPlayer2 = new User();

	public static Box2dUtils box2d;

	public Arena() {
		gameObjects = FXCollections.observableArrayList();
		box2d = Box2dUtils.getInstance();
		box2d.createContactListener(this);
	}

	public SimpleIntegerProperty getScorePlayer1Property() {
		return scorePlayer1;
	}

	public SimpleIntegerProperty getScorePlayer2Property() {
		return scorePlayer2;
	}

	public ObservableList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Box2dUtils getBox2d() {
		return box2d;
	}

	public void placeEntities() {

		// Boundaries
		gameObjects.add(new Rect(-1f, 100f, 10f, 500f)); // left
		gameObjects.add(new Rect(590f, 100f, 10f, 500f)); // right

		// Player1
		player1 = new Sprite(new Image(getClass().getResourceAsStream("/img/cell_center.png")), 300, 100, 100,
				BodyType.STATIC);
		gameObjects.add(player1);
		// Orbiter
		createOrbiterP1();

		// Player2
		player2 = new Sprite(new Image(getClass().getResourceAsStream("/img/virus_center.png")), 300, 600, 100,
				BodyType.STATIC);
		gameObjects.add(player2);
		// Orbiter
		createOrbiterP2();

		// Entities
		for (int i = 0; i < 50; i++) {
			GameObject neutral = new Sprite(new Image(getClass().getResourceAsStream("/img/neutral_center.png")),
					(float) Math.random() * 10 + 300, (float) Math.random() * 10 + 350, 20);
			gameObjects.add(neutral);
			((Sprite) neutral).setEntityType("neutral");
		}
	}

	public void createOrbiterP1() {
		orbiterP1 = new Sprite(new Image(getClass().getResourceAsStream("/img/cell_center.png")), 300, 150, 20);
		orbiterP1.addJoint(player1);
		gameObjects.add(orbiterP1);
		orbiterP1.setEntityType("orbiterP1");
	}

	public void createOrbiterP2() {
		orbiterP2 = new Sprite(new Image(getClass().getResourceAsStream("/img/virus_center.png")), 300, 550, 20);
		orbiterP2.addJoint(player2);
		gameObjects.add(orbiterP2);
		orbiterP2.setEntityType("orbiterP2");
	}

	public void update() {
		int numberOfNeutrals = 0;
		for (GameObject object : gameObjects) {
			if (object.getEntityType() == ("neutral")) {
				numberOfNeutrals++;
				Sprite obj = (Sprite) object;
				if (obj.getPos().x < 0 - obj.getRadius() * 2
						|| obj.getPos().x > 400 //Main.WIDTH
						|| obj.getPos().y < 0 - obj.getRadius() * 2
						|| obj.getPos().y > Main.HEIGHT) {
					obj.removeSprite(); // removes Box2D Body
					gameObjects.remove(obj); // removes object from View
				}
			}
		}
		if(numberOfNeutrals < 48) {
			gameOver();
		}
	}

	private void gameOver() {
		System.out.println("GameOver");
	}

	public void np1Collision() {
		scorePlayer1.set(scorePlayer1.get() + 1);
		System.out.println("np1Collision" + scorePlayer1);
	}

	public void np2Collision() {
		scorePlayer2.set(scorePlayer2.get() + 1);
		System.out.println("np2Collision" + scorePlayer2);
	}

	public void ppCollision() {
		System.out.println("ppCollision");
	}

	public void singleThreadExecutorPlayer1() {
		blockedPlayer1 = true;
		singleThreadPoolPlayer1.execute(new CountDown());
		blockedPlayer1 = false;
	}

	/*
	 * starts a Delay
	 */
	public void startCountDown() {
		System.out.println("startCountDown");
		singleThreadExecutorPlayer1();
		// Thread clock = new CountDownThread();
		// clock.start();
		// orbiterP1.removeJoint();
		// orbiterP2.removeJoint();
	}

}
