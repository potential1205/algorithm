package study_archive.week10.b14889_스타트와_링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,answer;
    static int[][] board;
    static boolean[] visit;

    static void calculate(){
        int total1 = 0;
        int total2 = 0;

       // System.out.println(Arrays.toString(visit));

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==j) continue;

                if(visit[i] && visit[i]==visit[j]){
                    total1 += board[i][j];
                } else if(!visit[i] && visit[i]==visit[j]){
                    total2 += board[i][j];
                }
            }
        }

        answer = Math.min(answer, Math.abs(total1-total2));
    }

    static void search(int depth, int cnt1, int cnt2){
       if(depth == N){
           calculate();
           return;
       }

       if(cnt1<N/2){
           visit[depth] = true;
           search(depth+1, cnt1+1, cnt2);
       }

       if(cnt2<N/2){
           visit[depth] = false;
           search(depth+1, cnt1, cnt2+1);
       }
    }

    static void solution(){
        visit = new boolean[N];
        answer = Integer.MAX_VALUE;

        search(0,0,0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
