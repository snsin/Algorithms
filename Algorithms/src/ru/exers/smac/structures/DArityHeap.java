package ru.exers.smac.structures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class DArityHeap<T extends Comparable<? super T>> {
	
	private static final class Comp {
		
		private static final <T extends Comparable<? super T>> Comparator<? super T> comparator() {
			return (o1, o2) -> {
				if (o1 == null) return -1;
				if (o2 == null) return 1;
				return o1.compareTo(o2);
			};
		}
	}
	
	private final static int DEFAULT_SIZE = 16;

	private final int arity;
	
	private final Comparator<? super T> cmp;
	
	private T[] heap;
	
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public DArityHeap(final int arity, final boolean isMax, final int size) {
		this.arity = arity < 2 ? 2 : arity;
		this.cmp = isMax ? Comp.comparator() : Collections.reverseOrder(Comp.comparator());
		final int initialCapacity = size < 2 ? DEFAULT_SIZE : size;
		heap = (T[]) new Comparable<?>[initialCapacity];
	}
	
	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return heap.length;
	}
	
	public boolean isEmpty() {
		return this.size <= 0;
	}
		
	private int child(int parentIndex, int childNumber) {
		return this.arity * parentIndex + childNumber;
	}
	
	private int parent(int childIndex) {
		return childIndex > 0 ? (childIndex - 1) / this.arity : 0;
	}
	
	private int[] getChildren(final int parentIndex) {
		final int[] children = new int[arity];
		for (int i = 0; i < children.length; i++) {
			children[i] = arity * parentIndex + i + 1;
		}
		return children;
	}
	
	private void heapify(int i) {
		int[] children = getChildren(i);
		int largest = indexOfMax(i, children);
		while(largest != i) {
			swap(i, largest);
			i = largest;
			children = getChildren(i);
			largest = indexOfMax(i, children);
		}
	}
	
	private void buildHeap() {
		int nodes = size > arity ? ( size / arity ) - 1 : 0;
		for (int i = nodes; i >= 0; i--) {
			heapify(i);
		}
	}
	
	private void swap(int i, int largest) {
		T dump = heap[i];
		heap[i] = heap[largest];
		heap[largest] = dump;
		
	}

	private void increaseSize() {
		final int newLength = heap.length + heap.length / 2;
		heap = Arrays.copyOf(heap, newLength);
	}
	
	private int indexOfMax(final int i, final int...children) {
		int largest = i;
		for (int child : children) {
			if (child < size && greaterThan(heap[child], heap[largest])) {
				largest = child;
			}
		}
		return largest;
	}
	
	private boolean greaterThan(T o1, T o2) {
		return this.cmp.compare(o1, o2) > 0;
	}
	
	private static final Random RND = new Random();
	
	public static void main(String...args) {
		DArityHeap<Integer> heap = new DArityHeap<>(4, true, 32);
		heap.heap = createIntegerArray(32, 20);
		heap.size =  32;
		
		System.out.println(Arrays.toString(heap.heap));
		System.out.println(Arrays.toString(heap.getChildren(7)));
		System.out.println(heap.indexOfMax(7, heap.getChildren(7)));
		
		heap.buildHeap();
		
		System.out.println(Arrays.toString(heap.heap));
		
	}
	
	private static Integer[] createIntegerArray(final int size, final int maxValue) {
		final Integer[] result = new Integer[size]; 
		for (int i = 0; i < result.length; i++) {
			result[i] = RND.nextInt(maxValue);
		}
		return result;
	}

	
}
