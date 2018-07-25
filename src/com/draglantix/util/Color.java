package com.draglantix.util;

import java.nio.FloatBuffer;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class Color {

	private Vector3f color = new Vector3f();
	private float alpha = 1;

	public Color(float r, float g, float b) {
		color.set(r / 255, g / 255, b / 255);
	}

	public Color(float r, float g, float b, float a) {
		color.set(r / 255, g / 255, b / 255);
		this.alpha = a;
	}

	public byte[] getAsBytes(){
		int r = (int) (color.x * 255);
		int g = (int) (color.y * 255);
		int b = (int) (color.z * 255);
		int alpha = (int) (this.alpha * 255);
		return new byte[]{(byte)r, (byte) g, (byte) b, (byte) alpha};
	}
	
	public FloatBuffer getAsFloatBuffer() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(new float[] { color.x, color.y, color.z, alpha });
		buffer.flip();
		return buffer;
	}

	public float getR() {
		return color.x;
	}

	public float getG() {
		return color.y;
	}

	public float getB() {
		return color.z;
	}

	public Vector3f getVector() {
		return color;
	}
	
	public static Color interpolateColors(Color color1, Color color2, float blend, Color dest) {
		float color1Weight = 1 - blend;
		float r = (color1Weight * color1.color.x) + (blend * color2.color.x);
		float g = (color1Weight * color1.color.y) + (blend * color2.color.y);
		float b = (color1Weight * color1.color.z) + (blend * color2.color.z);
		if (dest == null) {
			return new Color(r, g, b);
		} else {
			return dest;
		}
	}

}
