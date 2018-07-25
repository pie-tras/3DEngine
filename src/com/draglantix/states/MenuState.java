package com.draglantix.states;

import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.stateManager.GameState;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.window.Window;

public class MenuState extends GameState{

	public MenuState(GameStateManager gsm, Window window, MasterRenderer renderer, MasterUpdater updater) {
		super(gsm, window, renderer, updater);
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
