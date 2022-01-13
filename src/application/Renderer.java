package application;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Fredi Benko
 */
public class Renderer {
	Canvas canvas;
	GraphicsContext context;
	
	ArrayList<CSprite> sprites = new ArrayList<CSprite>();	
	ArrayList<Rect> walls = new ArrayList<Rect>();
	
	public Renderer(Canvas canvas) {
		this.canvas = canvas;
		this.context = canvas.getGraphicsContext2D();
	}
	
	public void addSprite(CSprite sprite) {
		sprites.add(sprite);
	}
	
	public void addWall(Rect wall) {
		walls.add(wall);
	}
	
	public void render() {
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for (CSprite sprite : sprites) {
			context.drawImage(sprite.getImage(), sprite.getPos().x, sprite.getPos().y, sprite.radius, sprite.radius);
		}
		for (Rect wall : walls) {
			context.fillRect(wall.getPos().x, wall.getPos().y, wall.width, wall.height);
		}
	}

}
