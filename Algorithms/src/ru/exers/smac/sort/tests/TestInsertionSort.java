package ru.exers.smac.sort.tests;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.exers.smac.sort.InsertionSort;

public class TestInsertionSort {
	public static final int ARRAY_SIZE = 1000;
	Integer[] array;
	Random rnd;
	Comparator<Integer> comp;

	@Before
	public void setUp() throws Exception {
		rnd = new Random();
		comp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};
		array = new Integer[ARRAY_SIZE];
		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt();
		}	
	}

	@Test
	public void testComparatorSort() {
		InsertionSort.sort(array, comp);
		assertTrue(isSortedisSortedDescending(array));
	}
	
	@Test
	public void testComparableSort() {
		InsertionSort.sort(array);
		assertTrue(isSortedAscending(array));
	}

	private boolean isSortedAscending(Integer[] array2) {
		for (int i = 1; i < array2.length; i++) {
			if (array2[i-1].compareTo(array2[i]) > 0) {
				return false;
			}
		}
		return true;
	}

	private boolean isSortedisSortedDescending(Integer[] array2) {
		for (int i = 1; i < array2.length; i++) {
			if (array2[i-1].compareTo(array2[i]) < 0) {
				return false;
			}
		}
		return true;
	}

}
