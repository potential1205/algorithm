package algorithm_study.week13.b17472_다리_만들기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[][] board;
    static int[][] bridgeNum;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static List<List<Point>> pointList;

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    static List<Point> connect(int sy, int sx, int num) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sy, sx));
        bridgeNum[sy][sx] = num;

        List<Point> localPointList = new ArrayList<>();

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            localPointList.add(p);

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                if (bridgeNum[ny][nx] == 0 && board[ny][nx] == 1) {
                    queue.offer(new Point(ny, nx));
                    bridgeNum[ny][nx] = num;
                }
            }
        }

        return localPointList;
    }

    static void game() {
        // 다리 구분하기
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (bridgeNum[i][j] == 0 && board[i][j] == 1) {
                    pointList.add(connect(i, j, num));
                    num++;
                }
            }
        }

        for (int i = 0; i < num; i++) { // 각 땅의 모든 점으로 부터 사방으로 탐색
            for (Point p : pointList.get(i)) {
                for (int d = 0; d < 4; d++) {
                    int ny = p.y;
                    int nx = p.x;

                    while(true) {
                        ny = ny + dy[d];
                        nx = nx + dx[d];

                        if (ny < 0 || nx < 0 || ny>=N || nx>=M) break;

                        if (bridgeNum[ny][nx]==bridgeNum[p.y][p.x]) break;

                        if (bridgeNum[ny][nx]!=0) {
                            // 거리 갱신
                        }
                    }


                }

            }
        }
    }

    static void solution() {
        answer = 0;
        game();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        bridgeNum = new int[N][M];
        pointList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
