package daily.y2025.m10.d23.p72413_합승_택시_요금;

import java.util.*;

class Solution {

    static int[][] minCostArr;
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

    static void dijkstra(int num, List<List<Node>> graph, int n, int[][] minCostArr) {

        // num번 노드부터 다른 노드까지 이동하기 위한 최소비용
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[num] = 0;

        // 비용이 작은 순서부터 우선 탐색
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(num, 0));

        // 노드 방문 여부
        boolean[] visit = new boolean[n + 1];

        // 노드 순회
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visit[cur.num]) continue;
            visit[cur.num] = true;

            for (Node next : graph.get(cur.num)) {
                if (dist[next.num] > dist[cur.num] + next.cost) {
                    dist[next.num] = dist[cur.num] + next.cost;
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            minCostArr[num][i] = dist[i];
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {

        // 1. 그래프 생성하기 (양방향)
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            graph.get(fare[0]).add(new Node(fare[1], fare[2]));
            graph.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        // 2. 각 지점간 최단경로(최소비용)를 구하기
        minCostArr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dijkstra(i, graph, n, minCostArr);
        }

        // 3. A와 B가 어디서 헤어져야 최소 비용이 될 수 있는지 탐색
        int answer = minCostArr[s][a] + minCostArr[s][b]; // 시작하자마자 각자 따로 이동할 때 비용으로 초기화
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, minCostArr[s][i] + minCostArr[i][a] + minCostArr[i][b]);
        }

        return answer;
    }
}