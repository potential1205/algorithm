package streak.d20250711.p43164_여행경로;

import java.util.*;

class Try2 {
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
        int idx = 1;

        String[] answer = new String[n + 1];
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (String[] strArr : tickets) {
            graph.put(strArr[0], new PriorityQueue<>());
            graph.put(strArr[1], new PriorityQueue<>());
        }

        for (String[] strArr : tickets) {
            graph.get(strArr[0]).offer(strArr[1]);
        }


        String key = "ICN";

        answer[0] = "ICN";

        while (true) {

            PriorityQueue<String> pq = graph.get(key);

            if (!pq.isEmpty()) {
                String str = pq.poll();
                System.out.println(str);
                key = str;
                answer[idx++] = str;
                System.out.println(str);
            } else {
                break;
            }
        }


        return answer;
    }
}