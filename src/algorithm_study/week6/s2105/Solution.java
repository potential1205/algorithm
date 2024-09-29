package algorithm_study.week6.s2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,answer;
    static int[][] board;

    static int[] dy = {1, 1, -1, -1};
    static int[] dx = {1,-1, -1, 1};

    static void search(int sy, int sx, int y,int x, Set<Integer> nums, int d) {
        if(d == 3 && sy==y && sx==x) {
            answer = Math.max(answer, nums.size());
            return;
        }

        if(d<4) {
            int ky = y + dy[d];
            int kx = x + dx[d];

            if(!(ky<0 || kx<0 || ky>=N || kx>=N) && !nums.contains(board[ky][kx])) {
                nums.add(board[ky][kx]);
                search(sy,sx,ky,kx,nums,d);
                nums.remove(board[ky][kx]);
            }
        }

        if(d+1<4) {
            int ky = y + dy[d+1];
            int kx = x + dx[d+1];

            if(!(ky<0 || kx<0 || ky>=N || kx>=N) && !nums.contains(board[ky][kx])) {
                nums.add(board[ky][kx]);
                search(sy,sx,ky,kx,nums,d+1);
                nums.remove(board[ky][kx]);
            }
        }

    }

    static void solution() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Set<Integer> nums = new HashSet<>();
                search(i,j,i,j,nums,0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T  = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(bf.readLine());
            N  = Integer.parseInt(st.nextToken());

            board = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            solution();
            if(answer==0) answer=-1;

            System.out.println("#" + t + " " + answer);
        }

    }

}