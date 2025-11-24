package study_archive.week10.b3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N,K,L,answer;
    static int[][] board;
    static char[] moveCmds;
    static Deque<Point> deque = new ArrayDeque<>();

    static int d;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void add(){
        Point p = deque.getFirst();
        int ky = p.y + dy[d];
        int kx = p.x + dx[d];

        deque.offerFirst(new Point(ky,kx));
    }

    static void turn(char direction){
        if(direction=='L'){
            d = (d+3)%4;
        } else if(direction=='D'){
            d = (d+1)%4;
        }
    }

    static void solution(){
        deque.offerFirst(new Point(0,0));
        d=1;

        for(int t=1; t<=10000; t++){
            add();
            if(isEnd()){
                answer = t;
                break;
            }

            checkApple();

            if(moveCmds[t]!='0'){
                turn(moveCmds[t]);
            }
        }

        System.out.println(answer);
    }

    static void checkApple() {
        Point p = deque.getFirst();
        if(board[p.y][p.x]==1){
            board[p.y][p.x] = 0;
        } else{
            deque.removeLast();
        }
    }

    static boolean isEnd() {
        Point p = deque.getFirst();
        if(p.y<0 || p.x<0 || p.y>=N || p.x>=N) return true;

        int index = 0;

        for(Point point : deque){
            if(index==0){
                index++;
                continue;
            }

            if(point.y == p.y && point.x == p.x){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());

        for(int i=0; i<K; i++){
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y-1][x-1] = 1;
        }

        st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());

        moveCmds = new char[10001];
        for(int i=0; i<L; i++){
            st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            moveCmds[time] = str.charAt(0);
        }

        solution();
    }
}
