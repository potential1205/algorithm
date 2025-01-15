package streak.d20250113.b11724_연결_요소의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int answer;
    static boolean[] visit;
    static List<List<Integer>> graph;

    static void dfs(int node) {
        for (Integer val : graph.get(node)) {
            if (visit[val]) continue;
            visit[val] = true;
            dfs(val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visit = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            if (visit[i]) continue;
            dfs(i);
            answer++;
        }

        System.out.println(answer);
    }
}
