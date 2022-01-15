package application;

import javafx.scene.image.Image;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

/**
 * @author Fredi Benko 
 */
public class CSprite {
	World world;
	Body body;
	DistanceJoint djoint;
	

	Image image;
	float radius;

	public CSprite (Image image, float x, float y, float r, World world, BodyType bt) {
		this.image = image;
		this.radius = r;
		this.world = world;
		makeBody(new Vec2(x,y), r, bt);
	}
	
	void makeBody(Vec2 center, float r, BodyType bt) {
		// Define the Shape
		CircleShape cs = new CircleShape();
		cs.setRadius(Box2DUtils.scalarPixelsToWorld(r/2));
		
		// Create the Body
		BodyDef bd = new BodyDef();
		bd.type = bt;
		bd.position.set(Box2DUtils.coordPixelsToWorld(center));
		body = this.world.createBody(bd);
		
	    // Define a Fixture
	    FixtureDef fd = new FixtureDef();
	    fd.shape = cs;
	    // Physics Parameters
	    fd.density = 1;
	    fd.friction = 0.3f;
	    fd.restitution = 0.5f;

		// Attached the Shape to the Body using a Fixture
		body.createFixture(fd);
		
	    // Initial random velocity
	    body.setLinearVelocity(new Vec2((float)Math.random()*5, (float)Math.random()*5));
	}
	
	// Returns Sprite Image
	public Image getImage() {
		return this.image;
	}
	
	// Returns the Position for Rendering (left upper corner)
	public Vec2 getPos() {
		Vec2 temp = Box2DUtils.getBodyPixelCoord(body);
		float xPosRect = temp.x - this.radius/2;
		float yPosRect = temp.y - this.radius/2;
		return (new Vec2(xPosRect, yPosRect));
	}
	
	// Creates a Joint between this Object and a provided anchor
	public void addJoint(CSprite anchor) {
		DistanceJointDef djd = new DistanceJointDef();
		djd.bodyA = anchor.body;
		djd.bodyB = this.body;
		djd.length = Box2DUtils.scalarPixelsToWorld(50);
		djd.frequencyHz = 0;
	    djd.dampingRatio = 0;
	    this.djoint = (DistanceJoint) this.world.createJoint(djd);
	}
	
	// Removes a Joint
	public void removeJoint() {
		this.world.destroyJoint(this.djoint);
	}

}
