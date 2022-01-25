package box2D;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.JointDef;

import application.Main;
import model.Arena;

/**
 * Box2dUtils Class is a Facade for the JBox2D Framework
 * 
 * @author Fredi Benko
 * parts of the code are from "https://github.com/shiffman/Box2D-for-Processing/blob/master/Box2D-for-Processing/src/shiffman/box2d/Box2DProcessing.java"
 * and are modified for this project
 */
public class Box2dUtils {
	
	public World world;
	
	private static volatile Box2dUtils instance;
	private Box2DContactListener contactListener;
	
	private float canvasWidth; 
	private float canvasHeight; 
	private float transX;
	private float transY;
	private float scaleFactor;
	private float yFlip;
	
	private Box2dUtils() {
		canvasWidth = (float) Main.WIDTH; 
		canvasHeight = (float) Main.HEIGHT; 
		transX = canvasWidth / 2.0f;
		transY = canvasHeight / 2.0f;
		scaleFactor = 10.0f;
		yFlip = -1.0f; // = -1.0f; //flip y coordinate
		world = createWorld();
	}
	
    // Return Singleton  
	public static Box2dUtils getInstance() {
		Box2dUtils result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Box2dUtils.class) {
            if (instance == null) {
                instance = new Box2dUtils();
            }
            return instance;
        }		
	}
	
	
	
	/*------- Coordinate Transformations -------*/	
	
	// Functions to convert from Box2D world to Pixel world
	public Vec2 coordWorldToPixels(Vec2 world) {
		return coordWorldToPixels(world.x,world.y);
	}	
	// Functions to convert from Box2D world to Pixel world
	public Vec2 coordWorldToPixels(float worldX, float worldY) {
		float pixelX = map(worldX, 0f, 1f, transX, transX+scaleFactor);
		float pixelY = map(worldY, 0f, 1f, transY, transY+scaleFactor);
		if(yFlip == -1.0f) pixelY = map(pixelY, 0f, canvasHeight, canvasHeight, 0f); // hard-coded because canvas.getHeigth() == NaN
		return new Vec2(pixelX, pixelY);
	}
	
	// Functions to convert from Pixel world to Box2D world 
	public Vec2 coordPixelsToWorld(Vec2 screen) {
		return coordPixelsToWorld(screen.x,screen.y);
	}
	// Functions to convert from Pixel world to Box2D world
	public Vec2 coordPixelsToWorld(float pixelX, float pixelY) {
		float worldX = map(pixelX, transX, transX+scaleFactor, 0f, 1f);
		float worldY = pixelY;
		if (yFlip == -1.0f) worldY = map(pixelY, canvasHeight, 0f, 0f, canvasHeight); // hard-coded because canvas.getHeigth() == NaN
		worldY = map(worldY, transY, transY+scaleFactor, 0f, 1f);
		return new Vec2(worldX,worldY);
	}
	// Helper-Function
	private float map(float var, float min1, float max1, float min2, float max2) {
		return min2+(max2-min2)*((var-min1)/(max1-min1));
	}
	
	// Find position of a body
	public Vec2 getBodyPixelCoord(Body b) {
		Transform xf = b.getTransform();
		return coordWorldToPixels(xf.p); 
	}
	
	// Scale scalar quantity between worlds
	public float scalarPixelsToWorld(float val) {
		return val / scaleFactor;
	}
	// Scale scalar quantity between worlds
	public float scalarWorldToPixels(float val) {
		return val * scaleFactor;
	}
	

	
	/*------- Creation Functions -------*/
	
	// create a world
	private World createWorld() {
		this.world = new World(new Vec2(0.0f, 0.0f));
		return this.world;
	}
	
	// Create a Body
	public Body createBody(BodyDef bd) {
		return this.world.createBody(bd);
	}
	
	// Create a Joint
	public Joint createJoint(JointDef jd) {
		return this.world.createJoint(jd);
	}
	
	// Create a Contact Listener
	public void createContactListener(Arena modelRef) {
		this.contactListener = new Box2DContactListener(modelRef);
		this.world.setContactListener(contactListener);
	}
	
	/*------- Destruction Functions -------*/
	
	// Destroys Body
	public void destroyBody(Body body) {
		this.world.destroyBody(body);
	}
	
	public void destroyJoint(Joint j) {
		this.world.destroyJoint(j);
	}
	
	/*------- Physics Animation -------*/
	
	// Advance Simulation step
	public void step() {
		float timeStep = 1.0f / 60f;
		this.world.step(timeStep, 6, 2);
		this.world.clearForces();
	}
	
}
