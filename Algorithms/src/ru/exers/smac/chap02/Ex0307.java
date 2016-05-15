package ru.exers.smac.chap02;

import java.util.Arrays;

public class Ex0307 {
	public static class Result {
		public int first;
		public int second;
	}
	public static boolean isExist(int x, int[] set) {
		Arrays.sort(set);
		int l = -1;
		int m = set.length;
		while (m  > 0 && l < 0) {
			m--;
			int delta = x - set[m];
			l = Arrays.binarySearch(set, delta);
		}	
		return l >= 0;
	}
	
	public static Result indexes(int x, int[] set) {
		Arrays.sort(set);
		int l = -1;
		int m = set.length;
		while (m  > 0 && l < 0) {
			m--;
			int delta = x - set[m];
			l = Arrays.binarySearch(set, delta);
		}
		Result result = new Result();
		result.first = m;
		result.second = l;
		return result;
	}

}
