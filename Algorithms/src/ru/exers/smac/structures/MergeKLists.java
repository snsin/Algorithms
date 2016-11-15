package ru.exers.smac.structures;

import java.util.Arrays;
import java.util.Comparator;
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
		
		public void minHeapify(final int i) {
			int left = left(i);
			int right = right(i);
			int lowest = min(left, i, right);
			if (lowest != i) {
				swap(i, lowest);
				minHeapify(lowest);
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
	
	public static void main(String...args) {
		NonincreasingHeap<Integer> heap = new NonincreasingHeap<>(10);
		for (int i = 0; i < 9; i++) {
			final int val = RND.nextInt(30);
			System.out.println(val);
			heap.insert(val);
		}
		System.out.println("===============================");
		while (!heap.isEmpty()) {
			System.out.println(heap.extractMin());
		}
	}
}
