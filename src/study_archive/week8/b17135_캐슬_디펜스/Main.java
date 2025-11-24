package study_archive.week8.b17135_캐슬_디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N,M,D,answer,cnt;
    static int[][] board, originalBoard;
    static int[] archerPositions;
    static boolean[] isArcherAttack;
    static boolean[][] visit;
    static List<Point> eliminatedList;

    static class Point{
        int y,x,dist;

        Point(int y, int x, int dist){
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    static void attackOne(int dist,int y, int x, int archerIndex) {
        for(int j=-dist; j<=dist; j++) {
            for(int k=-dist; k<=dist; k++) {
                if(Math.abs(j)+Math.abs(k)<=dist) {
                    int ky = y + k;
                    int kx = x + j;

                    if(ky<0 || kx<0 || ky>=N || kx>=M || board[ky][kx]==0) continue;

                    if(!visit[ky][kx]) cnt++;
                    eliminatedList.add(new Point(ky,kx,0));
                    visit[ky][kx] = true;
                    isArcherAttack[archerIndex] = true;
                    return;
                }
            }
        }
    }

    static void attack(){
        isArcherAttack = new boolean[3];
        eliminatedList = new ArrayList<>();
        visit = new boolean[N][M];

        for(int dist=1; dist<=D; dist++){
            for(int i=0; i<3; i++) {
                if(isArcherAttack[i]) continue;
                attackOne(dist,N,archerPositions[i],i);
            }
        }

        for(Point p : eliminatedList)
            board[p.y][p.x] = 0;
    }

    static void move(){
        for(int i=N-1; i>0; i--){
            for(int j=0; j<M; j++){
                board[i][j] = board[i-1][j];
            }
        }

        for(int j=0; j<M; j++)
            board[0][j] = 0;
    }

    static void gameStart(){
        for(int i=0; i<N; i++){
            attack();
            move();
        }
    }

    static void setOriginalBoard() {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                board[i][j] = originalBoard[i][j];
            }
        }
    }

    static void combination(int depth, int start){
        if(depth==3){
            cnt = 0;
            setOriginalBoard();
            gameStart();
            answer = Math.max(answer,cnt);
            return;
        }

        for(int i=start; i<M; i++){
            archerPositions[depth] = i;
            combination(depth+1, i+1);
        }
    }

    static void solution(){
        answer = 0;
        combination(0,0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        originalBoard = new int[N][M];
        archerPositions = new int[3];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                originalBoard[i][j] = board[i][j];
            }
        }

        solution();
    }
}