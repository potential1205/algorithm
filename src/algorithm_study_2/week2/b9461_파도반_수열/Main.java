package algorithm_study_2.week2.b9461_파도반_수열;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T= sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();

            long[] dp = new long[n+1];

            if (n <=3) {
                System.out.println(1);
            } else {
                dp[1] = 1;
                dp[2] = 1;
                dp[3] = 1;

                for (int i = 4; i <= n; i++) {
                    dp[i] = dp[i-2] + dp[i-3];
                }

                System.out.println(dp[n]);
            }
        }
    }
}
