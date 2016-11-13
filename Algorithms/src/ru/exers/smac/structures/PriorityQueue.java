package ru.exers.smac.structures;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import java.util.NoSuchElementException;

public class PriorityQueue<K extends Comparable<? super K>, V> {
	
	Comparator<Map.Entry<K, V>> cmpWithNull = (o1, o2) -> {
		if (o1 == null) {return -1;}
		if (o2 == null) {return 1;}
		if (o1.getKey() == null) {return -1;}
		if (o2.getKey() == null) {return 1;}
		
		return o1.getKey().compareTo(o2.getKey());
	}; 
	
	private Heap<Map.Entry<K, V>> heap;
	
	public PriorityQueue(final int size) {
		heap = new Heap<Map.Entry<K, V>>(size, cmpWithNull);
	}
	
	public Entry<K, V> maximum() {
		return heap.get(0);
	}
	
	public Entry<K, V> extracrMax() {
		if (heap.size() < 1) {
			throw new NoSuchElementException();
		}
		heap.swap(0, heap.size() - 1);
		Map.Entry<K, V> max = heap.get(heap.size() - 1);
		heap.decSize();
		heap.maxHeapify(0);
		return max;
	}
	
	public void increaseKey(int i, K key) {
		if (cmpWithNull
				.compare(new AbstractMap.SimpleEntry<K,V>(key, null), heap.get(i)) < 0) {
			throw new IllegalArgumentException();
		}
		heap.set(new AbstractMap.SimpleEntry<K,V>(key, heap.get(i).getValue()), i);

		while (i > 0 && cmpWithNull.compare(heap.get(i), heap.get(heap.parent(i))) > 0) {
			heap.swap(i, heap.parent(i));
			i = heap.parent(i);
		}
	}
	
	public void insert(K key, V value) {
		if (heap.size() >= heap.capacity()) {
			heap.increaseCapacity();
		}
		heap.set(new AbstractMap.SimpleEntry<K, V>(null, value), heap.size());
		heap.incSize();
		increaseKey(heap.size() - 1, key);
	}
	
	public boolean isEmpty() {
		return heap.size() <= 0;
	}

}
