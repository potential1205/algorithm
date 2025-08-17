package streak.d20250817.p92343_양과_늑대_복습;

import java.util.*;

class Solution {
    static int n;
    static int answer;
    static int[] dp;
    static int[] parent;

    static void dfs(int mask, int sheep, int wolf, int[] info) {
        if (dp[mask] >= sheep) return;

        dp[mask] = sheep;
        answer = Math.max(answer, sheep);

        for (int i = 0; i < n; i++) {
            if (((mask >> i) & 1) == 1) continue;
            if (((mask >> parent[i]) & 1) == 0) continue;

            if (info[i] == 0 && sheep + 1 > wolf) {
                dfs(mask | (1 << i), sheep + 1, wolf, info);
            } else if (info[i] == 1 && sheep > wolf + 1) {
                dfs(mask | (1 << i), sheep, wolf + 1, info);
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        n = info.length;

        parent = new int[n];
        Arrays.fill(parent, -1);

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int p = edges[i][0];
            int c = edges[i][1];
            children[p].add(c);
            parent[c] = p;
        }

        dp = new int[1 << n];
        Arrays.fill(dp, -1);

        dfs(1, 1, 0, info);

        return answer;
    }
}