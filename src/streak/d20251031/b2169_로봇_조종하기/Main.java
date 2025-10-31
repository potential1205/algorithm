package streak.d20251031.b2169_로봇_조종하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = board[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] += dp[0][j - 1] + board[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] left = new int[m];
            int[] right = new int[m];
            left[0] = dp[i - 1][0] + board[i][0];
            right[m - 1] = dp[i - 1][m - 1] + board[i][m - 1];

            // 왼쪽 -> 오른쪽
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(board[i][j] + left[j - 1], board[i][j] + dp[i - 1][j]);
            }

            // 오른쪽 -> 왼쪽
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(board[i][j] + right[j + 1], board[i][j] + dp[i - 1][j]);
            }

            //
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
