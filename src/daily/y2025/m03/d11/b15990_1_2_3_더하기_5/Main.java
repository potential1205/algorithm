package daily.y2025.m03.d11.b15990_1_2_3_더하기_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long result = 0;
        long [][] dp = new long[100001][4];

        dp[1][1]=1;
        dp[2][2]=1;
        dp[3][1]=1;
        dp[3][2]=1;
        dp[3][3]=1;

        for(int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % 1000000009;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % 1000000009;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % 1000000009;
        }

        for(int i = 0; i < t; i++){
            int k = Integer.parseInt(br.readLine());
            result = (dp[k][1] + dp[k][2] + dp[k][3]) % 1000000009;
            System.out.println(result);
        }
    }
}
