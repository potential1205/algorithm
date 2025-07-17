package streak.d20250717.p86971_전력망을_둘로_나누기;

import java.util.*;

class Solution {
    static int bfs(List<List<Integer>> graph, int start, int otherRoot, int n) {
        int cnt = 0;
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == otherRoot || visit[cur]) continue;
            visit[cur] = true;
            cnt++;

            for (int next : graph.get(cur)) {
                queue.offer(next);
            }
        }

        return cnt;
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        int m = wires.length;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = wires[i][0];
            int to = wires[i][1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int i = 0; i < m; i++) {
            int rootA = wires[i][0];
            int rootB = wires[i][1];

            int cntA = bfs(graph, rootA, rootB, n);
            int cntB = bfs(graph, rootB, rootA, n);

            answer = Math.min(answer, Math.abs(cntA - cntB));
        }

        return answer;
    }
}
