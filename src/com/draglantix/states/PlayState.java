package com.draglantix.states;

import org.joml.Vector3f;

import com.draglantix.engine.Light;
import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.main.Configs;
import com.draglantix.stateManager.GameState;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.terrain.ColorGenerator;
import com.draglantix.terrain.HybridTerrainGenerator;
import com.draglantix.terrain.PerlinNoise;
import com.draglantix.terrain.Terrain;
import com.draglantix.terrain.TerrainGenerator;
import com.draglantix.window.Window;

public class PlayState extends GameState{

	private float value = 0;
	private boolean increase;
	
	private static Terrain terrain;
	static TerrainGenerator terrainGenerator;
	private Light light;
	
	public PlayState(GameStateManager gsm, Window window, MasterRenderer renderer, MasterUpdater updater) {
		super(gsm, window, renderer, updater);
		
		light = new Light(Configs.LIGHT_POS, Configs.LIGHT_COL, Configs.LIGHT_BIAS);

		PerlinNoise noise = new PerlinNoise(Configs.OCTAVES, Configs.AMPLITUDE, Configs.ROUGHNESS);
		ColorGenerator colourGen = new ColorGenerator(Configs.TERRAIN_COLS, Configs.COLOR_SPREAD);
		terrainGenerator = new HybridTerrainGenerator(noise, colourGen);
		terrain = terrainGenerator.generateTerrain(Configs.WORLD_SIZE);
	}

	@Override
	protected void tick() {
		handleWindow();
		
		updater.update();
		
		if(increase) {
			value += 0.01f;
		}else{
			value-= 0.01f;
		}
		
		if(value>=1) {
			value = 1;
			increase = false;
		}
		
		if(value <= 0) {
			value = 0;
			increase = true;
		}
		
		renderer.color = new Vector3f(value, value/2, .5f);
	}

	@Override
	protected void render() {
		prepareRender();
		renderer.renderTerrain(terrain, updater.getCamera(), light);
		finishRender();
	}
	
	public static void cleanUp() {
		terrainGenerator.cleanUp();
		terrain.delete();
	}

}
