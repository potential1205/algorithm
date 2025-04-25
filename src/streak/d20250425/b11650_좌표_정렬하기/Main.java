package streak.d20250425.b11650_좌표_정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Node implements Comparable<Node> {
        int a;
        int b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            int gap1 = this.a - o.a;

            if (gap1 == 0) {
                return this.b - o.b;
            }

            return gap1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            Node node = new Node(a, b);
            list.add(node);
        }

        Collections.sort(list);

        for (Node node : list) {
            System.out.println(node.a + " " + node.b);
        }
    }
}
