package ru.exers.smac.search;

/*
 * Неоптимальная и запутанная реализация
 */
public class BinSearch {
	public static final Integer search(int[] array, int val) {
		Integer result = null;
		long c = array.length;
		int i = (int) c / 2;
		do {
			if (val < array[i]) {
				i -= (int) ((c + 3) / 4);
			} else if (val > array[i]) {
				i += (int) ((c + 3) / 4);
			} else {
				result = i;
				break;
			}
			c /= 2;
			
		} while (i >= 0 && i < array.length && c > 0);
		return result;
	}

}
