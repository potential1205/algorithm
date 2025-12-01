package daily.y2025.m12.d01.r1.b13334_철로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int left, right;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.right - o.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            if (val1 < val2) {
                list.add(new Node(val1, val2));
            } else {
                list.add(new Node(val2, val1));
            }
        }

        Collections.sort(list);

        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Node node : list) {

            while (!queue.isEmpty() && node.right - d > queue.peek()) {
                queue.poll();
            }

            if (node.right - d <= node.left) {
                queue.offer(node.left);
            }

            answer = Math.max(answer, queue.size());
        }

        System.out.println(answer);
    }
}
