package streak.d20250419.b11060_점프점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] move = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int i = 1; i <= move[x]; i++) {
                int nx = x + i;

                if (nx < n && dp[nx] > (dp[x] + 1)) {
                    dp[nx] = dp[x] + 1;
                    queue.offer(nx);
                }
            }
        }

        if (dp[n-1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n-1]);
        }
    }
}
