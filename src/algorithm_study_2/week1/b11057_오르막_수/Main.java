package algorithm_study_2.week1.b11057_오르막_수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] dp = new int[N+1][10];
        int answer = 0;

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i <= N; i++) { // 자릿수
            for (int j=0; j<=9; j++) { // 수
                for (int k=0; k<=j; k++) {
                    dp[i][j] += dp[i-1][k] % 10007;
                }
            }
        }

        for (int i = 0; i <= 9; i++) {
            answer += dp[N][i];
        }

        System.out.println(answer % 10007);
    }
}
