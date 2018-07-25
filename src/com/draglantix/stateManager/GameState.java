package com.draglantix.stateManager;

import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.window.Window;
import com.draglantix.window.WindowHandler;

public abstract class GameState {

	private Window window;
	private GameStateManager gsm;
	
	protected MasterRenderer renderer;
	protected MasterUpdater updater;
	
	public GameState(GameStateManager gsm, Window window, MasterRenderer renderer, MasterUpdater updater) {
		this.gsm = gsm;
		this.window = window;
		this.renderer = renderer;
		this.updater = updater;
	}
	
	protected abstract void tick();
	
	protected abstract void render();
	
	protected void handleWindow() {
		WindowHandler.handle(window, gsm);
	}
	
	protected void prepareRender() {
		renderer.prepare();
	}
	
	protected void finishRender() {
		renderer.finish(window);
	}
	
}



