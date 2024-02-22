class Solution {
    public int findChampion(int[][] grid) {
        int teams = grid.length;
        for(int i=0; i<teams; i++){
            int count = 0;
            for(int j=0; j<teams;j++){
                if(i==j)
                    continue;
                if(grid[i][j] == 0)
                    break;
                count++;
            }
            if(count == teams - 1)
                return i;
        }
        return -1;
    }
}