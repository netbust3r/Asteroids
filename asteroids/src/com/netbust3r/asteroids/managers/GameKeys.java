package com.netbust3r.asteroids.managers;

public class GameKeys {
	
	private static boolean[] keys;
	private static boolean[] pkeys;
	
	private static final int NUMKEYS = 8;
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int ENTER = 4;
	public static final int ESCAPE = 5;
	public static final int SPACE = 6;
	public static final int SHIFT = 7;
	
	static{
		keys = new boolean[NUMKEYS];
		pkeys = new boolean[NUMKEYS];
	}
	
	public static void update(){
		for(int i = 0; i < NUMKEYS; i++) {
			pkeys[i] = keys [i];
		}
	}
	
	public static void setKey(int k, boolean b){
		keys[k] = b;
	}
	
	public static boolean isDown(int k){
		return keys[k];
	}
	
	public static boolean isPressed(int k){
		return keys[k] && !pkeys[k];
	}
}
