package daily.y2025.m11.d22.b1949_우수_마을;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] costs;
    static int[][] dp;
    static boolean[] visit;
    static List<List<Integer>> graph;

    static void dfs(int cur) {
        if (visit[cur]) return;
        visit[cur] = true;

        for (int next : graph.get(cur)) {
            dfs(next);
        }

        // cur가 우수 마을인 경우
        dp[cur][1] = costs[cur];
        for (int next : graph.get(cur)) {
            dp[cur][1] += dp[next][0];
        }

        // cur가 우수 마을이 아닌 경우
        dp[cur][0] = 0;
        int cnt = 0;
        int val = 0;

        for (int next : graph.get(cur)) {
            dp[cur][0] += Math.max(dp[next][0], dp[next][1]);

            if (dp[next][0] < dp[next][1]) {
                cnt++;
            }

            if (val < (dp[next][1] - dp[next][0])) {
                val = dp[next][1] - dp[next][0];
            }
        }

        if (cnt == 0) {
            dp[cur][1] += (val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        costs = new int[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}
