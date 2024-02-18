class Solution {
    long pref[];
    
    private boolean check(int[] nums, int len, long k)
    {
        long sum = Long.MAX_VALUE;
        for(int j=len;j<nums.length + 1;j++)
        {
            int i = j - len, mid = (i + j) / 2, tar = nums[mid];
            sum = Math.min(sum, tar * (mid - i) - (pref[mid] - pref[i]) + (pref[j] - pref[mid]) - tar * (j - mid));
        }
        return sum <= k;
    }
    
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length, l = 1, h = n, res = -1;
        pref = new long[n + 1];
        for(int i=0;i<n;i++) pref[i + 1] = pref[i] + nums[i];
        while(l <= h)
        {
            int mid = l + (h - l) / 2;
            if(check(nums, mid, k))
            {
                res = mid;
                l = mid + 1;
            }
            else
                h = mid - 1;
        }
        return res;
    }
}