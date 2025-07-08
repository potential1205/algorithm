package streak.d20250708.b1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));
        }

        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;
        boolean[] visit = new boolean[v + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visit[cur.num]) continue;
            visit[cur.num] = true;

            for (Node next : graph.get(cur.num)) {
                if (!visit[next.num] && dist[next.num] > dist[cur.num] + next.cost) {
                    dist[next.num] = dist[cur.num] + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
