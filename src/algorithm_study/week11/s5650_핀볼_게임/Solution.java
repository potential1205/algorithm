package algorithm_study.week11.s5650_핀볼_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int T,N,answer;
    static int[][] board;
    static List<Point> whiteHallList;
    static int[][] changeDirections = {
            {},
            {2, 3, 1, 0},
            {1, 3, 0, 2},
            {3, 2, 0, 1},
            {2, 0, 3, 1},
            {2, 3, 0, 1}
    };

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static int gameStart(int sy, int sx, int sd){
        int score = 0;
        boolean flag = false;

        int y = sy;
        int x = sx;
        int d = sd;

        while(true){
            if(sy==y && sx==x && flag || board[y][x]==-1) break;

            y = y + dy[d];
            x = x + dx[d];
            flag = true;

            if(y<0 || y>=N || x<0 || x>=N){
                y = y - dy[d];
                x = x - dx[d];
                d = (d+2)%4;
                score++;
            }

            if(1<=board[y][x] && board[y][x]<=5){
                int num = board[y][x];
                d = changeDirections[num][d];
                score++;
            } else if(6<=board[y][x] && board[y][x]<=10){
                Point hall = move(y,x);
                y = hall.y;
                x = hall.x;
            }

        }

        return score;
    }

    static Point move(int ny, int nx) {
        int num = board[ny][nx];

        for(Point p : whiteHallList){
            if(board[p.y][p.x]==num && (p.y!=ny || p.x!=nx)){
                return new Point(p.y, p.x);
            }
        }

        return null;
    }

    static void solution(int t){
        answer = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j]!=0) continue;

                for(int d=0; d<4; d++){
                    int cnt = gameStart(i,j,d);
                    answer = Math.max(answer, cnt);
                }
            }
        }

        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine().trim());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            whiteHallList = new ArrayList<>();

            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if(6<=board[i][j] && board[i][j]<=10)
                        whiteHallList.add(new Point(i,j));
                }
            }

            solution(t);
        }
    }
}
