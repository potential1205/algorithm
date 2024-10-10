package algorithm_study.week12.b5972_택배_배송;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[] dist;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node>{
        int num, cost;

        Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void dijkstra(int start){
        boolean[] visit = new boolean[N+1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start,0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            if(visit[node.num]) continue;
            visit[node.num] = true;

            for(Node next : graph[node.num]){
                if(dist[next.num] > dist[node.num] + next.cost){
                    dist[next.num] = dist[node.num] + next.cost;
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        System.out.println(dist[N]);
    }

    static void solution(){
        dijkstra(1);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        solution();
    }
}
