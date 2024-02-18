class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int l = 0, h = nums.length-1 , n= nums.length;
        while(l < (nums.length -1) && nums[l] < nums[l+1]) l++;
        while(h > 0 && nums[h-1] < nums[h]) h--;
        long res = 0;
        if(l!=n-1) res = res + (n-h)+1; // Game Changer
        for(int i=0;i<=l;i++){
            int target = nums[i];
            int lo = h ,hi = nums.length-1,ans = nums.length;
            while(lo<=hi){
                int mid = (lo+hi)/2;
                if(nums[mid] > target){
                    ans = mid;
                   hi = mid -1;
                }else{
                    lo = mid+1 ;
                }
            }
            System.out.println(ans);
            res = res + (nums.length-ans)+1;


        }
        return res;

    }
}