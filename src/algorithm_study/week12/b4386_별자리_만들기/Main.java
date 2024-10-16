package algorithm_study.week12.b4386_별자리_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Star[] starArr;
    static ArrayList<Edge>[] graph;

    static class Edge implements Comparable<Edge>{
        int num;
        double dist;

        Edge(int num, double dist){
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.dist - o.dist);
        }
    }

    static class Star{
        double y, x;

        Star(double y, double x){
            this.y = y;
            this.x = x;
        }
    }

    static void kruskal(){
        
    }

    static void prim(){
        boolean[] visit = new boolean[N];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(0, 0));

        double total = 0;

        while(!queue.isEmpty()){
            Edge edge = queue.poll();

            if(visit[edge.num]) continue;
            visit[edge.num] = true;

            total += edge.dist;

            for(Edge next : graph[edge.num]){
                if(visit[next.num]) continue;
                queue.offer(next);
            }
        }

        System.out.println(total);
    }

    static void solution(){
        prim();
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        starArr = new Star[N];
        graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            starArr[i] = new Star(x, y);
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                Star s1 = starArr[i];
                Star s2 = starArr[j];
                double dist = Math.sqrt(Math.pow((s1.x-s2.x), 2) + Math.pow((s1.y-s2.y), 2));
                graph[i].add(new Edge(j,dist));
                graph[j].add(new Edge(i,dist));
            }
        }

        solution();
    }
}
