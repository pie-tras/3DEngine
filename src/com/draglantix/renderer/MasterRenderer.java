package com.draglantix.renderer;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.draglantix.window.Window;

public class MasterRenderer {

	public Vector3f color = new Vector3f(0, 0, 0);
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(color.x, color.y, color.z, 1);
	}

	public void finish(Window window) {
		window.swapBuffers();
	}
	
}
