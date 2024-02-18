class Solution {
    // Function to check if there exist two numbers in the given array `nums`
    // such that their bitwise OR operation results in a number with trailing zeros.
    public boolean hasTrailingZeros(int[] nums) {
        int n = nums.length; // Number of elements in the array `nums`
        
        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Check if any pair of elements' bitwise OR results in a number with trailing zeros (even number)
            for (int j = 0; j < n; j++) {
                if (i != j && (nums[i] | nums[j]) % 2 == 0) {
                    return true; // Return true if such a pair is found
                }
            }
        }
        
        return false; // Return false if no such pair is found
    }
}

