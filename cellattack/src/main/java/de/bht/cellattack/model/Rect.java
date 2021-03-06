package de.bht.cellattack.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

/**
 * Rect Class for creating a rectangular GameObject
 * 
 * @author Fredi Benko 
 */
public class Rect implements GameObject {

	Body body;
	float width;
	float height;
	String entityType;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Rect(float x, float y, float w, float h) {
		this.width = w;
		this.height = h;

		makeBody(new Vec2(x+(w/2),y+(h/2)), w, h);
		body.setUserData(this);
	}
	
	
	/** 
	 * creates a box2d Object
	 * 
	 * @param center
	 * @param w
	 * @param h
	 */
	private void makeBody(Vec2 center, float w, float h) {
		// Define the shape
		PolygonShape ps = new PolygonShape();
		float box2dW = Arena.box2d.scalarPixelsToWorld(w/2);
		float box2dH = Arena.box2d.scalarPixelsToWorld(h/2);
		ps.setAsBox(box2dW, box2dH);
		
		// Create the body
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position.set(Arena.box2d.coordPixelsToWorld(center));
		body = Arena.box2d.createBody(bd);

		// Attached the shape to the body using a Fixture
		body.createFixture(ps,1);
	}
	
	
	/** 
	 *  Returns the Position for Rendering (left upper corner)
	 *  
	 *  @return Vec2 Position of the GameObject
	 */
	@Override
	public Vec2 getPos() {
		Vec2 temp = Arena.box2d.getBodyPixelCoord(body);
		float xPosRect = temp.x - this.width/2;
		float yPosRect = temp.y - this.height/2;
		return (new Vec2(xPosRect, yPosRect));
	}
	
	
	/** 
	 * set the entity type of the GameObject
	 * 
	 * @param et entity Type
	 */
	@Override
	public void setEntityType(String et) {
		this.entityType = et;
	}
	
	
	/** 
	 * returns the entity type of the GameObject
	 * 
	 * @return String entity Type
	 */
	@Override
	public String getEntityType() {
		return this.entityType;
	}

	
	/** 
	 * getter for the width of the gameObject
	 * 
	 * @return float width of the gameObject
	 */
	public float getWidth() {
		return width;
	}

	
	/** 
	 * getter for the height of the gameObject
	 * 
	 * @return float height of the gameObject
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Removes a Sprite image
	 */
	@Override
	public void removeBody() {
		Arena.box2d.destroyBody(this.body);
	}
}
