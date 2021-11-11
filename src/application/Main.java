package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Fredi Benko
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CellAttack");

			Group root = new Group();
			Scene scene = new Scene(root);
			scene.setFill(Color.BLACK);
			primaryStage.setScene(scene);

			Canvas canvas = new Canvas(600, 920);
			root.getChildren().add(canvas);

			GraphicsContext gc = canvas.getGraphicsContext2D();

			Sprite cell = new Sprite();
			cell.setImage("img/cell_center.png", 100.0);
			cell.setPosition(250, 50);
			Sprite virus = new Sprite();
			virus.setImage("img/virus_center.png", 100.0);
			virus.setPosition(250, 770);

			ArrayList<Sprite> cellColiderList = new ArrayList<Sprite>();

			for (int i = 0; i < 5; i++) {
				Sprite colider = new Sprite();
				colider.setImage("img/cell_center.png", 20.0);
				double px = 500 * Math.random();
				double py = 300 * Math.random() + 300;
				colider.setPosition(px, py);
				cellColiderList.add(colider);
			}
			
			ArrayList<Sprite> virusColiderList = new ArrayList<Sprite>();

			for (int i = 0; i < 5; i++) {
				Sprite colider = new Sprite();
				colider.setImage("img/virus_center.png", 20.0);
				double px = 500 * Math.random();
				double py = 300 * Math.random() + 300;
				colider.setPosition(px, py);
				virusColiderList.add(colider);
			}

			final long lastNanoTime = System.nanoTime();

			new AnimationTimer() {
				public void handle(long currentNanoTime) {
					double t = (currentNanoTime - lastNanoTime) / 1000000000.0;

					gc.clearRect(0, 0, 600, 920);

					cell.render(gc);
					virus.render(gc);

					for (Sprite colider : cellColiderList) {
						Vector2 cPos = colider.getPosition();
						colider.setPosition(cPos.x + Math.random() * 1, cPos.y + Math.random() * 1);
						colider.render(gc);
					}
					
					for (Sprite colider : virusColiderList) {
						Vector2 cPos = colider.getPosition();
						colider.setPosition(cPos.x - Math.random() * 1, cPos.y - Math.random() * 1);
						colider.render(gc);
					}
				}
			}.start();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
