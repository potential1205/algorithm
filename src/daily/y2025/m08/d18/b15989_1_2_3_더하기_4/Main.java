package daily.y2025.m08.d18.b15989_1_2_3_더하기_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dp = new int[10001];
        for (int i = 0; i <= 10000; i++) {
            dp[i] = 1;
        }

        for (int i = 2; i <= 10000; i++) {
            dp[i] += dp[i - 2];
        }

        for (int i = 3; i <= 10000; i++) {
            dp[i] += dp[i - 3];
        }

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            System.out.println(dp[val]);
        }
    }
}
