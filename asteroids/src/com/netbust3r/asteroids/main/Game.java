package com.netbust3r.asteroids.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.netbust3r.asteroids.managers.GameInputProcessor;
import com.netbust3r.asteroids.managers.GameKeys;
import com.netbust3r.asteroids.managers.GameStateManager;

public class Game implements ApplicationListener   {

	public static int WIDTH;
	public static int HEIGTH;
	
	public static OrthographicCamera cam;
	
	private GameStateManager gsm; 
	
	@Override
	public void create() {
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGTH = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(WIDTH,HEIGTH);
		cam.translate(WIDTH / 2 , HEIGTH / 2);
		cam.update();
		
		Gdx.input.setInputProcessor(
				new GameInputProcessor()
				);
		
		gsm = new GameStateManager();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		
		GameKeys.update();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
