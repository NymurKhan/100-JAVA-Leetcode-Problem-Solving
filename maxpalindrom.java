import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPalindromesAfterOperations(String[] w) {
        int[] t = new int[w.length];
        Map<Character, Integer> m = new HashMap<>();
        int n = w.length;

        for (int i = 0; i < n; i++) {
            for (char j : w[i].toCharArray())
                m.merge(j, 1, Integer::sum);
            t[i] = w[i].length();
        }

        int pairs = 0, odd = 0;
        for (int i : m.values()) {
            int sz = i / 2;
            pairs += sz;
            if (i % 2 != 0) {
                odd++;
            }
        }

        Arrays.sort(t);
        int i = 0, ans = 0;
        while (i < n) {
            if (t[i] % 2 != 0) {
                if (odd < 0) {
                    pairs--;
                    odd++;
                    t[i]--;
                } else {
                    odd--;
                    t[i]--;
                }
                if (t[i] == 0) {
                    ans++;
                    i++;
                    continue;
                }
            }
            if (t[i] % 2 == 0) {
                pairs -= t[i] / 2;
                if (pairs >= 0)
                    ans++;
            }
            i++;
        }
        return ans;
    }
}
