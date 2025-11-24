package daily.y2025.m11.d13.b12852_1로_만들기_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        int[] parent = new int[n + 1];
        dp[1] = 0;
        parent[1] = 0;

        for (int i = 2; i <= n; i++) {

            // i to i-1 까지 최소 횟수
            int best = dp[i - 1] + 1;
            int p = i - 1;

            // i to i / 2 까지 최소 횟수
            if (i % 2 == 0) {
                if (dp[i / 2] < best) {
                    best = Math.min(best, dp[i / 2] + 1);
                    p = i / 2;
                }
            }

            // i to i / 3 까지 최소 횟수
            if (i % 3 == 0) {
                if (dp[i / 3] < best) {
                    best = Math.min(best, dp[i / 3] + 1);
                    p = i / 3;
                }
            }

            dp[i] = best;
            parent[i] = p;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append('\n');

        int cur = n;
        while (cur != 0) {
            sb.append(cur).append(' ');
            if (cur == 1) break;
            cur = parent[cur];
        }
        System.out.println(sb);
    }
}
