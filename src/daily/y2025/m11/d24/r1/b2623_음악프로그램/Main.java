package daily.y2025.m11.d24.r1.b2623_음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] in;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        in = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 1; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                in[to]++;
                graph.get(before).add(to);
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.println(cur);

            for(int next : graph.get(cur)) {
                in[next]--;
                if (in[next] == 0) {
                    queue.offer(next);
                }
            }
        }

    }
}
