package streak.d20250716.b1238_파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] answer;

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

    static int[] dijkstra(int start, int n, List<List<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        boolean[] visit = new boolean[n + 1];

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visit[cur.num]) continue;
            visit[cur.num] = true;

            for (Node next : graph.get(cur.num)) {
                if (dist[next.num] > dist[cur.num] + next.cost) {
                    dist[next.num] = dist[cur.num] + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        answer = new int[n + 1];
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));
        }

        int[] temp = dijkstra(x, n, graph);

        for (int i = 1; i <= n; i++) {
            answer[i] = temp[i];
        }

        for (int i = 1; i <= n; i++) {
            temp = dijkstra(i, n, graph);
            answer[i] += temp[x];
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (i != x) {
                result = Math.max(result, answer[i]);
            }
        }

        System.out.println(result);
    }
}
