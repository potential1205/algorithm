package daily.y2025.m07.d07.p49189_가장_먼_노드;

import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            graph.get(edge[i][0]).add(new Node(edge[i][1], 1));
            graph.get(edge[i][1]).add(new Node(edge[i][0], 1));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;

            for (Node next : graph.get(node.num)) {
                if (!visit[next.num] && dist[next.num] > dist[node.num] + 1) {
                    dist[next.num] = dist[node.num] + 1;
                    pq.offer(new Node(next.num, dist[next.num] + 1));
                }
            }
        }

        int maxVal = 0;

        for (int i = 1; i <= n; i++) {
            if (maxVal < dist[i]) {
                maxVal = dist[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (maxVal == dist[i]) {
                answer++;
            }
        }

        return answer;
    }
}