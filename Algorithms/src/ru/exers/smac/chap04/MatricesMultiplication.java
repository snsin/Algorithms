package ru.exers.smac.chap04;

import java.util.Arrays;
import java.util.Random;

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
			final int startRow = x > 0 ? (x - 1) * size + this.startRow : 0;
			final int startCol = y > 0 ? (y - 1) * size + this.startCol: 0;
			return new MatrixRef(this.matrix, size, startRow, startCol);
		}
		
		public int getElemOneOne() {
			return matrix[startRow][startCol];
		}
		
		public int get(final int i, final int j) {
			return matrix[startRow + i][startCol + j];
		}
		
		public int[] getRow(final int i) {
			return matrix[startRow + i];
		}
		
		public void assign(MatrixRef anotherMatrix) {
			final int size = Math.min(this.size, anotherMatrix.size);
			for (int i = 0; i < size; i++){
				System.arraycopy(anotherMatrix.getRow(i), anotherMatrix.startCol, 
						this.getRow(i), this.startCol, size);
				
			}
		}
	}

	private static final int MAX_VALUE = 9;
	
	private static final Random RAND = new Random();
	
	public static MatrixRef sum(MatrixRef matrixA, MatrixRef matrixB) {
		final MatrixRef result;
		if(matrixA.size != matrixB.size || matrixA.size == 0) {
			result = null;
		} else {
			final int[][] sum = new int[matrixA.size][matrixA.size];
			for (int i = 0; i < sum.length; i++) {
				for (int j = 0; j < sum[i].length; j++) {
					sum[i][j] = matrixA.get(i, j) + matrixB.get(i, j);
				}
			}
			result = new MatrixRef(sum);
		}
		return result;
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
	
	
	
	public static MatrixRef recursiveMul(MatrixRef matrixA, MatrixRef matrixB) {
		final int size = matrixA.size;
		final int[][] arrC = new int[size][size];
		final MatrixRef matrixC = new MatrixRef(arrC);
		if (size == 1) {
			arrC[0][0] = matrixA.get(0,0) * matrixB.get(0, 0);
		} else {
			MatrixRef matrixA11 = matrixA.split(1, 1);
			MatrixRef matrixA12 = matrixA.split(1, 2);
			MatrixRef matrixA21 = matrixA.split(2, 1);
			MatrixRef matrixA22 = matrixA.split(2, 2);
			
			MatrixRef matrixB11 = matrixB.split(1, 1);
			MatrixRef matrixB12 = matrixB.split(1, 2);
			MatrixRef matrixB21 = matrixB.split(2, 1);
			MatrixRef matrixB22 = matrixB.split(2, 2);
			
			MatrixRef matrixC11 = matrixC.split(1, 1);
			MatrixRef matrixC12 = matrixC.split(1, 2);
			MatrixRef matrixC21 = matrixC.split(2, 1);
			MatrixRef matrixC22 = matrixC.split(2, 2);
			

			matrixC11.assign(sum(recursiveMul(matrixA11, matrixB11),
					recursiveMul(matrixA12, matrixB21)));
			matrixC12.assign(sum(recursiveMul(matrixA11, matrixB12), 
					recursiveMul(matrixA12, matrixB22)));
			matrixC21.assign(sum(recursiveMul(matrixA21, matrixB11),
					recursiveMul(matrixA22, matrixB21)));
			matrixC22.assign(sum(recursiveMul(matrixA21, matrixB12), 
					recursiveMul(matrixA22, matrixB22)));
		}
		return matrixC;
	}
	
	public static void main(String...args) {
		int[][] arrA = new int[][] { 
			{4, 6, 6, 8},
			{3, 3, 1, 4},
			{9, 8, 6, 2},
			{4, 7, 8, 2}
		};
		int[][] arrB = new int[][] { 
			{7, 4, 5, 9},
			{2, 8, 8, 4},
			{5, 5, 5, 7},
			{2, 9, 8, 1}
		};
		int[][] arrC = iterMul(arrA, arrB);
		
		printMatrix(arrA);
		printMatrix(arrB);
		printMatrix(arrC);
		MatrixRef matrixA = new MatrixRef(arrA);
		MatrixRef matrixB = new MatrixRef(arrB);
		printMatrixRef(recursiveMul(matrixA, matrixB));
	}
	
	public static void printMatrix(final int[][] matrix) {
		System.out.println("\n======================");
		for (int i = 0; matrix != null && i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("======================");
	}
	
	public static void printMatrixRef(MatrixRef matrix) {
		System.out.println("======================");
		for (int i = 0; i < matrix.size; i++) {
			for (int j = 0; j < matrix.size; j++) {
				System.out.printf("%4d", matrix.matrix[matrix.startRow + i][matrix.startCol+j]);
			}
			System.out.println();
			
		}
		System.out.println("======================");
		
	}
	
	public static int[][] createSquareMatrix(int size) {
		if (size < 1) {
			return null;
		}
		int[][] matrix =  new int[size][size];
		for (int[] row : matrix) {
			for (int i = 0; i < row.length; i++) {
				row[i] = 1 + RAND.nextInt(MAX_VALUE);
			}
		}
		return matrix;
		
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
	
	

}
