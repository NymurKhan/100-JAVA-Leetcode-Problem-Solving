public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[] nums1 = {1, -1, 1, -1, 1, -1};
        System.out.println("Boundary count for test case 1: " + solution.returnToBoundaryCount(nums1)); // Output: 3
        
        // Test case 2
        int[] nums2 = {1, -1, 1, -1, 1};
        System.out.println("Boundary count for test case 2: " + solution.returnToBoundaryCount(nums2)); // Output: 2
    }
}

class Solution {
    public int returnToBoundaryCount(int[] nums) {
        int count = 0, displacement = 0;
        for (int num : nums) {
            displacement += num;
            if (displacement == 0)
                count++;
        }
        return count;
    }
}
