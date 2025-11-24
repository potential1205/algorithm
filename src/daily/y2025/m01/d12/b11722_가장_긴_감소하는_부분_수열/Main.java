package daily.y2025.m01.d12.b11722_가장_긴_감소하는_부분_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int answer = 0;

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j=0; j<i; j++) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);

    }
}
