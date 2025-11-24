package daily.y2025.m03.d08;
import java.util.*;

public class Main {
    static class Node {
        int value;
        Node left, right;


        Node(int value){
            this.left = null;
            this.right = null;
            this.value = value;
        };


        void addNode(Node child){
            if (this.value > child.value){
                if(this.left == null){
                    this.left = child;
                } else {
                    this.left.addNode(child);
                }
            } else {
                if(this.right == null){
                    this.right = child;
                } else {
                    this.right.addNode(child);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        Node root = new Node(sc.nextInt());

        while(sc.hasNextInt()){
            root.addNode(new Node(sc.nextInt()));
            System.out.println("hi");
        }
        System.out.println("done");
        postOrder(root);
    }

    static void postOrder(Node root){

        if(root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.value);
    }

}
