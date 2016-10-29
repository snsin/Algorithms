package ru.exers.smac.structures;

public class Heap<T> {
	
	private static final int DEFAULT_ARRAY_SIZE = 16;
	
	private T[] heap;
	
	private int size = 0;
	
	public Heap(int length) {
		heap = createArray(length);
	}
	
	public Heap() {
		heap = createArray(DEFAULT_ARRAY_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T[] createArray(int length) {
		return (T[]) new Object[length];
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

}
