package com.draglantix.renderer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import com.draglantix.window.Window;

public class Camera {
	
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch = 45;
	private float yaw = 0;
	private float roll;
	
	private float theta;
	
	private float ypos = 0;
	private Vector2f lastMousePos = Window.getInput().getMousePos();
	
	private Vector3f center = new Vector3f(0, 0, 0);
	
	public void update() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
	}
	
	public void invertPitch() {
		this.pitch = - pitch;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getRoll() {
		return roll;
	}
	
	public float getTheta() {
		return theta;
	}
	
	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		theta = angleAroundPlayer;
		
		if(theta>360) {
			theta-=360;
		}else if(theta<-360) {
			theta+=360;
		}
	
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = center.x - offsetX;
		position.y = center.y + verticalDistance + 9;
		position.z = center.z - offsetZ;
		yaw = 180 - theta;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom() {
		@SuppressWarnings("unused")
		GLFWScrollCallbackI scrollCallback;
		ypos = 0;
		GLFW.glfwSetScrollCallback(Window.getWindow(), scrollCallback = GLFWScrollCallback.create((window, xoffset, yoffset) -> {
			ypos = (float) yoffset;
			float zoomLevel = ypos;
			distanceFromPlayer -= zoomLevel;
			if(distanceFromPlayer < -10) distanceFromPlayer = -10;
			if(distanceFromPlayer > 200) distanceFromPlayer = 200;
		}));
	}
	
	private void calculatePitch() {
		if(Window.getInput().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
			float pitchChange = (float) (Window.getInput().getMousePos().y - lastMousePos.y) * 0.1f;
			pitch -= pitchChange;
			if(pitch>90) pitch = 90;
			if(pitch<0) pitch = 0;
		}
		lastMousePos.y = Window.getInput().getMousePos().y;
	}
	
	private void calculateAngleAroundPlayer() {
		if(Window.getInput().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			float angleChange = (float) (Window.getInput().getMousePos().x - lastMousePos.x) * 0.1f;
			angleAroundPlayer -= angleChange;
		}
		lastMousePos.x = Window.getInput().getMousePos().x;
		
		if(angleAroundPlayer>360) {
			angleAroundPlayer-=360;
		}else if(angleAroundPlayer<-360) {
			angleAroundPlayer+=360;
		}
	}

}