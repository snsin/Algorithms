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
	
	public static Tuple<Integer> findMaxSubarrSquareTime(Integer[] array) {
		Integer maxSum = null;
		int left = -1;
		int right = -1;
		for (int i = 0; i < array.length; i++) {
			Integer sum = array[i];
			if (maxSum == null || sum > maxSum) {
				maxSum = sum;
				left = right = i;
			}
			for (int j = i + 1; j < array.length; j++) {
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
	
	public static void main(String...strings) {
		Integer[] testArr =  new Integer[] {-4, -3, -2, -1, -1, 1, -3};
		Tuple<Integer> res = findMaxSubarrSquareTime(testArr);
		System.out.printf("%d, %d, S = %d", res.low, res.high, res.sum);
	}
}
