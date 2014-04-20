package com.netbust3r.asteroids.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.netbust3r.asteroids.main.Game;

public class Player extends SpaceObject{


	private final int MAX_BULLETS = 4;
	private ArrayList<Bullet> bullets;
	
	private float[] flamex;
	private float[] flamey;
	
	private boolean left;
	private boolean right;
	private boolean up;
	
	private float maxSpeed;
	private float acceleration;
	private float deceleration;
	private float accelerationTimer;
	
	public Player(ArrayList<Bullet> bullets)	{
		
		this.bullets = bullets;

		x = Game.WIDTH / 2;
		y = Game.HEIGTH / 2;
		
		maxSpeed = 300;
		acceleration = 200;
		deceleration = 10;
		accelerationTimer = 0;
		
		shapex = new float[4];
		shapey = new float[4];	
		
		flamex = new float[3];
		flamey = new float[3];
		
		radians = 3.1415f / 2;
		rotationSpeed = 3;
	}
	
	private void setShape(){
		shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;

		shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
		shapey[1] = y + MathUtils.sin(radians - 4 * 3.1115f / 5) * 8;
	
		shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
		shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;
		
		shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
		shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
	}
	
	private void setFlame(float dt){
		flamex[0] = x + MathUtils.cos(radians - 5 * 3.1415f / 6) * 5;
		flamey[0] = y + MathUtils.sin(radians - 5 * 3.1415f / 6) * 5;
		
		flamex[1] = x + MathUtils.cos(radians - 3.1415f) * (6 + accelerationTimer * 100);
		flamey[1] = y + MathUtils.sin(radians - 3.1415f) * (6 + accelerationTimer * 100);
		
		flamex[2] = x + MathUtils.cos(radians + 5 * 3.1415f / 6) * 5;
		flamey[2] = y + MathUtils.sin(radians + 5 * 3.1415f / 6) * 5;
		
	}

	public void shoot(){
		if (bullets.size() == MAX_BULLETS)
			return;
		else bullets.add(new Bullet(x, y, radians));

	}
	
	public void setLeft(boolean b){ 
		left = b;
	}

	public void setRight(boolean b){ 
		right = b;
	}

	public void setUp(boolean b){ 
		up = b;
	}
	
	public void update(float dt){
		
		//turning
		if(left){
			radians += rotationSpeed * dt;
		}
		else if(right){
			radians -= rotationSpeed * dt;
		}
		
		//accelerating
		if(up){
			dx += MathUtils.cos(radians) * acceleration * dt;
			dy += MathUtils.sin(radians) * acceleration * dt;
			
			accelerationTimer += dt;
			if(accelerationTimer > 0.1)
				accelerationTimer = 0;
		}
		else
			accelerationTimer = 0;
		
		//deceleration (vec = our speed)
		float vec = (float) Math.sqrt(dx * dx + dy * dy);
		if (vec < 0) {
			dx=0;
			dy=0;
		}
		if (vec > 0){
			dx -= (dx / vec) * deceleration *dt;
			dy -= (dy / vec) * deceleration *dt;;
		}
		if(vec > maxSpeed){
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}
		
		// set position
		/*
		*x = x0 + u * dt + a * dt^2 = x0 + (a * dt) * dt = x0 + dx *dt
		*u = 0
		*a * dt * cos(radians)= dx
		*/
		x += dx * dt;
		y += dy * dt;
		
		//set shape
		setShape();
		
		//set flame
		if(up) {
			setFlame(dt);
		}

		//screen wrap
		wrap();		
	}
	
	public void draw(ShapeRenderer sr){
		
		sr.setColor(1, 1, 1, 1);
		
		sr.begin(ShapeType.Line);
		
		//draw ship
		for(int i = 0, j = shapex.length -1;
				i < shapex.length;
				j = i++){
			
			sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
		}
		
		//draw flame
		if(up){
			for(int i = 0, j = flamex.length -1;
					i < flamex.length;
					j = i++){
				
				sr.line(flamex[i],flamey[i],flamex[j],flamey[j]);
			}
		}
		
		sr.end();
		
	}
	
}
