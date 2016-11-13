package ru.exers.smac.structures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TryHeap {
	
	private static final Random RND = new Random();

	public static void main(String...args) {
		Integer[] arr = createIntegerArray(7, 20);
		System.out.println(Arrays.toString(arr));
		Heap<Integer> h = new Heap<>(arr, Comparator.reverseOrder());
		h.sort();
		System.out.println(Arrays.toString(h.getArray()));
		PriorityQueue<Integer, String> pq = new PriorityQueue<>(16);
		pq.insert(5, "first");
		pq.insert(0, "fifth");
		pq.insert(1, "fourth");
		pq.insert(2, "third");
		pq.insert(4, "second");
		while (!pq.isEmpty()) {
			System.out.println(pq.extracrMax().getValue());
		}
		
	}			

	private static Integer[] createIntegerArray(final int size, final int maxValue) {
		final Integer[] result = new Integer[size]; 
		for (int i = 0; i < result.length; i++) {
			result[i] = RND.nextInt(maxValue);
		}
		return result;
	}

}
