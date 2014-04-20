package com.netbust3r.asteroids.entities;

import com.netbust3r.asteroids.main.Game;

public class SpaceObject {

	protected float x_;
	protected float y_;
	
	protected float dx_;
	protected float dy_;
	
	protected float radians_;
	protected float speed_;
	protected float rotationSpeed_;
	
	protected int width_;
	protected int height_;
	
	protected float[] shapex_;
	protected float[] shapey_;
	
	protected void wrap(){
		if (x_ < 0)	x_ = Game.WIDTH;
		if (x_ > Game.WIDTH) x_ = 0;
		if (y_ < 0) y_ = Game.HEIGTH;
		if (y_ > Game.WIDTH) y_ = 0;
	}
	
	
}
