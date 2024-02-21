
public class Solution {
    public long minimumSteps(String s) {
        long ans = 0;                     // Variable to store the result
        List<Integer> black = new ArrayList<>(); // List to store indices of '1's in the string

        int n = s.length();               // Length of the input string 's'

        // Iterate through the string 's' to find indices of '1's
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                black.add(i);            // Store index if the character is '1'
            }
        }

        Collections.reverse(black);      // Reverse the 'black' list

        // Calculate the minimum steps required
        for (int i = 0; i < black.size(); i++) {
            // For each '1', calculate steps needed to move it to the end
            ans += (n - i - 1 - black.get(i));
        }

        return ans; // Return the total minimum steps
    }
}
