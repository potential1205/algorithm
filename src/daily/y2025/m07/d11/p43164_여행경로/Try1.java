package daily.y2025.m07.d11.p43164_여행경로;

import java.util.*;

class Try1 {
    static class Node implements Comparable<Node> {
        String str;

        Node(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Node o) {
            return this.str.compareTo(o.str);
        }
    }

    public String[] solution(String[][] tickets) {

        int n = tickets.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int startIdx = 0;
        String startStr = "ZZZZZZ";

        for (int i = 0; i < n; i++) {
            if (tickets[i][0].equals("ICN") && startStr.compareTo(tickets[i][0] + tickets[i][1]) > 0) {
                startIdx = i;
                startStr = tickets[i][0] + tickets[i][1];
            }
        }

        String[] answer = new String[n + 1];
        int idx = 2;

        answer[0] = tickets[startIdx][0];
        answer[1] = tickets[startIdx][1];

        for (int i = 0; i < n; i++) {
            if (i != startIdx) {
                pq.offer(new Node(tickets[i][0] + tickets[i][1]));
            }
        }


        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            answer[idx] = cur.str.substring(3, 6);
            idx++;
        }

        return answer;
    }
}