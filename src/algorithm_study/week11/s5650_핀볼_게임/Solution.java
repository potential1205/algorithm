package algorithm_study.week11.s5650_핀볼_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int T,N,answer;
    static int[][] board;
    static List<WhiteHallPoint> whiteHallPointList;
    static int[][] changeDirections = {
            {},
            {2, 3, 1, 0},
            {1, 3, 0, 2},
            {3, 2, 0, 1},
            {2, 0, 3, 1},
            {2, 3, 0, 1}
    };

    static int[][][] visit;

    static class WhiteHallPoint{
        int y,x,num,id;

        WhiteHallPoint(int y, int x, int num, int id){
            this.y = y;
            this.x = x;
            this.num = num;
            this.id = id;
        }
    }

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Ball{
        int y,x,sy,sx,d,score,moveCnt;

        Ball(int y, int x, int sy, int sx, int score){
            this.y = y;
            this.x = x;
            this.sy = sy;
            this.sx = sx;
            this.score = score;
            this.moveCnt = 0;
        }
    }

    static Ball moveWhiteHall(Ball ball){
        int num = board[ball.y][ball.x];

        for(WhiteHallPoint point : whiteHallPointList){
            if(point.num==num && (point.y != ball.y || point.x != ball.x)){
                ball.y = point.y;
                ball.x = point.x;
                return ball;
            }
        }

        return null;
    }

    static int gameStart(int sy, int sx){
        Queue<Ball> queue = new LinkedList<>();
        queue.offer(new Ball(sy, sx, sy, sx, 0));

        while(!queue.isEmpty()){
            Ball ball = queue.poll();

            //changeDirections[][];

            for(int i=0; i<4; i++){
                int ny = ball.y + dy[i];
                int nx = ball.x + dx[i];

                // 이동할 위치가 블랙홀 또는 시작점인 경우, 최고 점수 갱신
                if(board[ny][nx]==-1 || ny==sy && nx==sx && ball.moveCnt!=0){
                    answer = Math.max(answer, ball.score);
                    continue;
                }

                // 이동할 위치가 웜홀이라면, 정해진 위치로 이동
                if(6<=board[ball.y][ball.x] && board[ball.y][ball.x]<=10) {
                    queue.offer(moveWhiteHall(ball));
                    continue;
                }





            }


            // 2. 바라보는 방향으로 이동
            int ny = ball.y + dy[ball.d];
            int nx = ball.x + dx[ball.d];

            // 2.1 벽을 만난 경우, 방향 반대로 변경
            if(ny<0 || nx<0 || ny>=N || nx>=N) {
                ball.d = (ball.d + 2) % 4;
                ball.score++;
            } else{
                ball.y = ny;
                ball.x = nx;
            }

            ball.moveCnt++;
            queue.offer(ball);
        }

        return 0;
    }

    static void solution(int t){
        answer = 0;
        visit = new int[N][N][4];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j]!=0) continue;

                int cnt = gameStart(i,j);
                answer = Math.max(answer, cnt);
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
            whiteHallPointList = new ArrayList<>();

            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if(6<=board[i][j] && board[i][j]<=10)
                        whiteHallPointList.add(new WhiteHallPoint(i, j, board[i][j], whiteHallPointList.size()));
                }
            }

            solution(t);
        }
    }
}
