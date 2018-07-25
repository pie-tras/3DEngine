package com.draglantix.terrain;

import org.joml.Vector4f;

import com.draglantix.engine.Camera;
import com.draglantix.engine.Light;
import com.draglantix.glObject.Vao;
import com.draglantix.renderer.TerrainRenderer;

public class Terrain {
	
	private final Vao vao;
	private final int vertexCount;
	private final TerrainRenderer renderer;
	
	public Terrain(Vao vao, int vertexCount, TerrainRenderer renderer){
		this.vao = vao;
		this.vertexCount = vertexCount;
		this.renderer = renderer;
	}
	
	public int getVertexCount(){
		return vertexCount;
	}
	
	public Vao getVao(){
		return vao;
	}
	
	public void render(Camera camera, Light light, Vector4f clipPlane){
		renderer.render(this, camera, light, clipPlane);
	}
	
	public void delete(){
		vao.delete(true);
	}

}
