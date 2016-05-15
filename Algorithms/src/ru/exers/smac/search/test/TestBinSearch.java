package ru.exers.smac.search.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import ru.exers.smac.search.BinSearch;

public class TestBinSearch {
	public static final int ARRAY_SIZE = Integer.MAX_VALUE-3;
	public static final int MAX_NO_MATCH_VALUE = 10;
	public static final int MIN_NO_MATCH_VALUE = 1;
	private int[] testArr;
	int[] noMatchedValues;
	private int randomIndexValue;
	private int zeroIndexValue;
	private int maxIndexValue;

//	@Before
	public void setUp() throws Exception {
		testArr = new int[ARRAY_SIZE];
		Random rnd = new Random();
		for (int i = 0; i < testArr.length; i++) {
			testArr[i] = rnd.nextInt();
		}
		Arrays.sort(testArr);
		randomIndexValue = testArr[rnd.nextInt(ARRAY_SIZE)];
		zeroIndexValue = testArr[0];
		maxIndexValue = testArr[ARRAY_SIZE - 1];
	}

	@Test
	public void testRandomIndex() {
		prepareTestValues(Integer.MAX_VALUE-3);
		long time = System.nanoTime();
		int actualValue = testArr[BinSearch.search(testArr, randomIndexValue)];
		assertEquals(randomIndexValue, actualValue);
		time = System.nanoTime() - time;
		System.out.println("random index: " + time + " ns");
	}
	
	@Test
	public void testZeroIndex() {
		prepareTestValues(1024);
		long time = System.nanoTime();
		int actualValue = testArr[BinSearch.search(testArr, zeroIndexValue)];
		assertEquals(zeroIndexValue, actualValue);
		time = System.nanoTime() - time;
		System.out.println("zero index: " + time + " ns");
	}
	
	@Test
	public void testMaxIndex() {
		prepareTestValues(1024);
		long time = System.nanoTime();
		int actualValue = testArr[BinSearch.search(testArr, maxIndexValue)];
		assertEquals(maxIndexValue, actualValue);
		time = System.nanoTime() - time;
		System.out.println("max index: " + time + " ns");
	}
	
	@Test
	public void testAboveMax() {
		prepareNoMatchArray();
		assertNull(BinSearch.search(noMatchedValues, MAX_NO_MATCH_VALUE + 1));
	}
	
	@Test
	public void testBelowMin() {
		prepareNoMatchArray();
		assertNull(BinSearch.search(noMatchedValues, MIN_NO_MATCH_VALUE - 1));
	}
	
	private void prepareTestValues(int size) {
		testArr = new int[size];
		Random rnd = new Random();
		for (int i = 0; i < testArr.length; i++) {
			testArr[i] = rnd.nextInt();
		}
		Arrays.sort(testArr);
		randomIndexValue = testArr[rnd.nextInt(size)];
		zeroIndexValue = testArr[0];
		maxIndexValue = testArr[size - 1];
	}
	
	private void prepareNoMatchArray() {
		noMatchedValues = new int[4];
		Random rnd = new Random();
		for (int i = 0; i < noMatchedValues.length; i++) {
			noMatchedValues[i] = rnd.nextInt(MAX_NO_MATCH_VALUE) + MIN_NO_MATCH_VALUE;
		}
		Arrays.sort(noMatchedValues);
	}
}
