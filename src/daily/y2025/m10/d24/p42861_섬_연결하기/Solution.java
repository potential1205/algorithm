package daily.y2025.m10.d24.p42861_섬_연결하기;

import java.util.*;

class Solution {

    static List<List<Node>> graph = new ArrayList<>();

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

    public int solution(int n, int[][] costs) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] cost : costs) {
            graph.get(cost[0]).add(new Node(cost[1], cost[2]));
            graph.get(cost[1]).add(new Node(cost[0], cost[2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        int cnt = 0;
        boolean[] visit = new boolean[n];

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;
            cnt++;
            answer += node.cost;

            if (cnt == n) {
                break;
            }

            for (Node next : graph.get(node.num)) {
                pq.offer(new Node(next.num, next.cost));
            }
        }

        return answer;
    }
}