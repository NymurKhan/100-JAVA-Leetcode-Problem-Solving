class Solution {
    int rec(List<List<Integer>>adjList,Integer dp[][],int vis[] ,int[] coins, int k,int curNode,int reduced)
    {
        //Very very important line:  if(reduced>13) return 0;
        /*
            Why this works because maximum value of cost = 10,000. 
            So if we divide 10,000/2^14 we will get 0 as integral value. 
            Quite innovative!! 
            Even I was unable to come up with this solution in the contest!
         */

        if(reduced>13) return 0;

        vis[curNode]= 1;
        if(dp[curNode][reduced]!=null)  return dp[curNode][reduced];

        int cost = coins[curNode]/(1<<reduced);
        int way1 = cost-k,  way2 = cost/2;

        for(int i=0;i<adjList.get(curNode).size();i++)
        {
            int adjNode = adjList.get(curNode).get(i);
            if(vis[adjNode]==0)
            {
            way1 += rec(adjList,dp,vis,coins,k,adjNode,reduced);
            way2 += rec(adjList,dp,vis,coins,k,adjNode,reduced+1);
            }
        }

        vis[curNode]=0;
        return dp[curNode][reduced] = Math.max(way1,way2);
    }

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        List<List<Integer>> adjList = new ArrayList<>();
        
        for(int i=0;i<coins.length;i++) adjList.add(new ArrayList<>());

        for(int i=0;i<edges.length;i++)
        {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        int vis[] = new int[coins.length];

        Integer dp[][] = new Integer[coins.length][14];
        return rec(adjList,dp,vis,coins,k,0,0);
    }
}