package algorithm_study_2.week1.b10844;

import java.util.Scanner;

public class Main {

    static int N, answer;
    static long mod = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        long[][] dp = new long[N+1][10];

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {

            for (int j = 0; j <= 9; j++) {

                if (j==0) {
                    dp[i][0] = dp[i-1][1] % mod;
                } else if (j==9) {
                    dp[i][9] = dp[i-1][8] % mod;
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] % mod;
                }
            }
        }

        for (int i = 0; i <= 9; i++) {
            answer += dp[N][i];
        }

        System.out.println(answer % mod);
    }
}
