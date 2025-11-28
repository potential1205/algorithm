package daily.y2025.m11.d28.r1.b2150_Strongly_Connected_Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayDeque<Integer> stack;
    static List<List<Integer>> graph;
    static List<List<Integer>> reverseGraph;
    static List<List<Integer>> sccList;
    static boolean[] visit;

    static void dfs(int cur) {
        visit[cur] = true;

        for (int next : graph.get(cur)) {
            if (visit[next]) continue;
            dfs(next);
        }

        stack.offerLast(cur);
    }

    static void reverseDfs(int cur, List<Integer> scc) {
        visit[cur] = true;
        scc.add(cur);

        for (int next : reverseGraph.get(cur)) {
            if (visit[next]) continue;
            reverseDfs(next, scc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        stack = new ArrayDeque<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            reverseGraph.get(to).add(from);
        }

        visit = new boolean[v + 1];
        for (int i = 1; i <= v; i++) {
            if (visit[i]) continue;
            dfs(i);
        }

        int answer = 0;
        visit = new boolean[v + 1];
        sccList = new ArrayList<>();
        while (!stack.isEmpty()) {
            int cur = stack.pollLast();
            if (visit[cur]) continue;
            List<Integer> scc = new ArrayList<>();
            reverseDfs(cur, scc);
            Collections.sort(scc);
            sccList.add(scc);
            answer++;
        }

        // 정렬
        Collections.sort(sccList, (a, b) -> a.get(0) - b.get(0));

        System.out.println(answer);
        for (int i = 0; i < answer; i++) {
            List<Integer> scc = sccList.get(i);
            for (int val : scc) {
                System.out.print(val + " ");
            }
            System.out.println(-1);
        }
    }
}
