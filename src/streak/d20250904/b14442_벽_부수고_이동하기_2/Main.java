package streak.d20250904.b14442_벽_부수고_이동하기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int y;
        int x;
        int k;
        int cnt;

        Node(int y, int x, int k, int cnt) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int[][][] dp = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int a = 0; a <= k; a++) {
                    dp[i][j][a] = Integer.MAX_VALUE;
                }
            }
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0,1));
        dp[0][0][0] = 1;

        int[] dy = new int[]{-1, 1, 0, 0};
        int[] dx = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                if (board[ny][nx] == '0' && node.k <= k) {
                    if (dp[ny][nx][node.k] > node.cnt + 1) {
                        queue.offer(new Node(ny, nx, node.k, node.cnt + 1));
                        dp[ny][nx][node.k] = node.cnt + 1;
                    }
                } else if (board[ny][nx] == '1' && node.k + 1 <= k) {
                    if (dp[ny][nx][node.k + 1] > node.cnt + 1) {
                        queue.offer(new Node(ny, nx, node.k + 1, node.cnt + 1));
                        dp[ny][nx][node.k + 1] =  node.cnt + 1;
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            answer = Math.min(answer, dp[n - 1][m - 1][i]);
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
