class Solution {
    public long maxSpending(int[][] values) {
        int n = values.length;
        int m = values[0].length;

        int arr[] = new int[n*m];
        int idx = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                arr[idx++] = values[i][j];
            }
        }

        Arrays.sort(arr);

        long ans = 0;
        long d = 1;

        for(int i=0; i<arr.length; i++){
            ans += arr[i]*d;
            d++;
        }

        return ans;
    }
}