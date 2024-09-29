package algorithm_study.week8.b23288_주사위_굴리기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K,answer;
    static int[][] board;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static Dice dice;

    static class Dice{
        int y,x,d;
        int[] nums = {1,2,3,4,5,6}; // 윗면, 뒷면, 오른쪽, 왼쪽, 앞쪽, 밑쪽

        Dice(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void diceRotate(int d){
        int[] nums = dice.nums.clone();

        if(d==0){
            dice.nums[0] = nums[4];
            dice.nums[1] = nums[0];
            dice.nums[2] = nums[2];
            dice.nums[3] = nums[3];
            dice.nums[4] = nums[5];
            dice.nums[5] = nums[1];
        } else if(d==1){
            dice.nums[0] = nums[3];
            dice.nums[1] = nums[1];
            dice.nums[2] = nums[0];
            dice.nums[3] = nums[5];
            dice.nums[4] = nums[4];
            dice.nums[5] = nums[2];
        } else if(d==2){
            dice.nums[0] = nums[1];
            dice.nums[1] = nums[5];
            dice.nums[2] = nums[2];
            dice.nums[3] = nums[3];
            dice.nums[4] = nums[0];
            dice.nums[5] = nums[4];
        } else if(d==3){
            dice.nums[0] = nums[2];
            dice.nums[1] = nums[1];
            dice.nums[2] = nums[5];
            dice.nums[3] = nums[0];
            dice.nums[4] = nums[4];
            dice.nums[5] = nums[3];
        }
    }

    static void calScore(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(dice.y, dice.x));

        boolean[][] visit = new boolean[N][M];
        visit[dice.y][dice.x] = true;

        int val = board[dice.y][dice.x];
        int cnt = 1;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int i=0; i<4; i++){
                int ky = p.y + dy[i];
                int kx = p.x + dx[i];

                if(ky<0 || kx<0 || ky>=N || kx>=M || visit[ky][kx] || board[ky][kx] != val) continue;

                queue.add(new Point(ky,kx));
                visit[ky][kx] = true;
                cnt++;
            }
        }

        answer += (cnt * val);
    }

    static void move(){
        int ky = dice.y + dy[dice.d];
        int kx = dice.x + dx[dice.d];

        if(ky<0 || kx<0 || ky>=N || kx>=M){
            dice.d = (dice.d+2)%4;
            ky = dice.y + dy[dice.d];
            kx = dice.x + dx[dice.d];
        }

        dice.y = ky;
        dice.x = kx;
        diceRotate(dice.d);
    }

    static void compare(){
        if(dice.nums[5] > board[dice.y][dice.x]){
            dice.d = (dice.d+1)%4;
        } else if(dice.nums[5] < board[dice.y][dice.x]){
            dice.d = (dice.d+3)%4;
        }
    }

    static void solution(){
        answer = 0;
        for(int i=0; i<K; i++){
            move();
            calScore();
            compare();
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dice = new Dice(0,0,1);

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
