package daily.y2025.m03.d07.b1535_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] L, J;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());

        L = new int[n+1];
        J = new int[n+1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1][100];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 99; j++) {
                if (L[i] <= j) { // j 현재 체력
                    dp[i][j] = Math.max(dp[i-1][j-L[i]]+J[i], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.print(dp[n][99]);
    }
}
