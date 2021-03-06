package ru.exers.smac.structures;

import java.util.Arrays;
import java.util.Comparator;

public class Heap<T> {
	
	private static final int DEFAULT_ARRAY_SIZE = 16;
	
	private T[] heap;
	
	private int size = 0;
	
	private Comparator<? super T> comparator;
	
	@SuppressWarnings("unchecked")
	public Heap(int length) {
		heap = (T[]) new Object[length];
	}
	
	public Heap(int length, Comparator<? super T> comparator) {
		this(length);
		this.comparator = comparator;
	}
	
	public Heap() {
		this(DEFAULT_ARRAY_SIZE);
	}
	
	public Heap(T[] array) {
		this();
		if (array != null && array.length > 0) {
			heap = array;
			size = array.length;
		}
		comparator = createDefaultComp(array[0]);
		buildMaxHeap();
	}
	
	public Heap(T[] array, Comparator<? super T> comparator) {
		this();
		if (array != null) {
			heap = array;
			size = array.length;
		} 
		this.comparator = comparator == null ?
				createDefaultComp(heap[0]) : comparator;
		buildMaxHeap();
	}
	
	public int parent(int i) {
		return i  / 2;
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

	public Object getComparator() {
		return comparator;
	}
	
	public void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest = largest(l, i, r);
		while (largest != i) {
			swap(i, largest);
			i = largest;
			l = left(largest);
			r = right(largest);
			largest = largest(l, i, r);
		}
	}
	
	public void buildMaxHeap() {
		for (int i = (size / 2) - 1; i >= 0; i--){
			maxHeapify(i);
		}
	}
	
	public void sort() {
		for (int i = size - 1; i > 0; i--){
			swap(0, i);
			size--;
			maxHeapify(0);
		}
	}
	
	void set(T value, int i) {
		heap[i] =  value;
		
	}

	T[] getArray() {
		return heap;
	}
	
	void decSize() {
		if (size > 0) {
			size--;
		}
	}

	void incSize() {
		size++;
		
	}

	void increaseCapacity() {
		final int newSize = heap.length == 0 ? 2 : heap.length + heap.length / 2;
		heap = Arrays.copyOf(heap, newSize);
		
	}

	void swap(final int i, final int j) {
		T dummy = heap[i];
		heap[i] = heap[j];
		heap[j] =  dummy;
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

	private int largest(final int l, final int i, final int r){
		int largest;
		if (l < size && greaterThan(l, i)) {
			largest = l;
		} else {
			largest = i;
		}
		if (r < size && greaterThan(r, largest)) {
			largest = r;
		}
		return largest;
	}

	@SuppressWarnings("unused")
	private boolean lessThan(int index1, int index2) {
		return comparator.compare(heap[index1], heap[index2]) < 0;
	}

	private boolean greaterThan(int index1, int index2) {
		return comparator.compare(heap[index1], heap[index2]) > 0;
	}

}
