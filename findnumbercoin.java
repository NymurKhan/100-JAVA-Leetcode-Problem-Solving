class Solution {
    ArrayList<Long> postorder(ArrayList<ArrayList<Integer>> arr, int i, long[] ans, int[] cost, int[] vis){
        
        vis[i] = 1;
        ArrayList<Long> arli = new ArrayList<>(); 
        for(int x: arr.get(i)){
            if(vis[x]!=1){
            ArrayList<Long> sub = postorder(arr, x, ans, cost, vis);
            for(long m: sub){
                arli.add(m);
            }}
        }
        arli.add((long)cost[i]);
        if(arli.size()<3){
            ans[i] = 1;
            return arli;
        }
        else{
            
            Collections.sort(arli);
            ans[i] = arli.get(arli.size()-1)*arli.get(arli.size()-3)*arli.get(arli.size()-2);
            ans[i] = Math.max(arli.get(arli.size()-1)*arli.get(0)*arli.get(1),ans[i]);
            if(ans[i]<0){
                ans[i] = 0;
            }
        }
        ArrayList<Long> retArray = new ArrayList<>();
        if(arli.size()>5){
            retArray.add(arli.get(arli.size()-1));
            retArray.add(arli.get(arli.size()-2));
            retArray.add(arli.get(arli.size()-3));
            retArray.add(arli.get(0));
            retArray.add(arli.get(1));
            return retArray;
        }
        
        return arli; 
        
    }
    
    public long[] placedCoins(int[][] edges, int[] cost) {
        
        int n = cost.length;
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for(int i=0;i<n;i++){
            arr.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            arr.get(edges[i][0]).add(edges[i][1]);
            arr.get(edges[i][1]).add(edges[i][0]);
        }
        int[] vis = new int[n];
        long[] ans = new long[n];
        postorder(arr, 0, ans, cost, vis);
        return ans;
    }
}