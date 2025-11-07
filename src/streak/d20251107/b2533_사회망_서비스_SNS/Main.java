package streak.d20251107.b2533_사회망_서비스_SNS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static boolean[] visit;
    static List<List<Integer>> graph = new ArrayList<>();

    static void dfs(int cur) {

        // 역행 방지를 위한 방문 처리
        if (visit[cur]) return;
        visit[cur] = true;

        // 자식 노드 방문
        for (int next : graph.get(cur)) {
            dfs(next);
        }

        // 현재 노드가 얼리어답터가 아닌 경우
        // 연결된 노드들이 모두 얼리어답터인 경우의 합
        dp[cur][0] = 0;
        for (int next : graph.get(cur)) {
            dp[cur][0] += dp[next][1];
        }

        // 현재 노드가 얼리어답터인 경우
        // 연결된 노드들이 얼리어답터여도 아니어도 됨, 더 값이 작은 경우를 합산함
        dp[cur][1] = 1;
        for (int next : graph.get(cur)) {
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // dp[i][0] : i번째 노드가 얼리어답터가 아니고, i번째 노드와 그 주변 노드까지의 영역에서 아이디어가 모두 전파되기 위해 필요한 최소 얼리어답터의 수
        // dp[i][1] :  i번째 노드가 얼리어답터이고, i번째 노드와 그 주변 노드까지의 영역에서 아이디어가 모두 전파되기 위해 필요한 최소 얼리어답터의 수
        dp = new int[n + 1][2];
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        /*
            그래프는 무방향 그래프이므로 양쪽 모두 연결해줘야함
            대신 루트 트리처럼 방향을 정하기 위해 1부터 시작해서 visit처리로 관리하도록 설계함
            그래야 연결 고리가 하나인 노드부터 최소값을 차례로 갱신해나갈 수 있음
            시작 지점은 어떤 번호든 가능함
         */
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
