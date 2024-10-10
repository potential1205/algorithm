package lecture_ssafy.day20241010.s4014_활주로_건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,X,answer;
    static int[][] board;

    static boolean checkLine(int[] line){

        int curIndex = 0;
        boolean[] visit = new boolean[line.length];

        while(curIndex<N-1){
            if(Math.abs(line[curIndex] - line[curIndex+1])>=2) return false;

            if(line[curIndex] - line[curIndex+1]==1){ // 내리막
                for(int j=0; j<X; j++){
                    int jdx = curIndex + 1 + j;
                    if( jdx>=N || line[jdx]!=line[curIndex+1] || visit[jdx]) return false;
                }

                for(int j=0; j<X; j++){
                    int jdx = curIndex + 1 + j;
                    visit[jdx] = true;
                }

                curIndex = curIndex + X;

            } else if(line[curIndex] - line[curIndex+1]==-1){ // 오르막
                for(int j=0; j<X; j++){
                    int jdx = curIndex - j;
                    if(jdx<0 || line[jdx]!=line[curIndex] || visit[jdx]) return false;
                }

                for(int j=0; j<X; j++){
                    int jdx = curIndex - j;
                    visit[jdx] = true;
                }

                curIndex = curIndex + 1;

            } else curIndex++; // 평지
        }

        return true;
    }

    static void solution(int t){
        answer = 0;
        int[] line = new int[N];
        for(int i=0; i<N; i++){
            // 행
            for(int j=0; j<N; j++) line[j] = board[i][j];
            if(checkLine(line)) answer++;

            // 열
            for(int j=0; j<N; j++) line[j] = board[j][i];
            if(checkLine(line)) answer++;
        }

        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution(t);
        }
    }
}