package com.draglantix.states;

import com.draglantix.renderer.MasterRenderer;
import com.draglantix.stateManager.GameState;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.window.Window;

public class MenuState extends GameState{

	public MenuState(GameStateManager gsm, Window window, MasterRenderer renderer) {
		super(gsm, window, renderer);
	}

	@Override
	protected void tick() {
		handleWindow();
	}

	@Override
	protected void render() {
		prepareRender();
		
		
		
		finishRender();
	}
	
}
