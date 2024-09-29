package algorithm_study.week3.b14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,L;
    static int[][] board;
    static int answer;

    static void checkLine(int[] line){
        int idx = 0;
        boolean[] visit = new boolean[N];

        while(idx<N-1) {
            if(line[idx]==line[idx+1]){
                idx++;
            } else if(line[idx]-1==line[idx+1]){ // 내리막길
                for(int i=0; i<L; i++){
                    if(idx+1+i>=N || line[idx+1+i]!=line[idx+1] || visit[idx+1+i]){
                        return;
                    }
                }

                for(int i=0; i<L; i++){
                    visit[idx+1+i] = true;
                }
                idx = idx + L;

            } else if(line[idx]+1==line[idx+1]){ // 오르막길
                for(int i=0; i<L; i++){
                    if(idx-i<0 || line[idx-i]!=line[idx] || visit[idx-i]){
                        return;
                    }
                }
                for(int i=0; i<L; i++){
                    visit[idx-i] = true;
                }
                idx = idx+1;
            } else{
                return;
            }
        }
        answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            int[] line = new int[N];
            for(int j=0; j<N; j++){
                line[j] = board[i][j];
            }
            checkLine(line);
        }

        for(int i=0; i<N; i++){
            int[] line = new int[N];
            for(int j=0; j<N; j++){
                line[j] = board[j][i];
            }
            checkLine(line);
        }


        System.out.println(answer);
    }
}
