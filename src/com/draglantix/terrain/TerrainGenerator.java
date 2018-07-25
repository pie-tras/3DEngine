package com.draglantix.terrain;

import com.draglantix.util.Color;

public abstract class TerrainGenerator {

	private final PerlinNoise perlinNoise;
	private final ColorGenerator colorGen;

	public TerrainGenerator(PerlinNoise perlinNoise, ColorGenerator colorGen) {
		this.perlinNoise = perlinNoise;
		this.colorGen = colorGen;
	}

	public Terrain generateTerrain(int gridSize) {
		float[][] heights = generateHeights(gridSize, perlinNoise);
		Color[][] colors = colorGen.generateColors(heights, perlinNoise.getAmplitude());
		return createTerrain(heights, colors);
	}

	public abstract void cleanUp();

	protected abstract Terrain createTerrain(float[][] heights, Color[][] colors);

	private float[][] generateHeights(int gridSize, PerlinNoise perlinNoise) {
		float heights[][] = new float[gridSize + 1][gridSize + 1];
		for (int z = 0; z < heights.length; z++) {
			for (int x = 0; x < heights[z].length; x++) {
				heights[z][x] = perlinNoise.getPerlinNoise(x, z);
			}
		}
		return heights;
	}
}
