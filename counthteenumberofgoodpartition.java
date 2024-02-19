import java.util.*;

class Solution {
    final int N = (int)1e9 + 7;

    // Function to perform binary exponentiation
    long binary(long a, long b) {
        long res = 1;
        a = a;
        while (b > 0) {
            if ((b & 1) == 1)
                res = ((res % N) * (a % N)) % N;
            a = ((a % N) * (a % N)) % N;
            res = res % N;
            b >>= 1;
        }
        return res;
    }

    // Function to calculate the number of good partitions
    int numberOfGoodPartitions(int[] nums) {
        Set<Map.Entry<Integer, Integer>> s = new HashSet<>(); // Set to store frequency and element
        Map<Integer, Integer> m = new HashMap<>(); // Map to store frequency of elements

        // Count frequency of each element in the nums array
        for (int x : nums) {
            m.put(x, m.getOrDefault(x, 0) + 1);
        }

        int count = 0;
        int i = 0;
        int n = nums.length;

        // Iterate through the array
        while (i < n) {
            s.remove(new AbstractMap.SimpleEntry<>(-1 * m.get(nums[i]), nums[i])); // Remove frequency and element pair from set
            m.put(nums[i], m.get(nums[i]) - 1); // Decrease frequency of the current element

            // If the element still has frequency left, insert it back into the set
            if (m.get(nums[i]) > 0)
                s.add(new AbstractMap.SimpleEntry<>(-1 * m.get(nums[i]), nums[i]));

            i++;

            // If the set is empty, increment the count
            if (s.size() == 0) {
                count++;
            }
        }

        // Calculate the result using binary exponentiation
        return (int)binary(2, count - 1);
        // Equivalent way to calculate result using left shift operator
        // return (1 << (count - 1));
    }
}

