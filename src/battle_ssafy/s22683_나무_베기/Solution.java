package battle_ssafy.s22683_나무_베기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,K,answer;
    static char[][] board;
    static Point start,end;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static class Car{
        int y,x,d,cnt,moveCnt;

        @Override
        public String toString() {
            return "Car{" +
                    "y=" + y +
                    ", x=" + x +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    ", moveCnt=" + moveCnt +
                    '}';
        }

        Car(int y, int x, int d, int cnt, int moveCnt){
            this.y = y;
            this.x = x;
            this.d = d;
            this.cnt = cnt;
            this.moveCnt = moveCnt;
        }
    }

    static void game(){
        boolean[][][][] visit = new boolean[N][N][4][K+1];
        Queue<Car> queue = new LinkedList<>();
        queue.offer(new Car(start.y, start.x, 0, 0,0));
        visit[start.y][start.x][0][0] = true;

        while(!queue.isEmpty()){
            Car car = queue.poll();

            if(car.y == end.y && car.x == end.x){
                answer = car.moveCnt;
                return;
            }

            for(int i=0; i<3; i++){
                if(i==0){
                    int ny = car.y + dy[car.d];
                    int nx = car.x + dx[car.d];

                    if(ny<0 || nx<0 || ny>=N || nx>=N) continue;

                    if(board[ny][nx]=='T' && car.cnt<K){
                        if(visit[ny][nx][car.d][car.cnt+1]) continue;

                        queue.offer(new Car(ny,nx,car.d,car.cnt+1,car.moveCnt+1));
                        visit[ny][nx][car.d][car.cnt+1] = true;

                    } else if(board[ny][nx]=='G'){
                        if(visit[ny][nx][car.d][car.cnt]) continue;

                        queue.offer(new Car(ny,nx,car.d,car.cnt,car.moveCnt+1));
                        visit[ny][nx][car.d][car.cnt] = true;
                    }
                } else if(i==1){
                    if(visit[car.y][car.x][(car.d+1)%4][car.cnt]) continue;

                    queue.offer(new Car(car.y,car.x,(car.d+1)%4,car.cnt,car.moveCnt+1));
                    visit[car.y][car.x][(car.d+1)%4][car.cnt] = true;

                } else if(i==2) {
                    if(visit[car.y][car.x][(car.d+3)%4][car.cnt]) continue;

                    queue.offer(new Car(car.y,car.x,(car.d+3)%4,car.cnt,car.moveCnt+1));
                    visit[car.y][car.x][(car.d+3)%4][car.cnt] = true;
                }
            }
        }
        answer = -1;
    }

    static void solution(int t){
        answer = Integer.MAX_VALUE;
        game();
        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new char[N][N];

            for(int i=0; i<N; i++){
                String line = bf.readLine();

                for(int j=0; j<N; j++){
                    board[i][j] = line.charAt(j);

                    if(board[i][j]=='X') start = new Point(i,j);
                    else if(board[i][j]=='Y') end = new Point(i,j);
                }
            }
            board[start.y][start.x] = 'G';
            board[end.y][end.x] = 'G';

            solution(t);
        }
    }
}
