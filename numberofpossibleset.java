class Solution {
    private int res = 0;
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int [][] graph = new int [n][n];
        
        for(int [] road:roads){
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph[u][v]=graph[u][v]==0?w:Math.min(graph[u][v],w);
            graph[v][u]=graph[v][u]==0?w:Math.min(graph[v][u],w);
        }
        res=0;
        solve(n,0,new ArrayList<>(),graph,maxDistance);
        return res;
    }
    private void solve(int n,int level,List<Integer>closedBranches,int [][]graph,int maxDistance){
        //System.out.println(closedBranches);
       
        int cbsize = closedBranches.size();
        int [] cb = new int [cbsize];
        for(int b=0;b<cbsize;b++){
            cb[b]=closedBranches.get(b);
        }
        if(helper(graph,cb,maxDistance)){
            res++;
        }
        int i = cbsize==0?0:closedBranches.get(cbsize-1)+1;
        for(;i<n;i++){
            closedBranches.add(i);
            solve(n,level+1,closedBranches,graph,maxDistance);
            closedBranches.remove(cbsize);
        }
        
    }
    private boolean helper(int [][] graph,int [] closedBranches,int maxDistance){
        Set<Integer> hset = new HashSet<>();
        for(int b:closedBranches){
            hset.add(b);
        }
        int n = graph.length;
        int [][] graphClone = new int [n][n];
        for(int i=0;i<n;i++){
            if(hset.contains(i)) continue;
            for(int j=0;j<n;j++){
                if(hset.contains(j)) continue;
                graphClone[i][j]=graph[i][j];
            }
        }
        for(int i=0;i<n;i++){
            if(!hset.contains(i)){
                if(!isValid(graphClone,hset,i,maxDistance)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(int [][] graph,Set<Integer> closedBranches,int source,int maxDistance){
        //System.out.println(source);
        int n = graph.length;
        int [] dist = new int [n];
        boolean [] finalized = new boolean[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        for(int i=0;i<n;i++){
            int u=-1;
            for(int v=0;v<n;v++){
                if(finalized[v]) continue;
                if(dist[v]!=Integer.MAX_VALUE && (u==-1 || dist[u]>dist[v])){
                    u=v;
                }
            }
            if(u==-1) break;
            finalized[u]=true;
            for(int v=0;v<n;v++){
                if(!finalized[v] && graph[u][v]!=0 && (dist[v]>dist[u]+graph[u][v])){
                    dist[v]=dist[u]+graph[u][v];
                }
            }
        }
        boolean res = true;
        for(int i=0;i<n;i++){
            //System.out.print(dist[i]+" ");
            if(closedBranches.contains(i)) continue;
            if(dist[i]>maxDistance){
                res = false;
                break;
            }
        }
        
        return res;

    }
}