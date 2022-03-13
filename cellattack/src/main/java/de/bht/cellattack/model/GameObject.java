package de.bht.cellattack.model;

import org.jbox2d.common.Vec2;

public interface GameObject {
	abstract public Vec2 getPos();
	abstract public void setEntityType(String et);
	abstract public String getEntityType();
	abstract void removeBody();
}
