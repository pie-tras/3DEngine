package com.draglantix.stateManager;

import com.draglantix.renderer.MasterRenderer;
import com.draglantix.window.Window;
import com.draglantix.window.WindowHandler;

public abstract class GameState {

	private Window window;
	protected MasterRenderer renderer;
	private GameStateManager gsm;
	
	public GameState(GameStateManager gsm, Window window, MasterRenderer renderer) {
		this.gsm = gsm;
		this.window = window;
		this.renderer = renderer;
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



