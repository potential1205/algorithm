package daily.y2025.m12.d03.p133500_등대;

import java.util.*;

class Solution2 {

    static List<List<Integer>> graph;
    static boolean[] visit;
    static int[][] dp;

    static void dfs(int cur, int parent) {

        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (int next : graph.get(cur)) {
            if (next == parent) {
                continue;
            }

            dfs(next, cur);

            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            dp[cur][0] += dp[next][1];
        }

        return;
    }

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] node : lighthouse) {
            graph.get(node[0]).add(node[1]);
            graph.get(node[1]).add(node[0]);
        }

        dp = new int[n + 1][2];
        visit = new boolean[n + 1];

        dfs(1, 0);

        return Math.min(dp[1][0], dp[1][1]);
    }
}