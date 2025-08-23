package streak.d20250823.p12920_선입_선출;

import java.util.PriorityQueue;

class Timeout1 {

    static class Node implements Comparable<Node> {
        int id;
        int activeTime;

        Node(int id, int activeTime) {
            this.id = id;
            this.activeTime = activeTime;
        }

        @Override
        public int compareTo(Node o) {
            if (this.activeTime != o.activeTime) {
                return this.activeTime - o.activeTime;
            } else {
                return this.id - o.id;
            }
        }
    }

    public int solution(int n, int[] cores) {
        int answer = 0;

        int[] coreState = new int[cores.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < cores.length; i++) {
            pq.offer(new Node(i, 0));
        }

        int time = 0;
        while (true) {
            if (n == 0) {
                break;
            }

            while (n > 0 && !pq.isEmpty() && pq.peek().activeTime == time) {
                Node node = pq.poll();
                pq.offer(new Node(node.id, time + cores[node.id]));
                answer = node.id + 1;
                n--;
            }

            time++;
        }

        return answer;
    }
}