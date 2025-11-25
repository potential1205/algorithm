package daily.y2025.m11.d25.r2.b2623_음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] in = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int before = -1;

            for (int j = 0; j < cnt; j++) {
                int val = Integer.parseInt(st.nextToken());

                if (j > 0) {
                    graph.get(before).add(val);
                    in[val]++; // 진입 차수 저장
                }

                before = val;
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) queue.offer(i);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append("\n");
            cnt++;

            for (int next : graph.get(cur)) {
                in[next]--;
                if (in[next] == 0) queue.offer(next);
            }
        }

        if (cnt == n) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }


    }
}
