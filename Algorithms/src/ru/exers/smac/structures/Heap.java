package ru.exers.smac.structures;

import java.util.Comparator;

public class Heap<T> {
	
	private static final int DEFAULT_ARRAY_SIZE = 16;
	
	private T[] heap;
	
	private int size = 0;
	
	private Comparator<T> comparator;
	
	public Heap(int length) {
		heap = createArray(length);
	}
	
	public Heap() {
		heap = createArray(DEFAULT_ARRAY_SIZE);
	}
	
	public int parent(int i) {
		return (i - 1) / 2;
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
	
	public void maxHeapify(int i) {
		final int l = left(i);
		final int r = right(i);
		int largest;
		if (l <= size && greaterThan(l, i)) {
			largest = l;
		} else {
			largest = i;
		}
		if (r <= size && greaterThan(r, i)) {
			largest = r;
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest);
		}
	
	}
	
	public void buildMaxHeap() {
		for (int i = (size - 1)/ 2; i >= 0; i--){
			
		}
	}

	private void swap(int i, int j) {
		T dummy = heap[i];
		heap[i] = heap[j];
		heap[j] =  dummy;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] createArray(int length) {
		return (T[]) new Object[length];
	}

	@SuppressWarnings("unchecked")
	private static <T> Comparator<T> createDefaultComp(T o) {
		final Comparator<T> comp;
		if (o instanceof Comparable<?>) {
			comp = (o1, o2) -> ((Comparable<T>)o1).compareTo(o2);
		} else {
			comp = (o1, o2) -> 0;
		}
		return comp;
	}

	private boolean lessThan(int index1, int index2) {
		return comparator.compare(heap[index1], heap[index2]) < 0;
	}

	private boolean greaterThan(int index1, int index2) {
		return comparator.compare(heap[index1], heap[index2]) > 0;
	}

}
