package ru.exers.smac.structures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
	
	private void increaseSize() {
		final int newLength = heap.length + heap.length / 2;
		heap = Arrays.copyOf(heap, newLength);
	}
	
	
}
