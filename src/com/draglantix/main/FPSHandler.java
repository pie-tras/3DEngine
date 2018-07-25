package com.draglantix.main;

import com.draglantix.util.Timer;

public class FPSHandler {

	private boolean printFPS;
	
	private Timer timer;
	
	private double limitFPS = 1.0 / 60.0;
	
	private double now, delta, last;
	
	private float frameTime;
	
	private int frameCount = 0;
	
	public FPSHandler(boolean printFPS) {
		this.printFPS = printFPS;
		timer = new Timer();
		last = timer.getTime();
	}
	
	public boolean canUpdate() {
	
		frameTime += timer.getDelta();
		
		now = timer.getTime();
		delta += (now - last) / limitFPS;
		last = now;
		
		while(delta >= 1.0){
			delta--;
			frameCount++;
			return true;
		}
		
		if(frameTime >= 1) {
			if(printFPS) {
				System.out.println(frameCount);
			}
			frameTime = 0;
			frameCount = 0;
		}
		
		return false;
	
	}
	
}
