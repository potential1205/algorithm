package streak.d20250923.b2169_로봇_조종하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n][m];
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = board[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + board[0][j];
        }

        int[] left = new int[m];
        int[] right = new int[m];
        for (int i = 1; i < n; i++) {
            left[0] = dp[i - 1][0] + board[i][0];
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(left[j - 1] + board[i][j], dp[i - 1][j] + board[i][j]);
            }

            right[m - 1] = dp[i - 1][m - 1] + board[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] + board[i][j], dp[i - 1][j] + board[i][j]);
            }

            // 두 방향 중 최댓값
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
