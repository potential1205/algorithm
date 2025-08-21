package streak.d20250821.p118669_등산_코스;

import java.util.*;

class Solution {
    static List<List<Node>> graph;
    static boolean[] isGate;
    static boolean[] isSummit;

    static class Node implements Comparable<Node> {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];

        for (int gate : gates) {
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost != dist[cur.num]) continue;
            if (isSummit[cur.num]) continue;

            for (Node next : graph.get(cur.num)) {
                if (isGate[next.num]) continue;

                int nextCost = Math.max(cur.cost, next.cost);

                if (dist[next.num] > nextCost) {
                    dist[next.num] = nextCost;
                    pq.offer(new Node(next.num, nextCost));
                }
            }
        }

        Arrays.sort(summits);
        int bestSummit = -1;
        int bestCost = INF;
        for (int summit : summits) {
            if (dist[summit] < bestCost) {
                bestCost = dist[summit];
                bestSummit = summit;
            }
        }

        return new int[]{bestSummit, bestCost};
    }
}

