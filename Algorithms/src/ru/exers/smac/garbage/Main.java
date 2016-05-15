package ru.exers.smac.garbage;

import java.util.Arrays;
import java.util.Random;

import ru.exers.smac.chap02.Ex0307;

public class Main {
	private static Random rnd = new Random();

	public static void main(String[] args) {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rnd.nextInt(Integer.MAX_VALUE/2);
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(arr[1] + " + " + arr[5] + " = " + (arr[1] + arr[5]));
		Ex0307.Result res = Ex0307.indexes(Integer.MAX_VALUE, arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(res.first + " : " + res.second);
		if (res.first >= 0 && res.second >= 0) {
			System.out.println(
					arr[res.first] + " + " + arr[res.second] + " = " + (arr[res.first] + arr[res.second]));
		}

	}
	
	
	public static void printWeirdLog(int value) {
		for (long c = value; c > 0; c /= 2) {
			int i = (int) ((c + 3) / 4);
			System.out.printf("c: %12d\ti: %12d\n", c, i);
		}
		
	}

}
