package ru.exers.smac.sort.tests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.exers.smac.sort.MergeSort;

public class MergeSortTest {
	public static final int ARRAY_SIZE = 10000;
	Integer[] evenLengthArray;
	Integer[] oddLengthArray;
	Random rnd;

	@Before
	public void setUp() throws Exception {
		rnd = new Random();

		int evenLength = ARRAY_SIZE % 2 == 0 ? ARRAY_SIZE : ARRAY_SIZE + 1;
		int oddLength = evenLength + 1;
		evenLengthArray = new Integer[evenLength];
		oddLengthArray = new Integer[oddLength];
		
		for (int i = 0; i < evenLengthArray.length; i++) {
			evenLengthArray[i] = rnd.nextInt();
		}	
		
		for (int i = 0; i < oddLengthArray.length; i++) {
			oddLengthArray[i] = rnd.nextInt();
		}	
	}

	@Test
	public void testEven() {
		MergeSort.sort(evenLengthArray);
		assertTrue(SortCheck.isSortedAscending(evenLengthArray));
	}
	
	@Test
	public void testOdd() {
		MergeSort.sort(oddLengthArray);
		assertTrue(SortCheck.isSortedAscending(oddLengthArray));
		
	}
	
	@Test
	public void testOneElement() {
		Integer[] arrayOfOneElement = { 1 };
		MergeSort.sort(arrayOfOneElement);
		assertTrue(SortCheck.isSortedAscending(arrayOfOneElement));
	}
	
	@Test 
	public void testNullReference() {
		MergeSort.sort(null);
	}
	
	

}
