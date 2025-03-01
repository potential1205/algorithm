package streak.d20250301.b14725_개미굴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Node root, point;

    static class Node implements Comparable<Node>{
        String data;
        int childCount;
        List<Node> childList;

        Node() {
            this.childList = new ArrayList<>();
            this.childCount = 0;
        }

        Node(String data) {
            this.data = data;
            this.childList = new ArrayList<>();
            this.childCount = 0;
        }

        List<Node> getChildList() {
            return this.childList;
        }

        void addChild(Node newChild) {
            this.childList.add(newChild);
            this.childCount++;
            Collections.sort(childList);
        }

        Node findNode(String str) {
            for (Node node : childList) {
                if (node.data.equals(str)) {
                    return node;
                }
            }

            return null;
        }

        @Override
        public int compareTo(Node o) {
            return this.data.compareTo(o.data);
        }
    }

    static void recursion(Node node, int depth) {
        for (Node child : node.getChildList()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }

            System.out.println(child.data);

            recursion(child, depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        root = new Node();

        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int m = Integer.parseInt(st.nextToken());

            point = root;

            for (int j = 0; j < m; j++) {
                String str = st.nextToken();

                Node findNode = point.findNode(str);

                if (findNode == null) {
                    Node newNode = new Node(str);
                    point.addChild(newNode);
                    point = newNode;
                } else {
                    point = findNode;
                }
            }
        }

        recursion(root, 0);
    }
}