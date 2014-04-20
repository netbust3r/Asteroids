package com.netbust3r.asteroids.gamestates;

import com.netbust3r.asteroids.managers.GameStateManager;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}

	
	public abstract void init();
	
	public abstract void update(float dt);
	
	public abstract void draw();
	
	public abstract void handleInput();
	
	public abstract void dispose();
	
}
