package daily.y2025.m07.d17.p49191_순위;

import java.util.*;

class Solution {

    static int bfs(List<List<Integer>> graph, int root, int n) {
        boolean[] visit = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visit[cur]) continue;
            visit[cur] = true;

            for (int next : graph.get(cur)) {
                queue.offer(next);
            }
        }

        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (root != i && visit[i]) {
                cnt++;
            }
        }

        return cnt;
    }

    public int solution(int n, int[][] results) {
        int answer = 0;

        List<List<Integer>> graphUp = new ArrayList<>();
        List<List<Integer>> graphDown = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graphUp.add(new ArrayList<>());
            graphDown.add(new ArrayList<>());
        }

        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];

            graphUp.get(win).add(lose);
            graphDown.get(lose).add(win);
        }

        for (int i = 1; i <= n; i++) {
            int cntUp = bfs(graphUp, i, n);
            int cntDown = bfs(graphDown, i, n);

            if (cntUp + cntDown == (n - 1)) {
                answer++;
            }
        }

        return answer;
    }
}