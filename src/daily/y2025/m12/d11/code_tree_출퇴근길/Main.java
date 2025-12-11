package daily.y2025.m12.d11.code_tree_출퇴근길;

import java.util.*;

public class Main {

    static int n, m;
    static List<List<Integer>> forward;
    static List<List<Integer>> backward;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        forward = new ArrayList<>();
        backward = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            forward.add(new ArrayList<>());
            backward.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            forward.get(x).add(y);
            backward.get(y).add(x);
        }

        int S = sc.nextInt();
        int T = sc.nextInt();

        boolean[] reachFromSNoT = new boolean[n + 1];
        boolean[] reachFromTNoS = new boolean[n + 1];
        boolean[] canReachT      = new boolean[n + 1];
        boolean[] canReachS      = new boolean[n + 1];

        // 1) S에서 T를 밟지 않고 도달 가능한 정점들
        bfsWithBan(S, T, forward, reachFromSNoT);

        // 2) T에서 S를 밟지 않고 도달 가능한 정점들
        bfsWithBan(T, S, forward, reachFromTNoS);

        // 3) v -> T 가능한지 확인  (역방향에서 T 시작)
        bfs(backward, T, canReachT);

        // 4) v -> S 가능한지 확인  (역방향에서 S 시작)
        bfs(backward, S, canReachS);

        int answer = 0;
        for (int v = 1; v <= n; v++) {
            if (v == S || v == T) continue;
            if (reachFromSNoT[v] && reachFromTNoS[v] && canReachT[v] && canReachS[v]) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void bfsWithBan(int start, int banned, List<List<Integer>> graph, boolean[] visited) {
        if (start == banned) return;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (next == banned) continue;  // banned 노드는 아예 안 들어감
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }

    // 일반 BFS
    static void bfs(List<List<Integer>> graph, int start, boolean[] visited) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
