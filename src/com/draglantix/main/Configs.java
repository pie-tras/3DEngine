package com.draglantix.main;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.util.Color;

public class Configs {

	public static final int FPS_CAP = 100;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final float COLOR_SPREAD = 0.45f;
	public static final Color[] TERRAIN_COLS = new Color[] { new Color(201, 178, 99, true), new Color(164, 155, 98, true),
			new Color(164, 155, 98, true), new Color(229, 219, 164, true), new Color(135, 184, 82, true), new Color(120, 120, 120, true),
			new Color(200, 200, 210, true) };

	public static Vector3f LIGHT_POS = new Vector3f(0.3f, -1f, 0.5f);
	public static Color LIGHT_COL = new Color(1f, 0.95f, 0.95f);
	public static Vector2f LIGHT_BIAS = new Vector2f(0.3f, 0.8f);

	public static final int WORLD_SIZE = 200;
	public static final int SEED = 10164313;

	public static final float AMPLITUDE = 30;
	public static final float ROUGHNESS = 0.4f;
	public static final int OCTAVES = 5;
	
	public static final float WATER_HEIGHT = -1;
	
}
