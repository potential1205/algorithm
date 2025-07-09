package streak.d20250709.b1922_네트워크_연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] p;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int find(int a) {
        if (p[a] != a) {
            return p[a] = find(p[a]);
        }

        return p[a];
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;
        p[pb] = pa;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        p = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }

        int totalCost = 0;
        int cnt = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(from, to, cost));
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                cnt++;

                if (cnt == n) {
                    break;
                }
            }
        }

        System.out.println(totalCost);
    }
}
