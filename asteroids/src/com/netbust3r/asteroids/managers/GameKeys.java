package com.netbust3r.asteroids.managers;

public class GameKeys {
	
	private static boolean[] keys_;
	private static boolean[] pkeys_;
	
	private static final int NUM_KEYS_ = 8;
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int ENTER = 4;
	public static final int ESCAPE = 5;
	public static final int SPACE = 6;
	public static final int SHIFT = 7;
	
	static{
		keys_ = new boolean[NUM_KEYS_];
		pkeys_ = new boolean[NUM_KEYS_];
	}
	
	public static void update(){
		for(int i = 0; i < NUM_KEYS_; i++) {
			pkeys_[i] = keys_ [i];
		}
	}
	
	public static void setKey(int k, boolean b){
		keys_[k] = b;
	}
	
	public static boolean isDown(int k){
		return keys_[k];
	}
	
	public static boolean isPressed(int k){
		return keys_[k] && !pkeys_[k];
	}
}
