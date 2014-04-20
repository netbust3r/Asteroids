package com.netbust3r.asteroids.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.netbust3r.asteroids.entities.Player;
import com.netbust3r.asteroids.managers.GameKeys;
import com.netbust3r.asteroids.managers.GameStateManager;

public class PlayState extends GameState {

	private ShapeRenderer sr_;
	
	private Player player_;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		sr_ = new ShapeRenderer();
		
		player_ = new Player();
	}

	@Override
	public void update(float dt) {
		
		handleInput();
		
		player_.update(dt);
	}

	@Override
	public void draw() {
		player_.draw(sr_);
	}

	@Override
	public void handleInput() {
		player_.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player_.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player_.setUp(GameKeys.isDown(GameKeys.UP));
	}

	@Override
	public void dispose() {}

}
