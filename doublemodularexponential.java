class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; ++i) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            a %= 10;
            int t = 1;
            for (int j = 0; j < b; ++j) {
                t = (t * a) % 10;
            }
            int x = 1;
            for (int j = 0; j < c; ++j) {
                x = (x * t) % m;
            }
            if (x == target) ans.add(i);
        }
        return ans;
    }
}