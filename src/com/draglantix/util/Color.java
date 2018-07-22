package com.draglantix.util;

import java.nio.FloatBuffer;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class Color {

	private Vector3f color = new Vector3f();
	private float alpha = 1;

	public Color(float r, float g, float b) {
		color.set(r, g, b);
	}

	public Color(Vector3f colour) {
		color.set(colour);
	}

	public Color(float r, float g, float b, float a) {
		color.set(r, g, b);
		this.alpha = a;
	}

	public Color(float r, float g, float b, boolean convert) {
		if (convert) {
			color.set(r / 255f, g / 255f, b / 255f);
		} else {
			color.set(r, g, b);
		}
	}

	public Color() {
	}

	public Vector3f getVector() {
		return color;
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

	public Color duplicate() {
		return new Color(color.x, color.y, color.z);
	}

	public void multiplyBy(Color colour) {
		this.color.x *= colour.color.x;
		this.color.y *= colour.color.y;
		this.color.z *= colour.color.z;
	}

	public void setColour(float r, float g, float b) {
		color.set(r, g, b);
	}

	public void setColour(Vector3f colour) {
		color.set(colour);
	}

	public void setColour(Color colour) {
		this.color.set(colour.color);
	}

	public void setColour(float r, float g, float b, float a) {
		color.set(r, g, b);
		this.alpha = a;
	}

	public void setR(float r) {
		color.x = r;
	}

	public void setG(float g) {
		color.y = g;
	}

	public void setB(float b) {
		color.z = b;
	}

	public boolean isEqualTo(Color colour) {
		return (color.x == colour.color.x && color.y == colour.color.y && color.z == colour.color.z);
	}

	public Color scale(float value) {
		color.normalize(value);
		return this;
	}

	public String toString() {
		return ("(" + color.x + ", " + color.y + ", " + color.z + ")");
	}

	public static Color sub(Color colLeft, Color colRight, Color dest) {
		if (dest == null) {
			return new Color(colLeft.color.sub(colRight.color));
		} else {
			colLeft.color.sub(colRight.color, dest.color);
			return dest;
		}
	}

	public Color getUnit() {
		Color colour = new Color();
		if (color.x == 0 && color.y == 0 && color.z == 0) {
			return colour;
		}
		colour.setColour(this);
		colour.scale(1f / length());
		return colour;
	}

	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}

	public float lengthSquared() {
		return color.lengthSquared();
	}
	
	public void setHsvColour(float hue, float sat, float value) {
		this.setColour(hsvToRgb(hue, sat, value));
	}

	public static Color hsvToRgb(float hue, float saturation, float value) {
		int h = (int) (hue * 6);
		float f = hue * 6 - h;
		float p = value * (1 - saturation);
		float q = value * (1 - f * saturation);
		float t =value * (1 - (1 - f) * saturation);
		switch (h) {
		case 0:
			return new Color(value, t, p);
		case 1:
			return new Color(q, value, p);
		case 2:
			return new Color(p, value, t);
		case 3:
			return new Color(p, q, value);
		case 4:
			return new Color(t, p, value);
		case 5:
			return new Color(value, p, q);
		default:
			return new Color();
		}
	}

	public static Color interpolateColours(Color colour1, Color colour2, float blend, Color dest) {
		float colour1Weight = 1 - blend;
		float r = (colour1Weight * colour1.color.x) + (blend * colour2.color.x);
		float g = (colour1Weight * colour1.color.y) + (blend * colour2.color.y);
		float b = (colour1Weight * colour1.color.z) + (blend * colour2.color.z);
		if (dest == null) {
			return new Color(r, g, b);
		} else {
//			dest.setColour(r, g, b);
			return dest;
		}
	}

	public static Color add(Color colour1, Color colour2, Color dest) {
		if (dest == null) {
			return new Color(colour1.color.add(colour2.color));
		} else {
			colour1.color.add(colour2.color, dest.color);
			return dest;
		}
	}

}
