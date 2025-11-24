package study_archive.week9.b1504_특정한_최단_경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, E, A, B, answer;
    static int INF = 200000000;
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visit;
    static int[] dist;

    static class Node implements Comparable<Node>{
        int num, weight;

        Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int dijkstra(int start, int end) {
        visit = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (visit[curNode.num]) continue;
            visit[curNode.num] = true;

            for (Node nextNode : graph.get(curNode.num)) {
                if (!visit[nextNode.num] && dist[nextNode.num] > dist[curNode.num] + nextNode.weight) {
                    dist[nextNode.num] = dist[curNode.num] + nextNode.weight;
                    pq.offer(new Node(nextNode.num, dist[nextNode.num]));
                }
            }
        }

        return dist[end];
    }

    static void solution () {
        int answer1 = dijkstra(1,A);
        answer1 += dijkstra(A,B);
        answer1 += dijkstra(B,N);

        int answer2 = dijkstra(1,B);
        answer2 += dijkstra(B,A);
        answer2 += dijkstra(A,N);

        if(answer1>=INF && answer2>=INF) answer = -1;
        else answer = Math.min(answer1,answer2);
        System.out.println(answer);
    }

    public static void main (String[]args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }

        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solution();
    }
}
