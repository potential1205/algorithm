package daily.y2025.m12.d03.p133500_등대;

import java.util.*;

class Solution {

    static List<List<Integer>> graph;
    static boolean[] visit;
    static int[][] dp;

    static void dfs(int cur) {
        if (visit[cur]) {
            return;
        }

        visit[cur] = true;

        for (int next : graph.get(cur)) {
            dfs(next);
        }

        // 현재 등대를 포함시키면
        // 이웃한 등대가 켜져있어도, 꺼져있어도 상관 없음
        dp[cur][1] = 1;
        for (int next : graph.get(cur)) {
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }

        // 현재 등대를 포함시키지 않으면
        dp[cur][0] = 0;
        int cnt = 0;
        int gap = 0;

        for (int next : graph.get(cur)) {
            dp[cur][0] += Math.min(dp[next][0], dp[next][1]);

            if (dp[next][0] > dp[next][1]) {
                cnt++;
            }

            gap = Math.max(gap, dp[next][1] - dp[next][0]);
        }

        if (cnt == 0) {
            dp[cur][0] += gap;
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

        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }
}