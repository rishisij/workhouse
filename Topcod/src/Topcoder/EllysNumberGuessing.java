package Topcoder;

public class EllysNumberGuessing {
	/*
	 * Given an array with both positive and negative numbers, find sub-array
	 * with maximum sum. Consider sub-array to consist of consecutive elements
	 * from the array. Class: EllysNumberGuessing Method: getNumber Parameters:
	 * int[], int[] Returns: int Method signature: int getNumber(int[] guesses,
	 * int[] answers)
	 */
	public static void main(String[] args) {
		int[] a = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 0) {
				for (int j = a.length - 1; j == i; j--) {
					if (a[j] > 0) {
						for (int k = j; k == i; k--) {
							sum += a[k];
						}
						System.out.println(sum);
						return;
					}
				}

			}
		}
	}
}
