import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int maximumLength(String s) {
        List<List<Integer>> len = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            len.add(new ArrayList<>());
        }

        int n = s.length();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            len.get(s.charAt(i) - 'a').add(j - i);
            i = j;
        }

        int mx = -1;
        for (int i = 0; i < 26; ++i) {
            len.get(i).sort(null);
            for (int v : len.get(i)) {
                for (int j = Math.max(1, v - 2); j <= v; ++j) {
                    int idx = lowerBound(len.get(i), j + 2);
                    if (idx != len.get(i).size()) {
                        mx = Math.max(mx, j);
                        continue;
                    }
                    idx = lowerBound(len.get(i), j + 1);
                    if (idx != len.get(i).size() && idx + 1 != len.get(i).size()) {
                        mx = Math.max(mx, j);
                        continue;
                    }
                    idx = lowerBound(len.get(i), j);
                    if (idx != len.get(i).size() && idx + 1 != len.get(i).size()) {
                        if (len.get(i).get(idx + 1) == j + 1) {
                            mx = Math.max(mx, j);
                            continue;
                        }
                        if (idx + 2 != len.get(i).size()) {
                            mx = Math.max(mx, j);
                            continue;
                        }
                    }
                }
            }
        }
        
        return mx;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

