package com.draglantix.terrain;

import java.nio.ByteBuffer;

import org.joml.Vector3f;

import com.draglantix.util.Color;
import com.draglantix.util.Maths;
import com.draglantix.vertexDataStoring.DataStoring;

public class GridSquare {

	private final int row;
	private final int col;
	private final int lastIndex;
	private final Vector3f[] positions;
	private final Color[] colors;
	private final Vector3f normalLeft;
	private final Vector3f normalRight;

	public GridSquare(int row, int col, float[][] heights, Color[][] colors) {
		this.positions = calculateCornerPositions(col, row, heights);
		this.colors = calculateCornerColors(col, row, colors);
		this.lastIndex = heights.length - 2;
		this.row = row;
		this.col = col;
		boolean rightHanded = col % 2 != row % 2;
		this.normalLeft = Maths.calcNormal(positions[0], positions[1], positions[rightHanded ? 3 : 2]);
		this.normalRight = Maths.calcNormal(positions[2], positions[rightHanded ? 0 : 1], positions[3]);
	}
	
	public void storeSquareData(ByteBuffer buffer) {
		storeTopLeftVertex(buffer);
		if (row != lastIndex || col == lastIndex) {
			storeTopRightVertex(buffer);
		}
	}

	public void storeBottomRowData(ByteBuffer buffer) {
		if (col == 0) {
			storeBottomLeftVertex(buffer);
		}
		storeBottomRightVertex(buffer);
	}

	private Color[] calculateCornerColors(int col, int row, Color[][] colors) {
		Color[] cornerCols = new Color[4];
		cornerCols[0] = colors[row][col];
		cornerCols[1] = colors[row + 1][col];
		cornerCols[2] = colors[row][col + 1];
		cornerCols[3] = colors[row + 1][col + 1];
		return cornerCols;
	}

	private Vector3f[] calculateCornerPositions(int col, int row, float[][] heights) {
		Vector3f[] vertices = new Vector3f[4];
		vertices[0] = new Vector3f(col, heights[row][col], row);
		vertices[1] = new Vector3f(col, heights[row + 1][col], row + 1);
		vertices[2] = new Vector3f(col + 1, heights[row][col + 1], row);
		vertices[3] = new Vector3f(col + 1, heights[row + 1][col + 1], row + 1);
		return vertices;
	}

	private void storeTopLeftVertex(ByteBuffer buffer) {
		DataStoring.packVertexData(positions[0], normalLeft, colors[0], buffer);
	}

	private void storeTopRightVertex(ByteBuffer buffer) {
		DataStoring.packVertexData(positions[2], normalRight, colors[2], buffer);
	}

	private void storeBottomLeftVertex(ByteBuffer buffer) {
		DataStoring.packVertexData(positions[1], normalLeft, colors[1], buffer);
	}

	private void storeBottomRightVertex(ByteBuffer buffer) {
		DataStoring.packVertexData(positions[3], normalRight, colors[3], buffer);
	}

}
