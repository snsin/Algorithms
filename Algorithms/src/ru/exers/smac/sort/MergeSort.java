package ru.exers.smac.sort;

import java.util.ArrayList;

public class MergeSort {
		
	public static <T extends Comparable<T>> void sort(T[] array) {
		if (array != null) {
			sort(array, 0, array.length);
		}	
	}
	
	private static <T extends Comparable<T>> void sort(
			T[] array, int p, int r) {
		if (r > p + 1) {
			int q = (r + p + 1) / 2;
			sort(array, p, q);
			sort(array, q, r);
			merge(array, p, q, r);
		}
	}
	
	@SuppressWarnings("unused")
	private static <T extends Comparable<T>> void merge(
			T[] array, int p, int q, int r, boolean ignored) {
		ArrayList<T> left = createSubArray(array, p, q - p, true);
		ArrayList<T> rigth = createSubArray(array, q, r - q, true);
		int i = 0;
		int j = 0;

		while (i < left.size() && j < rigth.size()) {
			if (left.get(i).compareTo(rigth.get(j)) < 0) {
				array[p++] = left.get(i++);
			} else {
				array[p++] = rigth.get(j++);
			}
		}
		while (i < left.size()) {
			array[p++] = left.get(i++);
		}
		while (j < rigth.size()) {
			array[p++] = rigth.get(j++);
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
	
	private static <T extends Comparable<T>> ArrayList<T> createSubArray(
			T[] array, int p, int length, boolean ignored) {
		ArrayList<T> subArray = new ArrayList<>();
		for (int i = 0; i < length && p + i < array.length; i++) {
			subArray.add(array[p + i]);
		}
		
		return subArray;
	}
	
	private static <T extends Comparable<T>> T[] createSubArray(
			final T[] array, final int srcPos, final int length) {
		@SuppressWarnings("unchecked")
		T[] subArray = (T[]) new Comparable[length];
		System.arraycopy(array, srcPos, subArray, 0, length);
		return subArray;
		
	}

}
