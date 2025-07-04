package streak.d20250704.p42898_등굣길;

import java.util.*;

class Try1 {

    static int[] dy = {1, 0};
    static int[] dx = {0, 1};

    static class Node {
        int y;
        int x;
        int cnt;

        Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static int bfs(int n, int m, int[][] board, int[][] dp, int[][] moveCnt) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));
        int answer = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if ((n+m-2) == node.cnt && node.y == (n-1) && node.x == (m-1)) {
                answer++;
            }

            for (int i = 0; i < 2; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m || board[ny][nx] == 1 || moveCnt[ny][nx] < node.cnt) {
                    continue;
                }

                dp[ny][nx]++;
                queue.offer(new Node(ny, nx, node.cnt + 1));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return answer;
    }

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n][m];
        int[][] board = new int[n][m];
        int[][] moveCnt = new int[n][m];

        for (int i = 0; i < puddles.length; i++) {
            board[puddles[i][1] - 1][puddles[i][0] - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 1) {
                    moveCnt[i][j] = i + j;
                }
            }
        }

        return bfs(n, m, board, dp, moveCnt);
    }
}