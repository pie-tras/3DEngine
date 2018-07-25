package com.draglantix.main;

import com.draglantix.engine.Camera;
import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.stateManager.GameStateManager;
import com.draglantix.stateManager.State;
import com.draglantix.util.CleanUpMaster;
import com.draglantix.window.Window;

public class Main {
	
	private Window window;
	private GameStateManager gsm;
	
	private FPSHandler fpsHandler;
	
	public Main() {
		init();
		run();
	}
	
	private void init() {
		window = new Window(Configs.WIDTH, Configs.HEIGHT, Configs.TITLE+Configs.VERSION);
		gsm = new GameStateManager(State.MENU, window, new MasterRenderer(), new MasterUpdater(new Camera()));	
		fpsHandler = new FPSHandler(Configs.PRINT_FPS);
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
