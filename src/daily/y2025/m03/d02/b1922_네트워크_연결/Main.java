package daily.y2025.m03.d02.b1922_네트워크_연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, answer;
    static boolean[] visit;
    static List<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

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

    static void prim() {
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visit[node.num]) continue;
            visit[node.num] = true;
            answer += node.cost;

            for (Node next : graph[node.num]) {
                pq.offer(new Node(next.num, next.cost));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        visit = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int source = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[source].add(new Node(target, cost));
            graph[target].add(new Node(source, cost));
        }

        prim();

        System.out.println(answer);
    }
}
