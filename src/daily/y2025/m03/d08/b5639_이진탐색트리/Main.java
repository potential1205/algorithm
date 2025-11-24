package daily.y2025.m03.d08.b5639_이진탐색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Node {
        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        public void addNode(Node newNode) {
            if (this.num > newNode.num) {
                if (this.left == null) {
                    this.left = newNode;
                } else {
                    this.left.addNode(newNode);
                }
            } else {
                if (this.right == null) {
                    this.right = newNode;
                } else {
                    this.right.addNode(newNode);
                }
            }
        }
    }

    static void postOrder(Node cur) {
        if(cur == null) return;

        postOrder(cur.left);
        postOrder(cur.right);
        System.out.println(cur.num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(bf.readLine()));

        while(true) {
            String temp = bf.readLine();

            if (temp == null || temp.equals("")) break;

            root.addNode(new Node(Integer.parseInt(temp)));
        }

        postOrder(root);
    }
}
