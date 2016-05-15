package ru.exers.smac.sort;

import java.util.Comparator;

public final class InsertionSort {
	
	public static <E extends Comparable<E>> void sort(E[] array) {
		if (array == null) return;
		for(int j = 1; j < array.length; j++) {
			E key = array[j];
			int i = j - 1;
			while (i >= 0 && isGreater(array[i], key)) {
				array[i+1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}
	
	public static <E> void sort(E[] array, Comparator<E> comparator) {
		if (array == null) return;
		for(int j = 1; j < array.length; j++) {
			E key = array[j];
			int i = j - 1;
			while (i >= 0 && isGreater(array[i], key, comparator)) {
				array[i+1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	private static <E> boolean isGreater(E e1, E e2, Comparator<E> comparator) {
		return comparator != null ? comparator.compare(e1, e2) > 0 : false;
	}

	private static <E extends Comparable<E>> boolean isGreater(E e1, E e2) {	
		return e1 != null ? e1.compareTo(e2) > 0 : false;
	}
	
	

}
 