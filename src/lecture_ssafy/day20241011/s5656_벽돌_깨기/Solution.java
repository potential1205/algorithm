package lecture_ssafy.day20241011.s5656_벽돌_깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, W, H, answer;
    static int[][] board, copyBoard;
    static int[] start;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Point {
        int y, x, cnt;

        Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static void down() {
        Queue<Integer> queue = null;

        for (int i = 0; i < W; i++) {
            queue = new LinkedList<>();

            for (int j = H-1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    queue.offer(board[j][i]);
                    board[j][i] = 0;
                }
            }

            int index = H-1;

            while (!queue.isEmpty()) {
                int num = queue.poll();

                board[index][i] = num;
                index--;
            }
        }
    }

    static void bfs(Point startPoint) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(startPoint);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < cur.cnt; j++) {
                    int ny = cur.y + dy[i] * j;
                    int nx = cur.x + dx[i] * j;

                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;

                    if (board[ny][nx] != 0) {
                        queue.offer(new Point(ny, nx, board[ny][nx]));
                        board[ny][nx] = 0;
                    }
                }
            }
        }
    }

    static void gameStart() {
        for (int i = 0; i < N; i++) {
            int x = start[i]; // 구슬이 처음 떨어지는 위치
            Point point = null;

            for (int h = 0; h < H; h++) {
                if (board[h][x] != 0) {
                    point = new Point(h, x, board[h][x]);
                    break;
                }
            }

            if (point == null) continue;

            bfs(point);
            down();
        }
    }

    static void permutation(int depth){
        if (depth == N) {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    board[i][j] = copyBoard[i][j];
                }
            }

            gameStart();

            int cnt = 0;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (board[i][j] != 0) {
                        cnt++;
                    }
                }
            }

            answer = Math.min(answer, cnt);

            return;
        }

        for(int i = 0; i < W; i++) {
            start[depth] = i;
            permutation(depth+1);
        }
    }

    static void solution(int tc) {
        answer = Integer.MAX_VALUE;
        permutation(0);
        System.out.println("#" + tc + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[H][W];
            copyBoard = new int[H][W];
            start = new int[N];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(bf.readLine());

                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    copyBoard[i][j] = board[i][j];
                }
            }

            solution(tc);
        }
    }
}
