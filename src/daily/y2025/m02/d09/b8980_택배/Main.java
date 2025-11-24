package daily.y2025.m02.d09.b8980_택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, C, M ,answer;

    static class Node implements Comparable<Node> {
        int a,b,c;

        Node(int a, int b, int c) {
            this.a = a; // start
            this.b = b; // end
            this.c = c; // amount
        }

        @Override
        public int compareTo(Node o) {
            int gap1 = this.a - o.a;

            if (gap1==0) {
                int gap2 = this.b - o.b;
                if (gap2==0) {
                    return this.c - o.c;
                } else {
                    return gap2;
                }
            } else {
                return gap1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            queue.offer(new Node(a,b,c));
        }

        int store = 0;
        int[] posts = new int[N+1];

        for (int t = 1; t <= N; t++) {

            // 배송
            for (int j = 1; j <= N; j++) {
                if (t==j) {
                    answer += posts[j];
                    store -= posts[j];
                    posts[j] = 0;
                }
            }

            // 적재
            while(!queue.isEmpty() && queue.peek().a == t) {
                Node node = queue.poll();
                posts[node.b] += node.c;
                store += node.c;
            }

            // 버리기
            int idx = N;
            while(store > C) {
                if (posts[idx]!=0) {
                    posts[idx]--;
                    store--;
                } else {
                    idx--;
                }
            }
        }

        System.out.println(answer);
    }
}
