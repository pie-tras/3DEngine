package com.draglantix.main;

import com.draglantix.util.Timer;

public class FPSHandler {

	private boolean printFPS;
	
	private Timer timer;
	private float targetFPS = 60.0f;
	
	private float frameCap = 1.0f / targetFPS;
	private double passed = 0;

	private int FPS = 0;
	private int frameCount = 0;
	private double frameTime = 0;

	public FPSHandler(boolean printFPS) {
		this.printFPS = printFPS;
		timer = new Timer();
	}
	
	public boolean canUpdate() {
		passed += timer.getDelta();
		frameTime += timer.getDelta();
		
		while(passed >= frameCap) {
			passed -= frameCap;
			
			if(frameCount >= targetFPS) {
				break;
			}
			
			frameCount++;
			return true;
		}
		
		if(frameTime >= 1) {
			FPS = frameCount;
			
			if(printFPS) {
				System.out.println(FPS);
			}
			
			frameTime = 0;
			frameCount = 0;
		}
		return false;
	}
	
}
