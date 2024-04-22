import java.util.*;

public class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int cost = edges[i][2];
            graph.get(from).add(new Edge(to, cost, i));
            graph.get(to).add(new Edge(from, cost, i));
        }

        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.total));
        pq.offer(new Path(0, 0, new HashSet<>(), -1));
        int minPathCost = Integer.MAX_VALUE;
        Set<Integer> shortestPaths = new HashSet<>();
        Map<Integer, Integer> vis = new HashMap<>();
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            if (cur.node == n - 1) {
                if (minPathCost >= cur.total) {
                    minPathCost = cur.total;
                    shortestPaths.addAll(cur.path);
                    continue;
                }
            }
            if (cur.total > minPathCost) {
                continue;
            }
            vis.put(cur.node, cur.total);
            List<Edge> nei = graph.get(cur.node);
            for (Edge e : nei) {
                int next = e.to;
                int nextTot = e.cost + cur.total;
                if (vis.getOrDefault(next, Integer.MAX_VALUE) < nextTot) {
                    continue;
                }
                pq.offer(new Path(next, nextTot, cur.path, e.index));
            }

        }

        boolean[] res = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            if (shortestPaths.contains(i)) {
                res[i] = true;
            }
        }

        return res;
    }

}

class Edge {
    int to;
    int cost;
    int index;

    Edge(int to, int cost, int index) {
        this.to = to;
        this.cost = cost;
        this.index = index;
    }


}

class Path {
    int node;
    int total;
    Set<Integer> path;

    Path(int node, int total, Set<Integer> path, int index) {
        this.node = node;
        this.total = total;
        this.path = new HashSet<>(path);
        this.path.add(index);
    }

}
