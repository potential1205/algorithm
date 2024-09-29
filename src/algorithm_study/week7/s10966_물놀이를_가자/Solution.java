package algorithm_study.week7.s10966_물놀이를_가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int answer; // 땅으로 표현된 모든 칸에 대해서 물인 칸으로 이동하기 위한 최소 이동 횟수의 합
    static int T,N,M;
    static int[][] numBoard;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static char[][] board;
    static boolean[][] visit;
    static Queue<Point> queue;

    static class Point{
        int y,x,t;

        Point(int y, int x, int t){
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    static void bfs() {
        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int i=0; i<4; i++){
                int ky = p.y + dy[i];
                int kx = p.x + dx[i];

                if(ky<0 || kx<0 || ky>=N || kx>=M || visit[ky][kx] || board[ky][kx] == 'W') continue;

                queue.add(new Point(ky,kx,p.t+1));
                visit[ky][kx] = true;

                if(p.t+1 < numBoard[ky][kx]){
                    numBoard[ky][kx] = p.t+1;
                }
            }
        }
    }

    static void solution(int t){
        bfs();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(numBoard[i][j]!=Integer.MAX_VALUE)
                    answer+= numBoard[i][j];
            }
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
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            numBoard = new int[N][M];
            visit = new boolean[N][M];
            queue = new LinkedList<>();
            answer = 0;

            for(int i=0; i<N; i++){
                String line = bf.readLine();
                for(int j=0; j<M; j++){
                    board[i][j] = line.charAt(j);
                    numBoard[i][j] = Integer.MAX_VALUE;

                    if(board[i][j] == 'W'){
                        queue.add(new Point(i,j,0));
                        visit[i][j] = true;
                    }
                }
            }

            solution(t);
        }
    }
}
