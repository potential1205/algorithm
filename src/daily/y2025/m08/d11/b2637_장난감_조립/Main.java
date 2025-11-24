package daily.y2025.m08.d11.b2637_장난감_조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

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
        int[] outDegree = new int[n+1];

        int[][] countArr = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int target = Integer.parseInt(st.nextToken());
            inDegree[target]++;
            outDegree[target]++;

            int source = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());
            countArr[source][target] = cnt;
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(new Node(i, 1));
            }
        }

        int[] answer = new int[n + 1];

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            System.out.println(node.num + " " + node.cnt);

//            if (inDegree[node.num] == 0) {
//                answer[node.num] += node.cnt;
//                continue;
//            }

            for (int i = 1; i <= n; i++) {
                if (countArr[node.num][i] != 0 && outDegree[i] > 0) {
                    outDegree[i]--;
                    queue.offer(new Node(i, node.cnt * countArr[node.num][i]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                System.out.println(i + " " + answer[i]);
            }
        }
    }
}
