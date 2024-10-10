package lecture_ssafy.day20241010.s1263_사람_네트워크2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,answer;
    static int[][] graph;

    static void Floyd(){
        for(int k=0; k<N; k++){ // 경유지
            for(int i=0; i<N; i++){ // 출발지
                if(i==k) continue;

                for(int j=0; j<N; j++){ // 목적지
                    if(i==j || k==j) continue;

                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        for(int i=0; i<N; i++){
            int cum = 0;
            for(int j=0; j<N; j++){
                cum += graph[i][j];
            }
            answer = Math.min(answer, cum);
        }
    }

    static void solution(int tc){
        answer=Integer.MAX_VALUE;
        Floyd();
        System.out.println("#" + tc + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            graph = new int[N][N];

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    graph[i][j] = Integer.parseInt(st.nextToken());

                    if(i!=j && graph[i][j]==0) graph[i][j] = 1000*1000*2;
                }
            }

            solution(tc);
        }
    }
}
