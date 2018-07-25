package com.draglantix.states;

import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.stateManager.GameState;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.window.Window;

public class IntroState extends GameState{

	public IntroState(GameStateManager gsm, Window window, MasterRenderer renderer, MasterUpdater updater) {
		super(gsm, window, renderer, updater);
	}

	@Override
	protected void tick() {
		
	}

	@Override
	protected void render() {
	
	}

}
