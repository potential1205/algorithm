package algorithm_study.week6.s2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,answer,maxCnt;
    static int cost;
    static int[][] board;

    static void solution(){
        maxCnt = 0;

        for(int k=N+2; k>=1; k--){
            cost = (k*k) + (k-1)*(k-1);

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){

                    int cnt = 0;
                    int profit = 0;

                    for(int l=-(k-1); l<k; l++){
                        for(int m=-(k-1); m<k; m++){
                            if(Math.abs(l) + Math.abs(m) <=(k-1)){
                                int ky = i+l;
                                int kx = j+m;

                                if(ky<0 || kx<0 || ky>=N || kx>=N) continue;

                                if(board[ky][kx]==1){
                                    profit+=M;
                                    cnt++;
                                }

                            }
                        }
                    }

                    if(profit>=cost && maxCnt<cnt){
                        maxCnt = cnt;
                        return;
                    }

                }

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            System.out.println("#" + t + " " + maxCnt);
        }
    }
}
