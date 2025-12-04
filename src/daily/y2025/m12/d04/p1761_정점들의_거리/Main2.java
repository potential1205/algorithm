package daily.y2025.m12.d04.p1761_정점들의_거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static final int LOG = 17;
    static int n, m;
    static int[] distArr, depthArr;
    static int[][] parentArr;
    static List<List<Edge>> graph;

    static class Edge {
        int num, dist;
        Edge(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static void dfs(int cur, int dist, int depth, int parent) {
        distArr[cur] = dist;
        depthArr[cur] = depth;
        parentArr[cur][0] = parent;

        for (Edge next : graph.get(cur)) {
            if (next.num != parent) {
                dfs(next.num, dist + next.dist, depth + 1, cur);
            }
        }
    }

    static int findLca(int a, int b) {
        if (depthArr[a] < depthArr[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 높이 맞추기
        int diff = depthArr[a] - depthArr[b];
        for (int i = LOG; i >= 0; i--) {
            if ((diff & (1 << i)) != 0) {
                a = parentArr[a][i];
            }
        }

        // 둘 중 하나가 최소 공통 조상인 경우
        if (a == b) return a;

        for (int i = LOG; i >= 0; i--) {
            if (parentArr[a][i] != parentArr[b][i]) {
                a = parentArr[a][i];
                b = parentArr[b][i];
            }
        }

        return parentArr[a][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        distArr = new int[n + 1];
        depthArr = new int[n + 1];
        parentArr = new int[n + 1][LOG + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        // 정보 초기화
        dfs(1, 0, 0, 0);

        // 이진 리프팅 테이블
        for (int i = 1; i <= LOG; i++) {
            for (int j = 1; j <= n; j++) {
                parentArr[j][i] = parentArr[parentArr[j][i - 1]][i - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = findLca(a, b);
            int dist = distArr[a] + distArr[b] - 2 * distArr[lca];
            sb.append(dist).append("\n");
        }
        System.out.println(sb);
    }
}
