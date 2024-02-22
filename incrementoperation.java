class Solution {
    public long minIncrementOperations(int[] nums, int k) {
        long[] dp = new long[4];
        for (int i = nums.length - 1; i >= 0; i--) {
            long[] curr = new long[4];
            for (int lag = 3; lag >= 1; lag--)
                curr[lag] = lag == 3 ? dp[1] + Math.max(k - nums[i], 0) : Math.min(dp[lag + 1],
                        dp[1] + Math.max(0, k - nums[i]));
            dp = curr;
        }
        return dp[1];
    }
}