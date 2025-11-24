package daily.y2025.m02.d08.b1520_내리막_길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, answer;
    static int[][] board, dp;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    static int dfs(int sy, int sx) {
        if (sy == n-1 && sx == m-1) { // 목적지 도착한 경우 +1
            return 1;
        }

        if (dp[sy][sx] != -1) {
            return dp[sy][sx];
        }

        dp[sy][sx] = 0;

        for (int i = 0; i < 4; i++) {
            int ky = sy + dy[i];
            int kx = sx + dx[i];

            if (ky < 0 || kx < 0 || ky >= n || kx >= m) continue;

            if (board[sy][sx] > board[ky][kx]) {
                dp[sy][sx] += dfs(ky,kx);
            }
        }

        return dp[sy][sx];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        answer = dfs(0,0);

        System.out.println(answer);
    }
}
