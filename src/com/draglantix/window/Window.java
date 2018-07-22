package com.draglantix.window;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import com.draglantix.util.Input;

public class Window {

	private static long window;
	
	private static int width, height;
	private static String title;
	
	private static boolean hasResized;
	
	private static Input input;
	
	public Window(int width, int height, String title) {
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Failed to initalize GLFW!");
		}
		
		Window.width = width;
		Window.height = height;
		Window.title = title;
		
		WindowBuilder.setCallbacks();
		WindowBuilder.createWindow();
		
		GL.createCapabilities();
	}
	
	public void update() {
		hasResized = false;
		input.update();
		glfwPollEvents();
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
	
	public static void cleanUp(){
		glfwFreeCallbacks(window);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		Window.title = title;
	}

	public static boolean hasResized() {
		return hasResized;
	}

	public static void setHasResized(boolean hasResized) {
		Window.hasResized = hasResized;
	}

	public static Input getInput() {
		return input;
	}

	public static void setInput(Input input) {
		Window.input = input;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static void setWidth(int width) {
		Window.width = width;
	}
	
	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Window.height = height;
	}

	public static long getWindow() {
		return window;
	}

	public static void setWindow(long window) {
		Window.window = window;
	}
	
}
