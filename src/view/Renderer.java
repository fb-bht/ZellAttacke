package view;

import java.util.ArrayList;

import application.Main;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.GameObject;
import model.Rect;
import model.Sprite;

/**
 * Render Class represents the View in MVC
 * 
 * @author Fredi Benko
 */
public class Renderer {

	Parent pane;
	Canvas canvas;
	GraphicsContext context;

	ArrayList<GameObject> viewList = new ArrayList<GameObject>();
	Label ScoreTextPlayer1 = new Label("0");
	Label ScoreTextPlayer2 = new Label("0");

	public Renderer() {
		pane = createView();
	}

	public Parent getViewPane() {
		return pane;
	}
	
	public Label getScoreTextPlayer1() {
		return ScoreTextPlayer1;
	}
	
	public Label getScoreTextPlayer2() {
		return ScoreTextPlayer2;
	}
	
	
	private Parent createView() {
		AnchorPane aPane = new AnchorPane();
		canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		
		Label p1 = new Label("Player 1: "); 
        HBox InfoBoxP1 = new HBox(); 
        InfoBoxP1.getChildren().addAll(p1, ScoreTextPlayer1); 
        AnchorPane.setTopAnchor(InfoBoxP1, 10.0);
        
		Label p2 = new Label("Player 2: "); 
        HBox InfoBoxP2 = new HBox(); 
        InfoBoxP2.getChildren().addAll(p2, ScoreTextPlayer2); 
        AnchorPane.setBottomAnchor(InfoBoxP2, 10.0);
        
        aPane.getChildren().addAll(canvas, InfoBoxP1, InfoBoxP2);
		context = canvas.getGraphicsContext2D();
		return aPane;
	}


	/*
	 * returns the list of gameObjects that are stored in the View
	 * 
	 * @return local list of gameObjects
	 */
	public ArrayList<GameObject> getViewList() {
		return viewList;
	}


	/*
	 * Render Method for Objects if they are in the scene or remove them otherwise
	 */
	public void render() {
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

//		for (int i = 0; i < sprites.size(); i++) {
//			if (sprites.get(i).getPos().x < 0 - sprites.get(i).getRadius() * 2
//					|| sprites.get(i).getPos().x > canvas.getWidth()
//					|| sprites.get(i).getPos().y < 0 - sprites.get(i).getRadius() * 2
//					|| sprites.get(i).getPos().y > canvas.getHeight()) {
//				sprites.get(i).removeSprite(); // removes Box2D Body
//				sprites.remove(i); // remove from ArrayList
//			} else {
//				context.drawImage(sprites.get(i).getImage(), sprites.get(i).getPos().x, sprites.get(i).getPos().y,
//						sprites.get(i).getRadius(), sprites.get(i).getRadius());
//			}
//		}
//		for (int i = 0; i < viewList.size(); i++) {
//			context.drawImage(viewList.get(i).getImage(), viewList.get(i).getPos().x, viewList.get(i).getPos().y,
//					viewList.get(i).getRadius(), viewList.get(i).getRadius());
//		}


		for (int i = 0; i < viewList.size(); i++) {
			if(viewList.get(i) instanceof Rect) {
				Rect wall = (Rect) viewList.get(i);
				context.fillRect( wall.getPos().x, wall.getPos().y, wall.getWidth(), wall.getHeight());
			}
			
			if(viewList.get(i) instanceof Sprite) {
				Sprite sprite = (Sprite) viewList.get(i);
				context.drawImage(sprite.getImage(), sprite.getPos().x, sprite.getPos().y,
						sprite.getRadius(), sprite.getRadius());
			}
		}

	}

}