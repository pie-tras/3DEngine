package com.draglantix.engine;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.util.Color;

public class Light {

	private Vector3f direction;
	private Color color;
	private Vector2f lightBias;

	public Light(Vector3f direction, Color color, Vector2f lightBias) {
		this.direction = direction;
		this.direction.normalize();
		this.color = color;
		this.lightBias = lightBias;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public Color getColor() {
		return color;
	}

	public Vector2f getLightBias() {
		return lightBias;
	}

}
