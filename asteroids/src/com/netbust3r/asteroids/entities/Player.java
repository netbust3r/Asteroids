package com.netbust3r.asteroids.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.netbust3r.asteroids.main.Game;

public class Player extends SpaceObject{
	
	private float[] flamex_;
	private float[] flamey_;
	
	private boolean left_;
	private boolean right_;
	private boolean up_;
	
	private float maxSpeed_;
	private float acceleration_;
	private float deceleration_;
	private float accelerationTimer_;
	
	public Player()	{
		
		x_ = Game.WIDTH / 2;
		y_ = Game.HEIGTH / 2;
		
		maxSpeed_ = 300;
		acceleration_ = 200;
		deceleration_ = 10;
		accelerationTimer_ = 0;
		
		shapex_ = new float[4];
		shapey_ = new float[4];	
		
		flamex_ = new float[3];
		flamey_ = new float[3];
		
		radians_ = 3.1415f / 2;
		rotationSpeed_ = 3;
	}
	
	private void setShape(){
		shapex_[0] = x_ + MathUtils.cos(radians_) * 8;
		shapey_[0] = y_ + MathUtils.sin(radians_) * 8;

		shapex_[1] = x_ + MathUtils.cos(radians_ - 4 * 3.1415f / 5) * 8;
		shapey_[1] = y_ + MathUtils.sin(radians_ - 4 * 3.1115f / 5) * 8;
	
		shapex_[2] = x_ + MathUtils.cos(radians_ + 3.1415f) * 5;
		shapey_[2] = y_ + MathUtils.sin(radians_ + 3.1415f) * 5;
		
		shapex_[3] = x_ + MathUtils.cos(radians_ + 4 * 3.1415f / 5) * 8;
		shapey_[3] = y_ + MathUtils.sin(radians_ + 4 * 3.1415f / 5) * 8;
	}
	
	private void setFlame(float dt){
		flamex_[0] = x_ + MathUtils.cos(radians_ - 5 * 3.1415f / 6) * 5;
		flamey_[0] = y_ + MathUtils.sin(radians_ - 5 * 3.1415f / 6) * 5;
		
		flamex_[1] = x_ + MathUtils.cos(radians_ - 3.1415f) * (6 + accelerationTimer_ * 100);
		flamey_[1] = y_ + MathUtils.sin(radians_ - 3.1415f) * (6 + accelerationTimer_ * 100);
		
		flamex_[2] = x_ + MathUtils.cos(radians_ + 5 * 3.1415f / 6) * 5;
		flamey_[2] = y_ + MathUtils.sin(radians_ + 5 * 3.1415f / 6) * 5;
		
	}
	
	
	
	public void setLeft(boolean b){ left_ = b;}
	public void setRight(boolean b){ right_ = b;}
	public void setUp(boolean b){ up_ = b;}
	
	public void update(float dt){
		
		//turning
		if(left_){
			radians_ += rotationSpeed_ * dt;
		}
		else if(right_){
			radians_ -= rotationSpeed_ * dt;
		}
		
		//accelerating
		if(up_){
			dx_ += MathUtils.cos(radians_) * acceleration_ * dt;
			dy_ += MathUtils.sin(radians_) * acceleration_ * dt;
			
			accelerationTimer_ += dt;
			if(accelerationTimer_ > 0.1)
				accelerationTimer_ = 0;
		}
		else
			accelerationTimer_ = 0;
		
		//deceleration (vec = our speed)
		float vec = (float) Math.sqrt(dx_ * dx_ + dy_ * dy_);
		if (vec < 0) {
			dx_=0;
			dy_=0;
		}
		if (vec > 0){
			dx_ -= (dx_ / vec) * deceleration_ *dt;
			dy_ -= (dy_ / vec) * deceleration_ *dt;;
		}
		if(vec > maxSpeed_){
			dx_ = (dx_ / vec) * maxSpeed_;
			dy_ = (dy_ / vec) * maxSpeed_;
		}
		
		// set position
		x_ += dx_ * dt;
		y_ += dy_ * dt;
		
		//set shape
		setShape();
		
		//set flame
		if(up_) {
			setFlame(dt);
		}

		//screen wrap
		wrap();		
	}
	
	public void draw(ShapeRenderer sr){
		
		sr.setColor(1, 1, 1, 1);
		
		sr.begin(ShapeType.Line);
		
		//draw ship
		for(int i = 0, j = shapex_.length -1;
				i < shapex_.length;
				j = i++){
			
			sr.line(shapex_[i],shapey_[i],shapex_[j],shapey_[j]);
		}
		
		//draw flame
		if(up_){
			for(int i = 0, j = flamex_.length -1;
					i < flamex_.length;
					j = i++){
				
				sr.line(flamex_[i],flamey_[i],flamex_[j],flamey_[j]);
			}
		}
		
		sr.end();
		
	}
	
}
