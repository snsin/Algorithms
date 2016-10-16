package ru.exers.smac.chap04;

import java.util.Arrays;

public class MatricesMultiplication {
	private static class MatrixRef {
		
		final int[][] matrix;
		
		final int size;
		
		final int startRow;
		
		final int startCol;
		
		public MatrixRef(final int[][] matrix) {
			this.matrix = matrix;
			this.size = matrix.length;
			this.startRow = 0;
			this.startCol = 0;
		}
		
		private MatrixRef(final int[][] matrix, final int size, 
				final int startRow, final int startCol) {
			this.matrix = matrix;
			this.size = size;
			this.startRow = startRow;
			this.startCol = startCol;
			
		}
		
		public MatrixRef split(final int x, final int y) {
			final int size =  this.size / 2;
			final int startRow = x > 0 ? (x - 1) * size : 0;
			final int startCol = y > 0 ? (y - 1) * size : 0;
			return new MatrixRef(this.matrix, size, startRow, startCol);
		}
	}
	
	public static int[][] iterMul(final int[][] matrixA, final int[][] matrixB) {
		int[][] result = null;
		if (checkMatrices(matrixA, matrixB)) {
			result = new int[matrixA.length][matrixB[0].length];
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[0].length; j++) {
					for (int k = 0; k < matrixB.length; k++) {
						result[i][j] += matrixA[i][k] *  matrixB[k][j];
					}
					
				}
			}
		}
		return result;
	}
	
	private static boolean checkMatrices(int[][] matrixA, int[][] matrixB) {
		final boolean result;
		if (matrixA == null || matrixB == null) {
			result = false;
		} else {
			result = isRectangleMatrix(matrixA) && isRectangleMatrix(matrixB)
					&& (matrixA[0].length == matrixB.length);
		}
		return result;
	}

	private static boolean isRectangleMatrix(int[][] matrix) {
		if (matrix.length < 1) {
			return false;
		}

		final int columnsCount = matrix[0].length;
		if (columnsCount < 1) {
			return false;
		}
		for (int i = 1; i < matrix.length; i++) {
			if (columnsCount != matrix[i].length) {
				return false;
			}
		}
		return true;
	}

	public static void main(String...args) {
		int[][] matrixA = new int[2][2];
		int[][] matrixB = new int[2][2];
		matrixA[0][0] =  1;
		matrixA[0][1] =  3;
		matrixA[1][0] =  7;
		matrixA[1][1] =  5;
		matrixB[0][0] =  6;
		matrixB[0][1] =  8;
		matrixB[1][0] =  4;
		matrixB[1][1] =  2;
		int[][] multiplied = iterMul(matrixA, matrixB);
		for (int i = 0; multiplied != null && i < multiplied.length; i++) {
			System.out.println(Arrays.toString(multiplied[i]));
		}
		System.out.println(multiplied);
	}

}
