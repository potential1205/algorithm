package daily.y2025.m12.d04.p118669_등산코스_정하기;

import java.util.*;

class Solution {

    static List<List<Node>> graph;

    static class Node implements Comparable<Node>{
        int num;
        int intensity;

        Node(int num, int intensity) {
            this.num = num;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int c = paths[i][2];
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<Integer> isGate = new HashSet<>();
        HashSet<Integer> isSummit = new HashSet<>();

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
            isGate.add(gate);
        }

        for (int summit : summits) {
            isSummit.add(summit);
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (isSummit.contains(cur.num)) continue;
            if (cur.intensity > intensity[cur.num]) continue;

            for (Node next : graph.get(cur.num)) {
                if (isGate.contains(next.num)) continue;
                int nextIntensity = Math.max(cur.intensity, next.intensity); // cur.intentisy == intensity[cur.num]
                if (intensity[next.num] > nextIntensity) {
                    intensity[next.num] = nextIntensity;
                    pq.offer(new Node(next.num, intensity[next.num]));
                }
            }
        }

        int val1 = -1;
        int val2 = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for (int summit : summits) {
            if (val2 > intensity[summit]) {
                val1 = summit;
                val2 = intensity[summit];
            }
        }

        return new int[]{val1, val2};
    }
}

