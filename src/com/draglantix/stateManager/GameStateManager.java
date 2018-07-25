package com.draglantix.stateManager;

import com.draglantix.engine.MasterRenderer;
import com.draglantix.engine.MasterUpdater;
import com.draglantix.states.MenuState;
import com.draglantix.states.PlayState;
import com.draglantix.window.Window;

public class GameStateManager {
	
	private State currentState;
	
	private GameState currentStateClass;
	
	private MenuState menuState;
	private PlayState playState;
	
	public GameStateManager(State startState, Window window, MasterRenderer renderer, MasterUpdater updater){
		menuState = new MenuState(this, window, renderer, updater);
		playState = new PlayState(this, window, renderer, updater);
		
		currentState = startState;
		setState(currentState);
	}
	
	public void update() {
		currentStateClass.tick();
		currentStateClass.render();
	}
	
	public void setState(State state){
		currentState = state;
		
		switch (currentState) {
	        case PLAY:
	            currentStateClass  = playState;  
	            break;       
	        default:
	            currentStateClass = menuState;     
	            break;
		}
	}
	
	public State getState(){
		return currentState;
	}
	
}
