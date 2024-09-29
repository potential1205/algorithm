package algorithm_study.week10.b2637_장난감_조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static ArrayList<Node>[] graph;
    static int[] indegreeFrom, indegreeTo;

    static class Node{
        int num, cnt;

        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void solution(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(N,1));

        int[] counts = new int[N+1];
        counts[N] = 1;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i=0; i<graph[cur.num].size(); i++){
                Node next = graph[cur.num].get(i);
                indegreeTo[next.num]--;
                counts[next.num] += counts[cur.num] * next.cnt;
                if(indegreeTo[next.num]==0) queue.offer(new Node(next.num, counts[next.num]));
            }
        }

        for(int i=1; i<=N; i++){
            if(indegreeFrom[i]==0) System.out.println(i + " " + counts[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        indegreeFrom = new int[N+1];
        indegreeTo = new int[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,cnt));
            indegreeFrom[from]++; // 이 배열의 값이 0인 경우 기본 부품
            indegreeTo[to]++;
        }

        solution();
    }
}