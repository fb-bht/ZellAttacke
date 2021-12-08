package application;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * @author Fredi Benko
 */
public class GameController {

	// Initialize
	public Canvas gameCanvas;
//	public Pane gamePane;
	public World world;

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
		renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/cell_center.png")), 300, 100, 100, this.world, BodyType.STATIC));
		renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/virus_center.png")), 300, 600, 100, this.world, BodyType.STATIC));
		
//		renderer.addWall(new Rect(250, 300, 100, 100, this.world));

		// Entities
		for (int i = 0; i < 50; i++) {
			renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/cell_center.png")), 250, 300, 20, this.world, BodyType.DYNAMIC));
		}
		for (int i = 0; i < 50; i++) {
			renderer.addSprite(new CSprite(new Image(getClass().getResourceAsStream("../img/virus_center.png")), 350, 400, 20, this.world, BodyType.DYNAMIC));
		}

		// Game Loop
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				renderer.render();
				world.step((1.0f / 60.0f), 6, 2);
				world.clearForces();
			}
		}.start();
	}

}
