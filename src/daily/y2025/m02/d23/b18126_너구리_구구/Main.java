package daily.y2025.m02.d23.b18126_너구리_구구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static long[] dist;
    static long answer;
    static boolean[] visit;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int num;
        long cost;

        Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.cost - o.cost);
        }
    }

    static void solution() {
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;

            for (Node next : graph[node.num]) {
                if (dist[next.num] > dist[node.num] + next.cost) {
                    dist[next.num] = dist[node.num] + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, dist[i]);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(bf.readLine());
            int source = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[source].add(new Node(target, cost));
            graph[target].add(new Node(source, cost));
        }

        visit = new boolean[n+1];
        dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        solution();
    }
}
