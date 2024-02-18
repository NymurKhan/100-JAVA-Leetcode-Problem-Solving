class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length * grid.length;
        int sum = n * (n + 1) / 2;
        int[] a = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!set.add(grid[i][j])) a[0] = grid[i][j];
                else sum -= grid[i][j];
            }
        }
        a[1] = sum;
        return a;
    }
}