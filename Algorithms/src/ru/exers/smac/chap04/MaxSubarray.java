package ru.exers.smac.chap04;

import java.util.Random;

public class MaxSubarray {
	
	public static class Tuple<V> {
		
		public final int low;
		
		public final int high;
		
		public final V sum;
		
		public Tuple(final int low, final int high, final V sum) {
			this.low = low;
			this.high = high;
			this.sum = sum;
		}
	}
	private final static Random rnd = new Random();
	private static final int BOUND = 100;
	
	public static Tuple<Integer> findMaxSubarrSquareTime(final Integer[] array,
			final int low, final int high) {
		Integer maxSum = null;
		int left = -1;
		int right = -1;
		for (int i = low; i <= high; i++) {
			Integer sum = array[i];
			if (maxSum == null || sum > maxSum) {
				maxSum = sum;
				left = right = i;
			}
			for (int j = i + 1; j <= high; j++) {
				sum += array[j];
				if (sum > maxSum) {
					maxSum = sum;
					left = i;
					right = j;
				}
			}
		}
		return new Tuple<Integer>(left, right, maxSum);
		
	}
	
	public static Tuple<Integer> findMaxCrossingSubarray(final Integer[] array,
			final int low, final int mid, final int high) {
		Integer leftSum = null;
		Integer sum = 0;
		int maxLeft = -1;
		for (int i = mid; i >= low; i--) {
			sum += array[i];
			if (leftSum == null || sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		Integer rightSum = null;
		sum = 0;
		int maxRight = -1;
		for (int i = mid + 1; i <= high; i++) {
			sum += array[i];
			if (rightSum == null || sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		
		return new Tuple<Integer>(maxLeft, maxRight, leftSum + rightSum);
	}
	
	public static Tuple<Integer> findMaxSubarray(final Integer[] array,
			final int low, final int high) {
		if( high <= low + 50) {
			return findMaxSubarrSquareTime(array, low, high);
		}
		else {
			final int mid = (low + high) / 2;
			final Tuple<Integer> leftMax = findMaxSubarray(array, low, mid);
			final Tuple<Integer> rigthMax = findMaxSubarray(array, mid + 1, high);
			final Tuple<Integer> crossMax = findMaxCrossingSubarray(array, low, mid, high);
			return max(leftMax, rigthMax, crossMax);
		}
	}
	
	public static Tuple<Integer> findMaxSubarrayLinear(final Integer[] array) {
		Tuple<Integer> max = new Tuple<Integer>(-1, -1, null);
		Tuple<Integer> maxZeroToJ = new Tuple<Integer>(-1, -1, null);
		Tuple<Integer> minZeroToJ = new Tuple<Integer>(-1, -1, null);
		int currentSum = 0;
		for (int j = 0; j < array.length - 1; j++) {
			if (j == 0) {
				max = new Tuple<Integer>(0, 0, array[0]);
				maxZeroToJ = minZeroToJ = max;
			}
			
			currentSum += array[j];
						
			if (minZeroToJ.sum.compareTo(currentSum) > 0) {
				minZeroToJ = new Tuple<Integer>(0, j, currentSum);
			}
			
			if (maxZeroToJ.sum.compareTo(currentSum) < 0) {
				maxZeroToJ = new Tuple<Integer>(0, j, currentSum);
			}
			final Tuple<Integer> localMax;
			final Integer currentIToJPlusOne = currentSum - minZeroToJ.sum + array[j + 1];
			if (maxZeroToJ.sum.compareTo(currentIToJPlusOne) > 0) {
				localMax = new Tuple<Integer>(0, maxZeroToJ.high, maxZeroToJ.sum);
			} else {
				localMax = new Tuple<Integer>(minZeroToJ.high + 1, j + 1, currentIToJPlusOne);
			}
			max = max(max, localMax);
		}
		return max;
	}
	
	public static Tuple<Integer> findMaxSubarrayLinearB(final Integer[] array) {
		Tuple<Integer> max = new Tuple<Integer>(-1, -1, null);
		int currentSum = 0;
		int restartSumIndex = -1;
		for (int j = 0; j < array.length - 1; j++) {
			if (j == 0) {
				max = new Tuple<Integer>(0, 0, array[0]);
				restartSumIndex = 0;
			} 
			currentSum += array[j];
			
			if (currentSum < 0 && currentSum < array[j + 1]) {
				currentSum = 0;
				restartSumIndex = j + 1;
			}

			if (currentSum > max.sum) {
				max = new Tuple<Integer>(restartSumIndex, j, currentSum);
			} 
			
			if ( currentSum + array[j + 1] > max.sum) {
				max = new Tuple<Integer>(restartSumIndex, j + 1, currentSum + array[j + 1]);
			} 

		}
		return max;
	}
	
	@SafeVarargs
	private static Tuple<Integer> max(final Tuple<Integer>...values) {
		Tuple<Integer> maxTuple = null;
		Integer maxSum = null;
		for (Tuple<Integer> tuple : values) {
			if ( maxSum == null || tuple.sum.compareTo(maxSum) >= 0) {
				maxSum = tuple.sum;
				maxTuple = tuple;
			}
		}
		return maxTuple;
	}
	
	
//TODO Add benchmarks!
	public static void main(String...strings) {
		Integer[] testArr =  new Integer[10000000];
		for (int i = 0; i < testArr.length; i++) {
			testArr[i] = rnd.nextBoolean() ? -1 * rnd.nextInt(BOUND) 
					: rnd.nextInt(BOUND);
		}
		
		Tuple<Integer> res;
		long startTime, endTime;
		startTime = System.nanoTime();
		res = findMaxSubarray(testArr, 0, testArr.length - 1);
		endTime = System.nanoTime();
		System.out.printf("%d, %d, S = %d, %d\n", res.low, res.high, res.sum, (endTime - startTime) / 1000);
		startTime = System.nanoTime();
		res = findMaxSubarrayLinear(testArr);
		endTime = System.nanoTime();
		System.out.printf("%d, %d, S = %d, %d\n", res.low, res.high, res.sum, (endTime - startTime) / 1000);
		startTime = System.nanoTime();
		res = findMaxSubarrayLinearB(testArr);
		endTime = System.nanoTime();
		System.out.printf("%d, %d, S = %d, %d\n", res.low, res.high, res.sum, (endTime - startTime) / 1000);

	}
}
