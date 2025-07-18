package streak.d20250718.b1197_최소_스패닝_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    static int[] size;

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

    static int find(int cur) {
        if (p[cur] != cur) {
            return p[cur] = find(p[cur]);
        }

        return p[cur];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        if (size[rootA] >= size[rootB]) {
            size[rootA] += size[rootB];
            p[rootB] = rootA;
        } else {
            size[rootB] += size[rootA];
            p[rootA] = rootB;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(from, to, cost));
        }

        p = new int[v + 1];
        size = new int[v + 1];
        Arrays.fill(size, 1);

        for (int i = 0; i <= v; i++) {
            p[i] = i;
        }

        int totalCost = 0;
        int idx = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                idx++;

                if (idx == v - 1) {
                    break;
                }
            }
        }

        System.out.println(totalCost);
    }
}
