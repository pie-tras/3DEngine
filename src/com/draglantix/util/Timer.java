package com.draglantix.util;

import org.lwjgl.glfw.GLFW;

public class Timer {

    private double lastTime;
    
    private int frameCount;
    private double timePassed;
    private float FPS;
   
    public Timer() {
        lastTime = getTime();
    }

    public double getTime() {
		return GLFW.glfwGetTime();
    }
    
	public double getDelta() {
        double time = getTime();
        double delta = time - lastTime;
        lastTime = time;
        return delta;
	}
	
	public void calculateFPS() {
		frameCount++;
		
		timePassed += getDelta();
	
		if(timePassed>=1) {
			timePassed = 0;
			FPS = frameCount;
			frameCount = 0;
			System.out.println(FPS);
		}
	}

}