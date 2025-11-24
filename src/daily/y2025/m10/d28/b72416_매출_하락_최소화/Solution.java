package daily.y2025.m10.d28.b72416_매출_하락_최소화;

import java.util.*;

class Solution {

    static int n;
    static int[][] dp;
    static List<List<Integer>> graph = new ArrayList<>();

    static void dfs(int id, int[] sales) {

        // 리프 노드인 경우
        if (graph.get(id).isEmpty()) {
            dp[id][0] = 0;
            dp[id][1] = sales[id - 1];
            return;
        }

        // 자식 돌기
        for (int next : graph.get(id)) {
            dfs(next, sales);
        }

        // id가 참석하는 경우
        int takeVal = sales[id - 1];
        for (int next : graph.get(id)) {
            takeVal += Math.min(dp[next][0], dp[next][1]);
        }
        dp[id][1] = takeVal;

        // id가 참석하지 않는 경우
        int skipVal = 0;
        int cost = Integer.MAX_VALUE;
        boolean visit = false;

        for (int next : graph.get(id)) {
            int skip = dp[next][0];
            int take = dp[next][1];
            skipVal += Math.min(skip, take);

            if (take <= skip) {
                visit = true;
            } else { // 자식 중 아무도 참석하지 않는 경우 한 명은 반드시 포함되어야 하므로 뽑는 기준을 정해야함
                cost = Math.min(cost, take - skip);
            }
        }

        dp[id][0] = skipVal + (visit ? 0 : cost);
    }

    public int solution(int[] sales, int[][] links) {
        n = sales.length;
        dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] link : links) {
            graph.get(link[0]).add(link[1]);
        }

        dfs(1, sales);

        return Math.min(dp[1][0], dp[1][1]);
    }
}