package ru.exers.smac.sort;

public class MergeSort {
	
	public static <T extends Comparable<T>> void sort(T[] array) {
		sort(array, 0, array.length);
	}
	
	private static <T extends Comparable<T>> void sort(T[] array, int p, int r) {
		if (r > p - 1) {
			int q = (r + p + 1) / 2;
			sort(array, p, q);
			sort(array, q, r);
			merge(array, p, q, r);
		}
	}
	
	public static <T extends Comparable<T>> void merge(
			T[] array, int p, int q, int r) {
		T[] left = createSubArray(array, p, q - p);
		T[] rigth = createSubArray(array, q, r - q);
		int i = 0;
		int j = 0;
		while (i < left.length && j < rigth.length) {
			if (left[i].compareTo(rigth[j]) < 0) {
				array[p++] = left[i++];
			} else {
				array[p++] = rigth[j++];
			}
		}
		while (i < left.length) {
			array[p++] = left[i++];
		}
		while (j < rigth.length) {
			array[p++] = rigth[j++];
		}
		
	}
	
	private static <T extends Comparable<T>> T[] createSubArray(
			T[] src, int srcPos, int length) {
		@SuppressWarnings("unchecked")
		T[] subArray = (T[]) new Object[length];
		System.arraycopy(src, srcPos, subArray, 0, length);
		return subArray;
	}

}
