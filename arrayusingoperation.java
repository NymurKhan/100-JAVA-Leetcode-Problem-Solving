class Solution {
    public int minimumArrayLength(int[] nums) {
        int mn = nums[0], cnt = 0;
        
        for(int i: nums) mn = Math.min(i, mn);
        for(int i: nums){
            if(i == mn) cnt++;
            else if(i % mn != 0){
                return 1;
            }
        }
        
        return (cnt + 1) / 2;
    }
}