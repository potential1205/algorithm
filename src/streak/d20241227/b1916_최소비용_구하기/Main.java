package streak.d20241227.b1916_최소비용_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int source, target;
    static int[] dist;
    static boolean[] visited;
    static List<Node>[] graph;

    static class Node implements Comparable<Node>{
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static void solution() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[source] = 0;
        pq.offer(new Node(source, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.num]) continue;
            visited[node.num] = true;

            for (Node next : graph[node.num]) {
                if (dist[next.num] > dist[node.num] + next.cost) {
                    dist[next.num] = dist[node.num] + next.cost;

                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        System.out.println(dist[target]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
        }

        st = new StringTokenizer(bf.readLine());
        source = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        solution();
    }
}
