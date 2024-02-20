class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int count = 1;
        int score = nums[0] + nums[1];
        for(int i = 2; i < n - 1; i++){
            if(nums[i] + nums[i + 1] == score){
                count++;
                i++;
            }
            else{
                break;
            }
        }
        return count;
    }
}