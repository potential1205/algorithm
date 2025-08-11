package streak.d202508011.b2637_장난감_조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2 {

    static class Node {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[n+1];
        int[] reverseInDegree = new int[n+1];

        int[][] countArr = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int target = Integer.parseInt(st.nextToken());
            inDegree[target]++;

            int source = Integer.parseInt(st.nextToken());
            reverseInDegree[source]++;

            int cnt = Integer.parseInt(st.nextToken());
            countArr[source][target] = cnt;
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(n, 1));

        boolean[] visit = new boolean[n + 1];
        visit[n] = true;

        int[] answer = new int[n + 1];

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 1; i <= n; i++) {
                if (countArr[i][node.num] != 0) {
                    answer[i] += node.cnt * countArr[i][node.num];
                    reverseInDegree[i]--;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!visit[i] && reverseInDegree[i] == 0) {
                    visit[i] = true;
                    queue.offer(new Node(i, answer[i]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                sb.append(i).append(" ").append(answer[i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
