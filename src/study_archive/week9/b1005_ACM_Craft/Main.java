package study_archive.week9.b1005_ACM_Craft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T,N,K,A;
    static int[] taskTimes, indegree;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] maxTimes;

    static void solution(){
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            maxTimes[i] = taskTimes[i];

            if(indegree[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(int i : graph.get(node)) {
                maxTimes[i] = Math.max(maxTimes[i], maxTimes[node] + taskTimes[i]);
                indegree[i]--;

                if(indegree[i] == 0)
                    queue.offer(i);
            }
        }
        System.out.println(maxTimes[A]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i=0; i<=N; i++){
                graph.add(new ArrayList<>());
            }

            taskTimes = new int[N+1];
            maxTimes = new int[N+1];
            indegree = new int[N+1];

            st = new StringTokenizer(bf.readLine());

            for(int i=1; i<=N; i++){
                taskTimes[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                indegree[to]++;
            }

            st = new StringTokenizer(bf.readLine());
            A = Integer.parseInt(st.nextToken());

            solution();
        }
    }
}
