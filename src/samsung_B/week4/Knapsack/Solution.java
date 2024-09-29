package samsung_B.week4.Knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,K,answer;
    static int[][] info;
    static int[] dp;

    static void solution(int depth, int start, int val, int weight){
        if(weight>K) return;

        if(depth==N){
            dp[weight] = Math.max(dp[weight], val);
            return;
        }

        for(int i=start; i<N; i++){
            if(weight>K) continue;
            dp[weight] = Math.max(dp[weight], val);
            solution(depth+1, i+1, val+info[i][1], weight +info[i][0]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            info = new int[N][2];
            dp = new int[1001];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                info[i][0] = Integer.parseInt(st.nextToken());
                info[i][1] = Integer.parseInt(st.nextToken());
            }

            solution(0,0,0,0);

            for(int i=1; i<=K; i++){
                answer = Math.max(answer, dp[i]);
            }

            System.out.println(answer);
        }
    }
}
