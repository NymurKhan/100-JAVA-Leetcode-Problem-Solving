class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        var map = new HashMap<Integer,Integer>();
        int n = nums.length;
        long res = -(long)1e18;
        long prefix[] = new long[n+1];
        prefix[0] = nums[0];
        for(int i = 1; i<n; i++)prefix[i] = nums[i]+prefix[i-1];
        prefix[n] = prefix[n-1];
        
        for(int i = 0; i<n; i++){
            if(map.containsKey(nums[i]-k)){
                res = Math.max(prefix[i]-(map.get(nums[i]-k) == 0?0:prefix[map.get(nums[i]-k)-1]),res);
            }
            
            if(map.containsKey(nums[i]+k)){
                res = Math.max(prefix[i]-(map.get(nums[i]+k) == 0?0:prefix[map.get(nums[i]+k)-1]),res);
            }
            
            if(!map.containsKey(nums[i]))map.put(nums[i],i);
            else {
                if(prefix[n]-prefix[i-1]>prefix[n]-(map.get(nums[i])==0?0:prefix[map.get(nums[i])-1]))map.put(nums[i],i);
            }
        }
        
        return res==-(long)1e18?0:res;
        
    }
}