package daily.y2025.m12.d04.p1761_정점들의_거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    LCA를 활용한 문제
    1. 그래프 만들기
    2. 그래프 탐색하며 각 노드의 깊이, 루트와의 거리, 부모 업데이트
    3. 이진 리프팅 테이블 생성 및 업데이트 (LCA를 빠르게 탐색)
    4. LCA 구현
    5. 쿼리 수행
 */

public class Main {

    static int n, m;
    static final int LOG = 17;
    static int[] distArr; // root부터 각 정점까리 최단거리
    static int[] depthArr;
    static int[][] parentArr;
    static List<List<Node>> graph;

    static class Node {
        int num, cost;
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static void dfs(int cur, int parent, int depth, int dist) {
        parentArr[cur][0] = parent;
        depthArr[cur] = depth;
        distArr[cur] = dist;

        for (Node next : graph.get(cur)) {
            if (next.num != parent) {
                dfs(next.num, cur, depth + 1, dist + next.cost);
            }
        }
    }

    static int lca(int a, int b) {
        // 깊이가 더 깊은 것을 a로 고정
        if (depthArr[a] < depthArr[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이 차이
        int diff = depthArr[a] - depthArr[b];
        for (int k = LOG; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                a = parentArr[a][k];
            }
        }

        if (a == b) return a;

        for (int k = LOG; k >= 0; k--) {
            if (parentArr[a][k] != parentArr[b][k]) {
                a = parentArr[a][k];
                b = parentArr[b][k];
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
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        // 트리 만들기
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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int p = lca(from, to);
            int dist = distArr[from] + distArr[to] - 2 * distArr[p];
            sb.append(dist).append("\n");
        }

        System.out.println(sb);
    }
}
