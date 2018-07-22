package com.draglantix.states;

import org.joml.Vector3f;

import com.draglantix.renderer.MasterRenderer;
import com.draglantix.stateManager.GameState;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.window.Window;

public class PlayState extends GameState{

	private float value = 0;
	private boolean increase;
	
	public PlayState(GameStateManager gsm, Window window, MasterRenderer renderer) {
		super(gsm, window, renderer);
	}

	@Override
	protected void tick() {
		handleWindow();
		
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

		finishRender();
	}

}
