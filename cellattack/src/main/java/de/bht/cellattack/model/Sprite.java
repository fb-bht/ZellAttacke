package de.bht.cellattack.model;

import javafx.scene.image.Image;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;


/**
 * Sprite Class for creating a GameObject with Image
 * 
 * @author Fredi Benko 
 */
public class Sprite implements GameObject {

	Body body;
	RevoluteJoint joint;	
	Image image;
	float radius;
	String entityType;
	
	public Sprite (Image image, float x, float y, float r) {
		this(image, x, y, r, BodyType.DYNAMIC);

	}

	public Sprite (Image image, float x, float y, float r, BodyType bt) {
		this.image = image;
		this.radius = r;
		makeBody(new Vec2(x,y), r, bt);
		body.setUserData(this);
	}
	
	// creates a box2d Object
	private void makeBody(Vec2 center, float r, BodyType bt) {
		// Define the Shape
		CircleShape cs = new CircleShape();
		cs.setRadius(Arena.box2d.scalarPixelsToWorld(r/2));
		
		// Create the Body
		BodyDef bd = new BodyDef();
		bd.type = bt;
		bd.position.set(Arena.box2d.coordPixelsToWorld(center));
		this.body = Arena.box2d.createBody(bd);
		
	    // Define a Fixture
	    FixtureDef fd = new FixtureDef();
	    fd.shape = cs;
	    // Physics Parameters
	    fd.density = 1;
	    fd.friction = 0.3f;
	    fd.restitution = 0.5f;

		// Attached the Shape to the Body using a Fixture
		this.body.createFixture(fd);		
	}
	
	/*
	 * set the entity type of the GameObject
	 * 
	 * @param entity Type
	 */
	@Override
	public void setEntityType(String et) {
		this.entityType = et;
	}
	
	/* 
	 * returns the entity type of the GameObject
	 * 
	 * @return entity Type
	 */
	@Override
	public String getEntityType() {
		return this.entityType;
	}
	
	/* 
	 * returns the radius of the GameObject
	 * 
	 * @return radius
	 */
	public float getRadius() {
		return radius;
	}

	/*
	 * set the radius of the GameObject
	 * 
	 * @param radius
	 */
	public void setRadius(float coefficient) {
		this.radius = this.radius * coefficient;
		Shape shape = this.body.getFixtureList().getShape();
		shape.setRadius(shape.getRadius() * coefficient);		
	}

	/*
	 *  returns Sprite Image
	 *  
	 *  @return the Sprite image
	 */
	public Image getImage() {
		return this.image;
	}
		
	/*
	 *  returns the Position for Rendering (left upper corner)
	 *  
	 *  @return Position of the GameObject
	 */
	@Override
	public Vec2 getPos() {
		Vec2 temp = Arena.box2d.getBodyPixelCoord(body);
		float xPosRect = temp.x - this.radius/2;
		float yPosRect = temp.y - this.radius/2;
		return (new Vec2(xPosRect, yPosRect));
	}
	
	/*
	 *  Creates a Joint between this Object and a provided anchor
	 *  
	 *  @param anchor to attache the joint to
	 */
	public void addJoint(Sprite anchor) {
		RevoluteJointDef rjd = new RevoluteJointDef();
		rjd.initialize(this.body, anchor.body, anchor.body.getWorldCenter());
		rjd.motorSpeed = 1.0f;
		rjd.maxMotorTorque = 1000.0f;
		rjd.enableMotor = true; 
		joint = (RevoluteJoint) Arena.box2d.createJoint(rjd);
	}
	
	/*
	 *  Removes a Joint
	 */
	public void removeJoint() {
		Arena.box2d.destroyJoint(this.joint);
	}
	
	/*
	 * Removes a Sprite image
	 */
	public void removeSprite() {
		Arena.box2d.destroyBody(this.body);
	}
	
}
