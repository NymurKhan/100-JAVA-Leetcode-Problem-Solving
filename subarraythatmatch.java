class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int m = pattern.length;
        int n = nums.length;
        int[] dp = new int[n-1];
        for(int i=0;i<n-1;i++) {
            if(nums[i+1]==nums[i]) dp[i] = 0;
            else if(nums[i+1]<nums[i]) dp[i] = -1;
            else dp[i] = 1;
        }
        return KMP(dp, pattern);
    }
    
    private int KMP(int[] s, int[] t) {
        int[] table = buildNextTable(t);
        int res = 0;
        int i=0,j=0;
        while(i<s.length) {
            if(s[i]==t[j]) {
                i++;
                j++;
                if(j==t.length) {
                    res++;
                    j = table[j-1];
                }
            }
            else{
                if(j>0) j = table[j-1];
                else i++;
            }
        }
        return res;
    }

    private int[] buildNextTable(int[] t) {
        int[] table = new int[t.length];
        table[0] = 0;
        int i=1,j=0;
        while(i<t.length) {
            if(t[i]==t[j]) {
                table[i] = j+1;
                i++;
                j++;
            }
            else{
                if(j>0) {
                    j = table[j-1];
                }
                else{
                    table[i] = 0;
                    i++;
                }
            }
        }
        return table;
    }
    
}