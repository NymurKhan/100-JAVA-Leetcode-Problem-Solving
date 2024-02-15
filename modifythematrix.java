import java.util.Arrays;

class Solution {
    public int[][] modifiedMatrix(int[][] v) {
        int n = v.length, m = v[0].length;
        for (int i = 0; i < m; i++) {
            int k = 0;
            for (int j = 0; j < n; j++) {
                k = Math.max(k, v[j][i]);
            }
            for (int j = 0; j < n; j++) {
                if (v[j][i] == -1)
                    v[j][i] = k;
            }
        }
        return v;
    }
}
