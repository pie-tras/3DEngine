package com.draglantix.renderer;

import com.draglantix.shaders.ShaderProgram;
import com.draglantix.shaders.UniformMatrix;
import com.draglantix.shaders.UniformVec2;
import com.draglantix.shaders.UniformVec3;
import com.draglantix.shaders.UniformVec4;

public class TerrainShader extends ShaderProgram {

	protected UniformMatrix projectionViewMatrix = new UniformMatrix("projectionViewMatrix");
	protected UniformVec3 lightDirection = new UniformVec3("lightDirection");
	protected UniformVec3 lightColor = new UniformVec3("lightColor");
	protected UniformVec2 lightBias = new UniformVec2("lightBias");
	protected UniformVec4 plane = new UniformVec4("plane");

	public TerrainShader(String key, boolean useGeoShader) {
		super(key, useGeoShader);
		super.storeAllUniformLocations(projectionViewMatrix, lightDirection, lightColor, lightBias, plane);
	}

}
