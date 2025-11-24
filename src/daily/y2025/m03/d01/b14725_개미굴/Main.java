package daily.y2025.m03.d01.b14725_개미굴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static Node root, point;

    static class Node {
        String data;
        TreeMap<String, Node> childMap;

        Node() {
            this.childMap = new TreeMap<>();
        }

        Node(String str) {
            this.data = str;
            this.childMap = new TreeMap<>();
        }

        Iterable<Node> getChildNodes() {
            return childMap.values();
        }

        void addChild(Node newChild) {
            this.childMap.put(newChild.data, newChild);
        }

        Node findNode(String str) {
            return childMap.get(str);
        }
    }

    static void recursion(Node node, int depth) {
        for (Node child : node.getChildNodes()) {

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