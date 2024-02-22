class Solution {
  public long minSum(int[] nums1, int[] nums2) {
    long[] sum = new long[2];
    int[] zero = new int[2];
    for (int num : nums1) {
      if (num == 0) {
        zero[0]++;
      }
      sum[0] += num;
    }

    for (int num : nums2) {
      if (num == 0) {
        zero[1]++;
      }
      sum[1] += num;
    }

    // both have zero, replace all zero with one in one array
    if (zero[0] > 0 && zero[1] > 0) {
      return Math.max(sum[0] + zero[0], sum[1] + zero[1]);
    }

    // one of array has zero
    if (zero[0] > 0 && zero[1] == 0) {
      if (sum[0] < sum[1] && sum[0] + zero[0] <= sum[1]) {
        return sum[1];
      }
    }

    if (zero[0] == 0 && zero[1] > 0) {
      if (sum[0] > sum[1] && sum[0] >= sum[1] + zero[1]) {
        return sum[0];
      }
    }

    // no zero
    if (zero[0] + zero[1] == 0) {
      if (sum[0] == sum[1]) {
        return sum[1];
      }
    }

    return -1;
  }
}