class Solution {
    class Node {
        int n, step;
        Node(int n, int step) {
            this.n = n;
            this.step = step;
        }
    }
    public int minimumOperationsToMakeEqual(int x, int y) {
        if(y>=x) return y-x;
        Queue<Node> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        q.add(new Node(x, 0));
        int max = x+Math.max(11-x%11, 5-x%5);
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int n = cur.n;
            int step = cur.step;
            if(n == y) return step;
            if(set.contains(n)) continue;
            set.add(n);
            if(n%11 == 0) q.add(new Node(n/11, step+1));
            if(n%5 == 0) q.add(new Node(n/5, step+1));
            if(n-1>0) q.add(new Node(n-1, step+1));
            if(n+1<=max) q.add(new Node(n+1, step+1));
        }
        return -1;
    }   
}