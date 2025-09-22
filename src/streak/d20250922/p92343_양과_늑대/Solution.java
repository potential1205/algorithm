package streak.d20250922.p92343_양과_늑대;

import java.util.*;

class Solution {
    static int n;
    static int answer;
    static boolean[] visit;
    static List<List<Integer>> graph;

    static void dfs(int depth, int sheep, int wolf, int[] info) {
        answer = Math.max(answer, sheep);

        for (int i = 0; i < n; i++) {
            if (!visit[i]) continue;

            for (int next : graph.get(i)) {
                if (visit[next]) continue;

                if (info[next] == 0) {
                    visit[next] = true;
                    dfs(depth + 1, sheep + 1 , wolf, info);
                    visit[next] = false;
                } else if (info[next] == 1 && sheep > (wolf + 1)) {
                    visit[next] = true;
                    dfs(depth + 1, sheep, wolf + 1, info);
                    visit[next] = false;
                }
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        n = info.length;
        visit = new boolean[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        visit[0] = true;
        dfs(0, 1, 0, info);

        return answer;
    }
}