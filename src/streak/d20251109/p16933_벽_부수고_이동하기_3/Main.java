package streak.d20251109.p16933_벽_부수고_이동하기_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][][][] dp;
    static char[][] board;
    static final int DAY = 0;
    static final int NIGHT = 1;

    static class Node {
        // 위치
        int y, x;

        // 이동거리, 벽 부순 횟수, 낮/밤
        int dist, cnt, type;

        public Node(int y, int x, int dist, int cnt, int type) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.cnt = cnt;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", dist=" + dist +
                    ", cnt=" + cnt +
                    ", type=" + type +
                    '}';
        }
    }

    static void bfs() {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1, K, DAY));
        dp[0][0][K][DAY] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            //System.out.println(cur);

            int nextType = (cur.type == DAY) ? NIGHT : DAY;

            // 4방향
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                // 벽이 아닌 경우
                if (board[ny][nx] == '0') {
                    if ((cur.dist + 1) < dp[ny][nx][cur.cnt][nextType]) {
                        queue.offer(new Node(ny, nx, cur.dist + 1, cur.cnt, nextType));
                        dp[ny][nx][cur.cnt][nextType] = cur.dist + 1;
                    }

                // 벽일 떄
                } else {
                    if (cur.type == DAY) {
                        if ((cur.cnt - 1) >= 0 && (cur.dist + 1) < dp[ny][nx][cur.cnt - 1][nextType]) {
                            queue.offer(new Node(ny, nx, cur.dist + 1, cur.cnt - 1, nextType));
                            dp[ny][nx][cur.cnt - 1][nextType] = cur.dist + 1;
                        }
                    }
                }
            }

            // 다음 이동 칸이 벽이고 현재 밤이면, 제자리이동
            if (cur.type == NIGHT) {
                if ((cur.dist + 1) < dp[cur.y][cur.x][cur.cnt][nextType]) {
                    queue.offer(new Node(cur.y, cur.x, cur.dist + 1, cur.cnt , nextType));
                    dp[cur.y][cur.x][cur.cnt][nextType] = cur.dist + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        dp = new int[N][M][K + 1][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k][0] = 10000000;
                    dp[i][j][k][1] = 10000000;
                }
            }
        }

        bfs();
        int answer = 10000000;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(answer, dp[N - 1][M - 1][i][0]);
            answer = Math.min(answer, dp[N - 1][M - 1][i][1]);
        }

        if (answer == 10000000) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
