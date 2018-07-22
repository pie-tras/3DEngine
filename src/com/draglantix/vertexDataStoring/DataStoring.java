package com.draglantix.vertexDataStoring;

import java.nio.ByteBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.util.Color;
import com.draglantix.util.DataUtils;

public class DataStoring {

	public static void packVertexData(Vector3f position, Vector3f normal, Color color, ByteBuffer buffer) {
		packVertexData(position.x, position.y, position.z, normal, color, buffer);
	}

	public static void packVertexData(Vector2f position, byte[] indicators, ByteBuffer buffer) {
		buffer.putFloat(position.x);
		buffer.putFloat(position.y);
		buffer.put(indicators);
	}

	public static void packVertexData(float x, float y, float z, Vector3f normal, Color color, ByteBuffer buffer) {
		store3Floats(buffer, x, y, z);
		storeNormal(buffer, normal);
		storecolor(buffer, color);
	}

	public static void packVertexData(float x, float y, float z, Color color, ByteBuffer buffer) {
		store3Floats(buffer, x, y, z);
		storecolor(buffer, color);
	}

	private static void store3Floats(ByteBuffer buffer, float a, float b, float c) {
		buffer.putFloat(a);
		buffer.putFloat(b);
		buffer.putFloat(c);
	}

	private static void storeNormal(ByteBuffer buffer, Vector3f normal) {
		int packedInt = DataUtils.pack_2_10_10_10_REV_int(normal.x, normal.y, normal.z, 0);
		buffer.putInt(packedInt);
	}

	private static void storecolor(ByteBuffer buffer, Color color) {
		byte[] colorBytes = color.getAsBytes();
		buffer.put(colorBytes);
	}

}
