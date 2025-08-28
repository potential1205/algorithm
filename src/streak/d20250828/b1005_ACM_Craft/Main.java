package streak.d20250828.b1005_ACM_Craft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int id;
        int time;

        Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return o.time - this.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] time = new int[n + 1];
            int[] in = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                time[i + 1] = Integer.parseInt(st.nextToken());
            }

            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Node(to, 0));
                in[to]++;
            }

            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());

            PriorityQueue<Node> queue = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                if (in[i] == 0) {
                    queue.offer(new Node(i, time[i]));
                }
            }

            int[] answer = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                answer[i] = time[i];
            }

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (Node next : graph.get(cur.id)) {
                    in[next.id]--;
                    answer[next.id] = Math.max(answer[next.id], cur.time + time[next.id]);

                    if (in[next.id] == 0) {
                        queue.offer(new Node(next.id, answer[next.id]));
                    }
                }
            }

            System.out.println(answer[target]);
        }
    }
}
