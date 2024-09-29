package algorithm_study.week5.s4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N, answer;
    static int[][] board;
    static boolean[] visit;

    static void solution(int cnt, int start){
        if(cnt==N/2){
            int a = 0;
            int b = 0;

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visit[i] && visit[j]){
                        a += board[i][j];
                    }

                    if(!visit[i] && !visit[j]){
                        b += board[i][j];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(a-b));
            return;
        }

        for(int i=start; i<N; i++){
            visit[i] = true;
            solution(cnt+1,i+1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            visit = new boolean[N];
            solution(0,0);
            System.out.println("#" + t + " " + answer);
        }
    }

}
