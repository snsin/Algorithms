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
	
	private static <T extends Comparable<T>> void merge(
			T[] array, int p, int q, int r) {
		ArrayList<T> left = createSubArray(array, p, q - p);
		ArrayList<T> rigth = createSubArray(array, q, r - q);
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
	
	private static <T extends Comparable<T>> ArrayList<T> createSubArray(
			T[] array, int p, int length) {
		ArrayList<T> subArray = new ArrayList<>();
		for (int i = 0; i < length && p + i < array.length; i++) {
			subArray.add(array[p + i]);
		}
		
		return subArray;
	}

}
