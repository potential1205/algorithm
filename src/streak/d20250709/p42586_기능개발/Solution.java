package streak.d20250709.p42586_기능개발;

import java.util.*;

class Solution {

    static class Node {
        int speed;
        int progress;

        Node(int speed, int progress) {
            this.speed = speed;
            this.progress = progress;
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        int n = progresses.length;

        for (int i = 0; i < n; i++) {
            stack.push(new Node(speeds[n - 1 - i], progresses[n - 1 -i]));
        }

        while (!stack.isEmpty()) {
            int cnt = 0;

            while (!stack.isEmpty() && stack.peek().progress >= 100) {
                Node node = stack.pop();
                cnt++;
            }

            answer.add(cnt);

            for (int i = 0; i < stack.size(); i++) {
                Node node = stack.get(i);
                stack.set(i, new Node(node.speed, node.progress + node.speed));
            }
        }

        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i) != 0) {
                temp.add(answer.get(i));
            }
        }

        return temp.stream().mapToInt(Integer::intValue).toArray();
    }
}