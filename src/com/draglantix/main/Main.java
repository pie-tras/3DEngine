package com.draglantix.main;

import com.draglantix.renderer.MasterRenderer;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.stateManager.State;
import com.draglantix.util.CleanUpMaster;
import com.draglantix.window.Window;

public class Main {

	private final float VERSION = 0.01f;
	private final String TITLE = "Game v";
	private boolean printFPS = true;
	
	private Window window;
	
	private MasterRenderer renderer;
	private GameStateManager gsm;
	
	private FPSHandler fpsHandler;
	
	public Main() {
		init();
		run();
	}
	
	private void init() {
		window = new Window(800, 600, TITLE+VERSION);
	
		renderer = new MasterRenderer();
		gsm = new GameStateManager(State.MENU, window, renderer);
		
		fpsHandler = new FPSHandler(printFPS);
	}
	
	private void run() {
		while(!window.shouldClose()){	
			if(fpsHandler.canUpdate()) {
				gsm.update();
			}
		}
	    CleanUpMaster.cleanUp();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
