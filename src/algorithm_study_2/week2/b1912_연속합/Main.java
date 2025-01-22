package algorithm_study_2.week2.b1912_연속합;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[n+1];

        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i-1]);
        }

        int answer = dp[0];
        for (int i = 1; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
