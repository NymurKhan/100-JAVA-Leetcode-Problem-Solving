class Solution {
    
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = queries.length;
        int m = heights.length;
        int[] ans = new int[n];
        int[] table = new int[m];
        Stack<Integer> stack = new Stack<>();
        stack.push(m-1);
        table[m-1] = -1;
        
        for (int index = m-2; index>=0; index--) {
            if (heights[index]<heights[stack.peek()]) {
                table[index] = stack.peek();
                stack.push(index);
            } else {
                while (!stack.empty() && heights[index] >= heights[stack.peek()]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    table[index] = -1;
                } else {
                    table[index] = stack.peek();
                }
                stack.push(index);
            }
        }
        
        for (int index=0; index<n; index++) {      
            int[] query = queries[index];
            int i = query[0], j = query[1];
            if (i>j) {
                i = query[1];
                j = query[0];
            }
            if (heights[j]>heights[i] || i==j) {
                ans[index] = j;
                continue;
            }
            int k = j+1;
            
            if (table[i]==-1 || table[j]==-1) {
                ans[index] = -1;
                continue;
            }
            
            if (heights[i] > heights[j]) {
                if (table[i] >= table[j]) {
                    ans[index] = table[i];
                } else {
                    k = table[j];
                    int h = heights[i];
            
                    for (; k<m; k++) {
                        if (heights[k]>h) {
                            ans[index] = k;
                            break;
                        }
                    }
                    
                    if (ans[index] == 0) {
                        ans[index] = -1;
                    }
                }
            } else {
                ans[index] = table[j];
            }
        }
        
        return ans;
    }
}