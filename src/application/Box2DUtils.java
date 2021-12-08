package application;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


/**
 * @author Fredi Benko
 * parts of the code are from "https://github.com/shiffman/Box2D-for-Processing/blob/master/Box2D-for-Processing/src/shiffman/box2d/Box2DProcessing.java"
 * and are modified for this project
 */
public class Box2DUtils {
	
	private static float canvasWidth = 600.0f; // hard-coded because gameCanvas.getWidth() not working!
	private static float canvasHeight = 700.0f; // hard-coded because gameCanvas.getHeight() not working!
	private static float transX = canvasWidth / 2.0f;
	private static float transY = canvasHeight / 2.0f;
	private static float scaleFactor = 10.0f;
	private static float yFlip = -1.0f; // = -1.0f; //flip y coordinate
	
	// Functions to convert from Box2D world to Pixel world
	public static Vec2 coordWorldToPixels(Vec2 world) {
		return coordWorldToPixels(world.x,world.y);
	}	
	
	public static Vec2 coordWorldToPixels(float worldX, float worldY) {
		float pixelX = map(worldX, 0f, 1f, transX, transX+scaleFactor);
		float pixelY = map(worldY, 0f, 1f, transY, transY+scaleFactor);
		if(yFlip == -1.0f) pixelY = map(pixelY, 0f, canvasHeight, canvasHeight, 0f); // hard-coded because canvas.getHeigth() == NaN
		return new Vec2(pixelX, pixelY);
	}
	
	// Functions to convert from Pixel world to Box2D world 
	public static Vec2 coordPixelsToWorld(Vec2 screen) {
		return coordPixelsToWorld(screen.x,screen.y);
	}
	
	public static Vec2 coordPixelsToWorld(float pixelX, float pixelY) {
		float worldX = map(pixelX, transX, transX+scaleFactor, 0f, 1f);
		float worldY = pixelY;
		if (yFlip == -1.0f) worldY = map(pixelY, canvasHeight, 0f, 0f, canvasHeight); // hard-coded because canvas.getHeigth() == NaN
		worldY = map(worldY, transY, transY+scaleFactor, 0f, 1f);
		return new Vec2(worldX,worldY);
	}
	// Helper-Function
	private static float map(float var, float min1, float max1, float min2, float max2) {
		return min2+(max2-min2)*((var-min1)/(max1-min1));
	}
	
	// Find position of a body
	public static Vec2 getBodyPixelCoord(Body b) {
		Transform xf = b.getTransform();
		return coordWorldToPixels(xf.p); 
	}
	
	// Scale scalar quantity between worlds
	public static float scalarPixelsToWorld(float val) {
		return val / scaleFactor;
	}

	public static float scalarWorldToPixels(float val) {
		return val * scaleFactor;
	}

}
