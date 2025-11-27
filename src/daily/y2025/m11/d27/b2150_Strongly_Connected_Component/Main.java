package daily.y2025.m11.d27.b2150_Strongly_Connected_Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayDeque<Integer> stack;
    static List<List<Integer>> sccList;
    static List<List<Integer>> reverseGraph;
    static List<List<Integer>> graph;
    static boolean[] visit;

    // dfs 순차적으로 탐색, 스택에 역방향으로 저장
    static void dfs1(int cur) {
        visit[cur] = true;

        for (int next : graph.get(cur)) {
            if (!visit[next]) {
                dfs1(next);
            }
        }

        stack.offerLast(cur);
    }

    static void dfs2(int cur, List<Integer> scc) {
        visit[cur] = true;
        scc.add(cur);

        for (int next : reverseGraph.get(cur)) {
            if (!visit[next]) {
                dfs2(next, scc);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        sccList = new ArrayList<>();
        stack = new ArrayDeque<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }

        visit = new boolean[v + 1];
        for (int cur = 1; cur <= v; cur++) {
            if (!visit[cur]) {
                dfs1(cur);
            }
        }

        visit = new boolean[v + 1];
        while (!stack.isEmpty()) {
            int cur = stack.pollLast();
            if (!visit[cur]) {
                List<Integer> scc = new ArrayList<>();
                dfs2(cur, scc);
                sccList.add(scc);
            }
        }

        // 1) 각 SCC 내부 정렬 (정점 오름차순)
        for (List<Integer> scc : sccList) {
            Collections.sort(scc);
        }

        // 2) SCC 리스트 정렬 (각 SCC의 가장 작은 정점 기준)
        sccList.sort(Comparator.comparingInt(a -> a.get(0)));

        StringBuilder sb = new StringBuilder();
        sb.append(sccList.size()).append("\n");
        for (int i = 0; i < sccList.size(); i++) {
            List<Integer> scc = sccList.get(i);
            for (int cur : scc) {
                sb.append(cur).append(" ");
            }
            sb.append(-1).append("\n");
        }

        System.out.println(sb);
    }
}
