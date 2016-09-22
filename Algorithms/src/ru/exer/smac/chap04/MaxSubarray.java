package ru.exer.smac.chap04;

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
	
	public static void main(String...strings) {
		Integer[] testArr =  new Integer[] {-4, -3, -2, -1, -1, 1, -3};
		Tuple<Integer> res = findMaxSubarrSquareTime(testArr, 0, testArr.length);
		System.out.printf("%d, %d, S = %d", res.low, res.high, res.sum);
	}
}
