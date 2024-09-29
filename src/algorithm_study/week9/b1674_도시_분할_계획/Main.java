package algorithm_study.week9.b1674_도시_분할_계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N,E;
    static ArrayList<ArrayList<Node>> graph;

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

    static void prim(int start){
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int total = 0;
        int maxVal = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(visit[node.num]) continue;
            visit[node.num] = true;
            total += node.weight;
            maxVal = Math.max(maxVal, node.weight);

            for(Node next : graph.get(node.num)){
                if(visit[next.num]) continue;
                pq.offer(next);
            }
        }

        System.out.println(total-maxVal);
    }

    static void solution(){
        prim(1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }

        solution();
    }
}
