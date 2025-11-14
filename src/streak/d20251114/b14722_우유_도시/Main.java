package streak.d20251114.b14722_우유_도시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 위에서 오는 경우
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }

                // 왼쪽에서 오는 경우
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                // 먹은 우유 개수를 알면 현재 먹어야할 우유 종류를 알 수 있음
                if (arr[i][j] == dp[i][j] % 3) {
                    dp[i][j]++;
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}
