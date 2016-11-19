package ru.exers.smac.structures;

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

	private final int arity;
	
	private T[] heap;
	
	
}
