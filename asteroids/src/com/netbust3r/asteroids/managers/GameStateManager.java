package com.netbust3r.asteroids.managers;

import com.netbust3r.asteroids.gamestates.GameState;
import com.netbust3r.asteroids.gamestates.PlayState;

public class GameStateManager {
	
	//current game state
	private GameState gameState_;
	
	public static final int MENU = 0;
	public static final int PLAY = 87987;
	
	public GameStateManager(){
		setState(PLAY);
	}
	
	public void setState(int state){
		if (gameState_ != null) 
			gameState_.dispose();
		if(state == MENU){
			//gameState_ = new MenuState(this);
		}
		if(state == PLAY){
			gameState_ = new PlayState(this); 
		}
	}

	public void update(float dt){
		gameState_.update(dt);
	}
	
	public void draw(){
		gameState_.draw();
	}
}
