package algorithm_study_2.week2.b9465_스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N+1][2];
            st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N+1][2];
            dp[0][0] = 0;
            dp[0][1] = 0;
            dp[1][0] = arr[1][0];
            dp[1][1] = arr[1][1];

            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.max(dp[i-1][1] + arr[i][0], dp[i-2][1] + arr[i][0]);
                dp[i][1] = Math.max(dp[i-1][0] + arr[i][1], dp[i-2][0] + arr[i][1]);
            }

            sb.append(Math.max(dp[N][0], dp[N][1])).append("\n");
        }

        System.out.println(sb);
    }
}
