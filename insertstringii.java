import java.util.*;

class Node {
    Map<Character, Node> children;
    char data;
    boolean isEnd;

    Node(char data) {
        this.data = data;
        this.isEnd = false;
        this.children = new HashMap<>();
    }

    void setEnd(String dest, int cost) {
        isEnd = true;
    }
}

public class Solution {

    long[][] dist = new long[1000][1000];
    Map<String, Integer> index = new HashMap<>();
    long INF = (long) 1e18;
    long[] dp = new long[1000];

    void insert(Node root, String source, String dest, int cost, int i) {
        if (i == source.length()) {
            root.setEnd(dest, cost);
            return;
        }
        Node node = root.children.getOrDefault(source.charAt(i), new Node(source.charAt(i)));
        root.children.put(source.charAt(i), node);
        insert(node, source, dest, cost, i + 1);
    }

    long func(Node root, String source, String target, int i) {
        if (i == target.length())
            return 0;
        if (dp[i] == -1) {
            long opt1 = INF, opt2 = INF;
            if (source.charAt(i) == target.charAt(i))
                opt1 = func(root, source, target, i + 1);
            Node node = root;
            StringBuilder sourceSubstr = new StringBuilder(), targetSubstr = new StringBuilder();
            for (int j = i; j < source.length(); j++) {
                targetSubstr.append(target.charAt(j));
                sourceSubstr.append(source.charAt(j));
                Node child = node.children.get(source.charAt(j));
                if (child == null)
                    break;
                node = child;
                if (node.isEnd) {
                    int jt = index.getOrDefault(sourceSubstr.toString(), -1);
                    int kt = index.getOrDefault(targetSubstr.toString(), -1);
                    if (jt != -1 && kt != -1 && dist[jt][kt] != INF)
                        opt2 = Math.min(opt2, func(root, source, target, j + 1) + dist[jt][kt]);
                }
            }
            dp[i] = Math.min(opt1, opt2);
        }
        return dp[i];
    }

    void floydWarshall(List<String> original, List<String> changed, List<Integer> cost) {
        Set<String> S = new HashSet<>();
        for (int i = 0; i < original.size(); i++) {
            S.add(original.get(i));
            S.add(changed.get(i));
        }
        int size = 0;
        for (String it : S) {
            index.put(it, size);
            size++;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = INF;
                if (i == j)
                    dist[i][j] = 0;
            }
        }
        for (int i = 0; i < original.size(); i++)
            dist[index.get(original.get(i))][index.get(changed.get(i))] = Math.min(dist[index.get(original.get(i))][index.get(changed.get(i))], cost.get(i));

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][j] > (dist[i][k] + dist[k][j]) && (dist[k][j] != INF && dist[i][k] != INF))
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    long minimumCost(String source, String target, List<String> original, List<String> changed, List<Integer> cost) {
        Node root = new Node('\0');
        floydWarshall(original, changed, cost);
        Arrays.fill(dp, -1);
        for (int i = 0; i < original.size(); i++)
            insert(root, original.get(i), changed.get(i), cost.get(i), 0);
        long ans = func(root, source, target, 0);
        if (ans == INF)
            ans = -1;
        return ans;
    }
}

