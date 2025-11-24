package daily.y2025.m09.d19.p72416_매출_하락_최소화;

import java.util.*;

class Solution {

    static int n;
    static int[][] dp;
    static List<List<Integer>> graph = new ArrayList<>();

    static void dfs(int id, int[] sales) {
        // 자식 없으면 리프 처리
        if (graph.get(id).isEmpty()) {
            dp[id][1] = sales[id - 1];
            dp[id][0] = 0;
            return;
        }

        // 자식들 계산
        for (int next : graph.get(id)) {
            dfs(next, sales);
        }

        // id가 참석하는 경우
        int takeMinVal = sales[id - 1];
        for (int next : graph.get(id)) {
            takeMinVal += Math.min(dp[next][0], dp[next][1]);
        }
        dp[id][1] = takeMinVal;

        // id가 참석하지 않는 경우
        int skipMinVal = 0;
        boolean isVisit = false;
        int minExtra = Integer.MAX_VALUE;
        for (int next : graph.get(id)) {
            int skip = dp[next][0];
            int take = dp[next][1];
            skipMinVal += Math.min(skip, take);

            if (take <= skip) {
                isVisit = true;
            } else {
                minExtra = Math.min(minExtra, take - skip);
            }
        }

        dp[id][0] = skipMinVal + (isVisit ? 0 : minExtra);
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