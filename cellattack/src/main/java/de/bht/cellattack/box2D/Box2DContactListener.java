package de.bht.cellattack.box2D;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import de.bht.cellattack.model.Arena;
import de.bht.cellattack.model.Sprite;

/**
 * Box2DContactListener Class to retrieve contact information whenever Box2D objects collide
 * 
 * @author Fredi Benko
 */
public class Box2DContactListener implements ContactListener {
	
	Arena modelRef;
	
	public Box2DContactListener() {}
	public Box2DContactListener(Arena modelRef) {
		this.modelRef = modelRef;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		// Get both bodies
		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		// Get objects that reference the bodies
		Object o1 =  b1.getUserData();
		Object o2 =  b2.getUserData();

		if (o1.getClass() == Sprite.class && o2.getClass() == Sprite.class) {
			Sprite s1 = (Sprite) o1;
			Sprite s2 = (Sprite) o2;
			if (s1.getEntityType() == "orbiterP1" && s2.getEntityType() == "orbiterP2"
					|| s1.getEntityType() == "orbiterP2" && s2.getEntityType() == "orbiterP1") {
				modelRef.ppCollision();
			}
			if (s1.getEntityType() == "orbiterP1" && s2.getEntityType() == "neutral"
					|| s1.getEntityType() == "neutral" && s2.getEntityType() == "orbiterP1") {
				modelRef.np1Collision();
			}
			if (s1.getEntityType() == "orbiterP2" && s2.getEntityType() == "neutral"
					|| s1.getEntityType() == "neutral" && s2.getEntityType() == "orbiterP2") {
				modelRef.np2Collision();
			}
		}
	}

	@Override
	public void endContact(Contact arg0) {
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
	}

}
