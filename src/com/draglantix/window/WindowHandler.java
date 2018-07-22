package com.draglantix.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.draglantix.stateManager.GameStateManager;
import com.draglantix.stateManager.State;

public class WindowHandler {

	public static void handle(Window window, GameStateManager gsm) {
		
		window.update();
		
		if(Window.hasResized()){
			GL11.glViewport(0, 0, Window.getWidth(), Window.getHeight());
			//UPDATE PROJECTION MATRICES
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			GLFW.glfwSetWindowShouldClose(Window.getWindow(), true);
		}
		
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE) && gsm.getState() == State.MENU){
			gsm.setState(State.PLAY);
		}
	
	}
	
}
