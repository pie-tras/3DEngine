package com.draglantix.util;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

public class Input {
	private long window;
	
	private boolean keys[];
	
	public Input(long window) {
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		for(int i = 0; i < GLFW_KEY_LAST; i++)
			keys[i]=false;
	}
	
	public boolean isKeyDown(int key){
		return glfwGetKey(window, key) == 1;
	}
	
	public boolean isKeyPressed(int key) {
		return (isKeyDown(key) && !keys[key]);
	}
	
	public boolean isKeyReleased(int key) {
		return (!isKeyDown(key) && keys[key]);
	}
	
	public boolean isMouseButtonDown(int button){
		return glfwGetMouseButton(window, button) == 1;
	}
	
	public Vector2f getMousePos() {
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, xBuffer, yBuffer);
		double x = xBuffer.get(0);
		double y = yBuffer.get(0);
		return new Vector2f((float)x, (float)y);
	}
	
	public void setMousePos(double xpos, double ypos) {
		GLFW.glfwSetCursorPos(window, xpos, ypos);
	}
	
	public void update() {
		for(int i = 32; i < GLFW_KEY_LAST; i++)
			keys[i]= isKeyDown(i);
	}
}