package streak.d20250319.b10282_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N, D, C;
    static List<Node>[] graph;
    static boolean[] visit;
    static int[] dist;
    static int count;
    static int cost;

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

    static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;
            count++;

            for (Node next : graph[node.num]) {
                if (!visit[next.num] && dist[next.num] > dist[node.num] + next.cost) {
                    dist[next.num] = dist[node.num] + next.cost;
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (visit[i]) {
                cost = Math.max(cost, dist[i]);
            }
        }

        System.out.println(count + " " + cost);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            visit = new boolean[N+1];
            dist = new int[N+1];
            cost = 0;
            count = 0;

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int target = Integer.parseInt(st.nextToken());
                int source = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[source].add(new Node(target, cost));
            }

            dijkstra(C);
        }
    }
}
