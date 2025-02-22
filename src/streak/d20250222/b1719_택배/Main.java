package streak.d20250222.b1719_택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dist;
    static boolean[] visit;
    static int n,m;
    static int[][] graph;

    static class Node implements Comparable<Node> {
        int num, cost;

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
        dist[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        int[] before = new int[n+1];
        Arrays.fill(before, -1);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;

            for (int next = 1; next <= n; next++) {
                if (graph[node.num][next] != Integer.MAX_VALUE && dist[next] > dist[node.num] + graph[node.num][next]) {
                    dist[next] = dist[node.num] + graph[node.num][next];
                    queue.offer(new Node(next, dist[next]));

                    if (before[node.num] == -1) {
                        before[next] = next;
                    } else {
                        before[next] = before[node.num];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (before[i] == -1) {
                System.out.print("-" + " ");
            } else {
                System.out.print(before[i] + " ");
            }
        }
        System.out.println();
    }

    static void solution() {
        for (int i = 1; i <= n; i++) {
            visit = new boolean[n+1];
            dist = new int[n+1];

            for (int j = 1; j <= n; j++) {
                dist[j] = Integer.MAX_VALUE;
            }

            dijkstra(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int source = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[source][target] = cost;
            graph[target][source] = cost;
        }

        solution();
    }
}
