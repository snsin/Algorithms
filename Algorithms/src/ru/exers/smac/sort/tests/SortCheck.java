package ru.exers.smac.sort.tests;

public class SortCheck {
	
	
	public static <T extends Comparable<T>> boolean isSortedDescending(T[] array) {
		boolean result = true;
		for (int i = 1; result && i < array.length; i++) {
			if (array[i-1].compareTo(array[i]) < 0) {
				result = false;
			}
		}
		return result;
	}
	

	public static <T extends Comparable<T>> boolean isSortedAscending(T[] array) {
		boolean result = true;
		for (int i = 1; result && i < array.length; i++) {
			if (array[i-1].compareTo(array[i]) > 0 ) {
				result = false;
			}
		}
		return result;
	}
	
}