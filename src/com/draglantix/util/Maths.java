package com.draglantix.util;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {
	
	public static float clamp(float value, float min, float max){
		return Math.max(Math.min(value, max), min);
	}

	public static Vector3f calcNormal(Vector3f vertex0, Vector3f vertex1, Vector3f vertex2) {
		Vector3f tangentA = vertex1.sub(vertex0);
		Vector3f tangentB = vertex2.sub(vertex0);
		Vector3f normal = tangentA.cross(tangentB);
		normal.normalize();
		return normal;
	}
	
	public static void updateViewMatrix(Matrix4f viewMatrix, float x, float y, float z, float pitch, float yaw){
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0));
		viewMatrix.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0));
		Vector3f negativeCameraPos = new Vector3f(-x, -y, -z);
		viewMatrix.translate(negativeCameraPos);
	}
	
}
