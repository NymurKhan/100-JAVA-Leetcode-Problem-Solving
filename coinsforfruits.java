class Solution {
    int dp[];
    public int minimumCoins(int[] prices) {
      
        dp = new int[prices.length];
        Arrays.fill(dp,-1);
        
      return f(prices,0,prices.length);
    }
    
    public int f(int arr[] , int idx, int n)
    {
       if(idx>=n) return 0;
        if(dp[idx]!=-1) return dp[idx];
       
       int minans =(int)1e9;
       int maxidx=2*idx+2;
        for(int i=idx+1;i<=Math.min(n,maxidx);i++)
        {
           minans=Math.min(minans,arr[idx]+f(arr,i,n));
        }
     return dp[idx]=minans;
   }
}