package daily.y2025.m07.d08.b1197_최소_스패닝_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

        List<List<Node>> graph = new ArrayList<>();
        boolean[] visit = new boolean[v + 1];

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        int totalCost = 0;
        int cnt = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visit[cur.num]) continue;

            visit[cur.num] = true;
            totalCost += cur.cost;
            cnt++;

            for (Node next : graph.get(cur.num)) {
                if (visit[next.num]) continue;

                pq.offer(next);
            }
        }

        System.out.println(totalCost);
    }
}
