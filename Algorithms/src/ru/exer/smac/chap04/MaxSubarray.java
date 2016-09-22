package ru.exer.smac.chap04;

import java.util.Arrays;

import ru.exer.smac.chap04.MaxSubarray.Tuple;

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
	
	public static Tuple<Integer> findMaxSubarrSquareTime(final Integer[] array,
			final int low, final int high) {
		Integer maxSum = null;
		int left = -1;
		int right = -1;
		for (int i = low; i < high; i++) {
			Integer sum = array[i];
			if (maxSum == null || sum > maxSum) {
				maxSum = sum;
				left = right = i;
			}
			for (int j = i + 1; j < high; j++) {
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
		if( high <= low ) {
			return new Tuple<Integer>(low, high, array[low]);
		}
		else {
			final int mid = (low + high) / 2;
			final Tuple<Integer> leftMax = findMaxSubarray(array, low, mid);
			final Tuple<Integer> rigthMax = findMaxSubarray(array, mid + 1, high);
			final Tuple<Integer> crossMax = findMaxCrossingSubarray(array, low, mid, high);
			return max(leftMax, rigthMax, crossMax);
		}
	}
	
	
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

	private static boolean isGreateEqual(Tuple<Integer> leftMax, Tuple<Integer> rigthMax) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String...strings) {
		Integer[] testArr =  new Integer[] {-4, -3, -2, -1, -1, 1, -3};
		Tuple<Integer> res = findMaxSubarray(testArr, 0, testArr.length - 1);
		System.out.printf("%d, %d, S = %d", res.low, res.high, res.sum);
	}
}
