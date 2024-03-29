public class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] temp= new int[26][26];
        for (int i=0;i<26;i++){
            Arrays.fill(temp[i],Integer.MAX_VALUE);
            temp[i][i]=0;
        }
        for (int i = 0;i<original.length;i++){
            int start=original[i]-'a';
            int destination=changed[i]-'a';
            int amount=cost[i];

            temp [start][destination]=Math.min(temp [start][destination],amount);
        }

        for(int k=0;k<26;k++){
            for (int i=0;i<26;i++){
                for (int j=0;j<26;j++){
                    if(temp[i][k]!=Integer.MAX_VALUE && temp[k][j]!=Integer.MAX_VALUE) {
                        temp[i][j] = Math.min(temp[i][j],temp[i][k]+temp[k][j]);
                    }
                }
            }
        }

        long output=0;
        for (int i = 0; i<source.length(); i++) {
            int  start= source.charAt(i)-'a';
            int destination=target.charAt(i)-'a';
            if(start==destination) {
                continue;
            }
            if (temp [start][destination]==Integer.MAX_VALUE){
                return -1;
            } else {
                output+=temp [start][destination];
            }
        }

        return output;
    }
}