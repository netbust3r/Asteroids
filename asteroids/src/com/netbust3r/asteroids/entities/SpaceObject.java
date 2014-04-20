package com.netbust3r.asteroids.entities;

import com.netbust3r.asteroids.main.Game;

public class SpaceObject {
	
	protected float x;
	protected float y;
	
	protected float dx;
	protected float dy;
	
	protected float radians;
	protected float speed;
	protected float rotationSpeed;
	
	protected int width;
	protected int height;
	
	protected float[] shapex;
	protected float[] shapey;
	
	protected void wrap(){
		if (x < 0)	x = Game.WIDTH;
		if (x > Game.WIDTH) x = 0;
		if (y < 0) y = Game.HEIGTH;
		if (y > Game.WIDTH) y = 0;
	}
	
	
}
