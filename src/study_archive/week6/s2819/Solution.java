package study_archive.week6.s2819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    static int T,answer;
    static int[][] board;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    static HashSet<String> strSet;

    static void search(int cnt, String str, int y, int x){
        if(cnt==6){
            strSet.add(str);
            return;
        }

        for(int i=0; i<4; i++){
            int ky = y+dy[i];
            int kx = x+dx[i];
            if(ky<0 || kx<0 || ky>=4 || kx>=4) continue;
            search(cnt+1,str+board[ky][kx],ky,kx);
        }
    }

    static void solution(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                search(0,""+board[i][j],i,j);
            }
        }

        answer = strSet.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){

            board = new int[4][4];
            strSet = new HashSet<>();

            for(int i=0; i<4; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<4; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            solution();
            System.out.println("#" + t + " " + answer);
        }
    }
}
