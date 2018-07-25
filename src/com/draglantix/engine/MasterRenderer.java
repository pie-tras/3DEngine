package com.draglantix.engine;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import com.draglantix.terrain.Terrain;
import com.draglantix.util.OpenGlUtils;
import com.draglantix.window.Window;

public class MasterRenderer {

	public Vector3f color = new Vector3f(0, 0, 0);
	
	public void prepare() {
		GL11.glClearColor(color.x, color.y, color.z, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL32.glProvokingVertex(GL32.GL_FIRST_VERTEX_CONVENTION);
		OpenGlUtils.cullBackFaces(true);
		OpenGlUtils.enableDepthTesting(true);
		OpenGlUtils.antialias(true);
	}

	public void finish(Window window) {
		window.swapBuffers();
	}
	
	public void renderTerrain(Terrain terrain, Camera camera, Light light) {
		terrain.render(camera, light, new Vector4f(0, 0, 0, 0));
	}
}
