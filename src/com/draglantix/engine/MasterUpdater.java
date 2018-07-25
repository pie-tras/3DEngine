package com.draglantix.engine;

public class MasterUpdater {

	private Camera camera;
	
	public MasterUpdater(Camera camera) {
		this.camera = camera;
	}
	
	public void update() {
		camera.move();
	}
	
	public Camera getCamera() {
		return camera;
	}
	
}
