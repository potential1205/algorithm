package streak.d20250703.p42584_주식가격;

import java.util.*;

class Solution {

    static class Node {
        int price;
        int time;
        int index;

        Node(int price, int time, int index) {
            this.price = price;
            this.time = time;
            this.index = index;
        }
    }

    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Node newNode = new Node(prices[i], i + 1, i);

            while (!stack.isEmpty() && stack.peek().price > prices[i]) {
                Node node = stack.pop();
                answer[node.index] = newNode.time - node.time;
            }

            stack.push(newNode);
        }

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            answer[node.index] = n - node.time;
        }

        return answer;
    }
}