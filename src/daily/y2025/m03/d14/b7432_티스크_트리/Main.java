package daily.y2025.m03.d14.b7432_티스크_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int n;
    static Node root;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        Map<String, Node> child = new TreeMap<>();
    }

    static void search(Node cur, int depth) {
        for (String path : cur.child.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append(" ");
            }

            sb.append(path).append("\n");

            search(cur.child.get(path), depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        root = new Node();

        for (int i = 0; i < n; i++) {
            String[] paths = br.readLine().split("\\\\");

            Node cur = root;

            for (String path : paths) {
                cur.child.putIfAbsent(path, new Node());
                cur = cur.child.get(path);
            }
        }

        search(root, 0);

        System.out.print(sb);
    }
}
