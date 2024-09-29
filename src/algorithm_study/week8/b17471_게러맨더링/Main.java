package algorithm_study.week8.b17471_게러맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,answer;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] populations;
    static boolean[] visit, team;

    static boolean bfs(){
        int cnt = 0;
        visit = new boolean[N+1];

        // team false인 경우
        Queue<Integer> queue1 = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(!team[i]){
                queue1.offer(i);
                visit[i] = true;
                cnt++;
                break;
            }
        }

        while(!queue1.isEmpty()){
            int node = queue1.poll();

            for(int nextNode : graph.get(node)){
                if(visit[nextNode] || team[nextNode]) continue;

                queue1.offer(nextNode);
                visit[nextNode] = true;
                cnt++;
            }
        }

        // team true인 경우
        Queue<Integer> queue2 = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(team[i]){
                queue2.offer(i);
                visit[i] = true;
                cnt++;
                break;
            }
        }

        while(!queue2.isEmpty()){
            int node = queue2.poll();

            for(int nextNode : graph.get(node)){
                if(visit[nextNode] || !team[nextNode]) continue;

                queue2.offer(nextNode);
                visit[nextNode] = true;
                cnt++;
            }
        }

        if(cnt==N) return true;
        else return false;
    }

    static void subset(int depth){
        if(depth==N+1){
            if(bfs()){
                int cum1 = 0;
                int cum2 = 0;
                for(int i=1; i<=N; i++){
                    if(team[i]) cum1 += populations[i];
                    else cum2 += populations[i];
                }
                answer = Math.min(answer, Math.abs(cum1-cum2));
            }
            return;
        }

        team[depth] = false;
        subset(depth+1);

        team[depth] = true;
        subset(depth+1);
    }

    static void solution(){
        answer = Integer.MAX_VALUE;
        subset(1);

        answer = answer==Integer.MAX_VALUE ? -1 : answer;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        populations = new int[N+1];
        team = new boolean[N+1];
        visit = new boolean[N+1];

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i=1; i<=N; i++){
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(bf.readLine());
            int M = Integer.parseInt(st.nextToken());

            for(int j=0; j<M; j++){
                int id = Integer.parseInt(st.nextToken());
                graph.get(i).add(id);
            }
        }

        solution();
    }
}
