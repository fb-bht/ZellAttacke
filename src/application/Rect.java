package application;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

/**
 * @author Fredi Benko 
 */
public class Rect {
	World world;
	Body body;

	float width;
	float height;

	public Rect(float x, float y, float w, float h, World world) {
		this.width = w;
		this.height = h;
		this.world = world;
		makeBody(new Vec2(x+(w/2),y+(h/2)), w, h);
	}
	
	void makeBody(Vec2 center, float w, float h) {
		// Define the shape
		PolygonShape ps = new PolygonShape();
		float box2dW = Box2DUtils.scalarPixelsToWorld(w/2);
		float box2dH = Box2DUtils.scalarPixelsToWorld(h/2);
		ps.setAsBox(box2dW, box2dH);
		
		// Create the body
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position.set(Box2DUtils.coordPixelsToWorld(center));
		body = this.world.createBody(bd);

		// Attached the shape to the body using a Fixture
		body.createFixture(ps,1);
	}
	
	// Returns the Position for Rendering (left upper corner)
	public Vec2 getPos() {
		Vec2 temp = Box2DUtils.getBodyPixelCoord(body);
		float xPosRect = temp.x - this.width/2;
		float yPosRect = temp.y - this.height/2;
		return (new Vec2(xPosRect, yPosRect));
	}

}
