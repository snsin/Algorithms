package ru.exers.smac.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class MergeKLists {
	public static class NonincreasingHeap<T extends Comparable<? super T>> {
		
		private static final int DEFAULT_SIZE = 16;
		
		private T[] heap;
		
		private final Comparator<? super T> cmp = (T o1, T o2) -> {
			if (o1 == null) {return 1;}
			if (o2 == null) {return -1;}
			return o1.compareTo(o2);
		};
		
		int size = 0;
		
		@SuppressWarnings("unchecked")
		public NonincreasingHeap(final int size) {
			heap = (T[]) (new Comparable<?>[size > 0 ? size : DEFAULT_SIZE]);
		}
		
		public int parent(int i) {
			return (i - 1)  / 2;
		}
		
		public int left(int i) {
			return 2 * i + 1;
		}
		
		public int right(int i) {
			return 2 * i + 2;
		}
		
		public T get(int i) {
			return heap[i];
		}
		
		
		public int size() {
			return size;
		}
		
		public int capacity() {
			return heap.length;
		}
		
		
		public boolean isEmpty() {
			return size <= 0;
		}
		
		public void minHeapify(int i) {
			int left = left(i);
			int right = right(i);
			int lowest = min(left, i, right);
			while (lowest != i) {
				swap(i, lowest);
				i = lowest;
				left = left(i);
				right = right(i);
				lowest = min(left, i, right);
			}
			
		}
		
		public void buildMinHeap() {
			for (int i = (size / 2) - 1; i >= 0; i--){
				minHeapify(i);
			}
		}
		
		public T extractMin() {
			if (size < 1) {
				throw new NoSuchElementException();
			}
			final T min = heap[0];
			heap[0] = heap[size - 1];
			size--;
			minHeapify(0);
			return min;
		}
		
		public void decreaseKey(int i, final T key) {
			if (cmp.compare(heap[i], key) < 0) {
				throw new IllegalArgumentException("Old key less than new one");
			}
			heap[i] = key;
			while (i > 0 && lessThan(i, parent(i))) {
				swap(i, parent(i));
				i = parent(i);
			}
		}
		
		public void insert(T element) {
			if (size >= heap.length) {
				increaseCapacity();
			}
			heap[size] = null;
			size++;
			decreaseKey(size - 1, element);
		}
		
		private void increaseCapacity() {
			final int newLength = heap.length + heap.length / 2;
			heap = Arrays.copyOf(heap, newLength);
			
		}

		private void swap(int i, int j) {
			final T dump = heap[i];
			heap[i] = heap[j];
			heap[j] = dump;
			
		}

		private int min(final int l, final int i, final int r) {
			int lowest = i;
			if (l < size && lessThan(l, i)) { 
				lowest = l; 
			} 
			if (r < size && lessThan(r, lowest)) {
				lowest = r;
			}
			return lowest;
		}
		
		private boolean lessThan(final int i, final int j) {
			return cmp.compare(heap[i], heap[j]) < 0;
		}
	}

	private static final Random RND = new Random();
	
	private static final int K = 10;
	
	private static final int LIST_LENGTH = 10;
	
	public static void main(String...args) {
		@SuppressWarnings("unchecked")
		List<Integer>[] a = (List<Integer>[]) new List<?>[K];
		for (int i = 0; i < a.length; i++) {
			int minVal = RND.nextInt(20);
			int maxVal = minVal + 10 + RND.nextInt(20);
			a[i] = new ArrayList<>(LIST_LENGTH);
			addValues(a[i], LIST_LENGTH, minVal, maxVal);
			Collections.sort(a[i]);
		}
		
		for (List<Integer> list : a) {
			System.out.println(Arrays.toString(list.toArray()));
		}
		
		List<Integer> res =  mergeLists(a);
		
		System.out.println(Arrays.toString(res.toArray()));
	
	}

	private static void addValues(List<Integer> list, int listLength, int minVal, int maxVal) {
		while (listLength > 0) {
			list.add(RND.nextInt(maxVal - minVal) + minVal);
			listLength--;
		}
		
	}
	
	private static <T extends Comparable<? super T>> List<T> mergeLists(List<T>[] lists) {
		final List<T> resultList = new ArrayList<>();
		final NonincreasingHeap<T> heap = new NonincreasingHeap<>(K);
		final int maxListLength = maxLength(lists);
		for (int i = 0; i < maxListLength; i++) {
			for (List<T> list : lists) {
				if (i < list.size()) {
					heap.insert(list.get(i));
				}	
			}
			resultList.add(heap.extractMin());
		}
		while (!heap.isEmpty()) {
			resultList.add(heap.extractMin());
		}
		return resultList;
	}

	private static <T> int maxLength(List<T>[] lists) {
		int max = 0;
		for (List<T> list : lists) {
			final int currentLength = list.size();
			max = currentLength > max ? currentLength : max;
		}
		return max;
	}
	
	
}
