package com.draglantix.window;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import com.draglantix.util.Input;

public class WindowBuilder {
	
	private static long window;
	
	private static GLFWWindowSizeCallback windowSizeCallback;
	
	private static Input input;
	
	public static void createWindow(){		
		int width = Window.getWidth();
		int height = Window.getHeight();
		
		glfwGetVideoMode(glfwGetPrimaryMonitor());
		Window.setHasResized(false);
		
		window = glfwCreateWindow(width, height, Window.getTitle(), 0, 0);
		
		if(window == 0)
			throw new IllegalStateException("Failed to create window!");
		glfwWindowHint(GLFW.GLFW_DEPTH_BITS, 24);
		
		GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vid.width()-width)/2, (vid.height()-height)/2);
			
		glfwShowWindow(window);
	
		glfwMakeContextCurrent(window);
		
		input = new Input(window);
		setLocalCallbacks();
		
		Window.setWindow(window);
		Window.setInput(input);
	}
	
	public static void setCallbacks() {
		glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
	}
	
	private static void setLocalCallbacks(){
		windowSizeCallback = new GLFWWindowSizeCallback(){

			@Override
			public void invoke(long argWindow, int argWidth, int argHeight) {
				Window.setWidth(argWidth);
				Window.setHeight(argHeight);
				Window.setHasResized(true);;
			}
			
		};
		
		glfwSetWindowSizeCallback(window, windowSizeCallback);
	}
	
}