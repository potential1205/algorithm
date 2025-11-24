package daily.y2025.m11.d10.p16933_벽_부수고_이동하기_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static final int DAY = 0;
    static final int NIGHT = 1;
    static int N, M, K;
    static int[][][][] dp;
    static char[][] board;

    static class Node {
        int y, x;
        int cnt, dist;
        int time;

        public Node(int y, int x, int cnt, int dist, int time) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.dist = dist;
            this.time = time;
        }
    }

    static void bfs() {

        // 상하좌우
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, K, 1, 0));
        dp[0][0][K][0] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            int nextTime = cur.time == DAY ? NIGHT : DAY;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                // 다음 칸이 벽이 아닐 때는 벽 부수고 이동 (횟수가 남아있는 경우만)
                if (board[ny][nx] == '0') {
                   if (dp[ny][nx][cur.cnt][nextTime] > cur.dist + 1) {
                       dp[ny][nx][cur.cnt][nextTime] = cur.dist + 1;
                       queue.offer(new Node(ny, nx, cur.cnt, cur.dist + 1, nextTime));
                   }

                } else {
                    // 다음 칸이 벽이고 낮인 경우 벽 부수고 이동 (횟수가 남아있는 경우만)
                    if (cur.time == DAY && cur.cnt - 1 >= 0 && dp[ny][nx][cur.cnt - 1][nextTime] > cur.dist + 1) {
                        dp[ny][nx][cur.cnt - 1][nextTime] = cur.dist + 1;
                        queue.offer(new Node(ny, nx, cur.cnt - 1, cur.dist + 1, nextTime));

                    // 다음 칸이 벽이고 밤인 경우 제자리 이동
                    } else if (cur.time == NIGHT && dp[cur.y][cur.x][cur.cnt][nextTime] > cur.dist + 1) {
                        dp[cur.y][cur.x][cur.cnt][nextTime] = cur.dist + 1;
                        queue.offer(new Node(cur.y, cur.x, cur.cnt, cur.dist + 1, nextTime));
                    }
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
        dp = new int[N][M][K + 1][2]; // 해당 위치에 벽을 k번 부술 수 있을 때, 낮이거나 밤인 경우의 최단 경로를 저장

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
