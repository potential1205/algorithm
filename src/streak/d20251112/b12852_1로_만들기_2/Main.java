package streak.d20251112.b12852_1로_만들기_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Node {
        int val;
        int cnt;
        List<Integer> list = new ArrayList<>();

        Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        int[] parent = new int[n + 1];
        dp[1] = 0;
        parent[1] = 0;

        for (int i = 2; i <= n; i++) {
            int best = dp[i - 1] + 1;
            int p = i - 1;

            if (i % 2 == 0) {
                int cand = dp[i / 2] + 1;
                if (cand < best) {
                    best = cand;
                    p = i / 2;
                }
            }

            if (i % 3 == 0) {
                int cand = dp[i/3] + 1;
                if (cand < best) {
                    best = cand;
                    p = i/3;
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
