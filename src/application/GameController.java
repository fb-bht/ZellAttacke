package application;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Fredi Benko
 */
public class GameController {

	// Initialize
	public Canvas gameCanvas;
//	public Pane gamePane;
	public World world;
	public CSprite orbiterP1;
	public CSprite orbiterP2;

	@FXML
	public void initialize() {
		this.world = new World(new Vec2(0.0f, 0.0f));

		// Display configuration
//		this.gameCanvas.widthProperty().bind(gamePane.widthProperty());
//		this.gameCanvas.heightProperty().bind(gamePane.heightProperty());
		Renderer renderer = new Renderer(this.gameCanvas);

		// Boundaries
		renderer.addWall(new Rect(-9f, 100f, 10f, 500f, this.world)); // left
		renderer.addWall(new Rect(599f, 100f, 10f, 500f, this.world)); // right
		
		// Players
		CSprite player1 = new CSprite(new Image(getClass().getResourceAsStream("../img/cell_center.png")), 300, 100, 100, this.world, BodyType.STATIC);
		renderer.addSprite(player1);
		
		CSprite player2 = new CSprite(new Image(getClass().getResourceAsStream("../img/virus_center.png")), 300, 600, 100, this.world, BodyType.STATIC);
		renderer.addSprite(player2);
		
		// Orbiter
		this.orbiterP1 = new CSprite(new Image(getClass().getResourceAsStream("../img/cell_center.png")), 300, 150, 20, this.world, BodyType.DYNAMIC);
		orbiterP1.addJoint(player1);		
		renderer.addSprite(orbiterP1);
		
		this.orbiterP2 = new CSprite(new Image(getClass().getResourceAsStream("../img/virus_center.png")), 300, 550, 20, this.world, BodyType.DYNAMIC);
		orbiterP2.addJoint(player2);		
		renderer.addSprite(orbiterP2);
		
		// Entities
		for (int i = 0; i < 50; i++) {
			renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/cell_center.png")), 280, 300, 20, this.world, BodyType.DYNAMIC));
		}
		for (int i = 0; i < 50; i++) {
			renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/virus_center.png")), 320, 400, 20, this.world, BodyType.DYNAMIC));
		}

		// Game Loop
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				renderer.render();
				world.step((1.0f / 60.0f), 6, 2);
				world.clearForces();
				update();
			}
		}.start();
	}

	// update after user interaction
	protected void update() {
		this.gameCanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				orbiterP1.removeJoint();
				orbiterP2.removeJoint();				
			}});	
	}
	

}
