package streak.d20250818.p42891_무지의_먹방_라이브;

import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int num;
        int time;

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public int solution(int[] food_times, long k) {
        long total = 0;
        for (int foodTime : food_times) total += foodTime;
        if (total <= k) return -1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Node(i, food_times[i]));
        }

        long prev = 0L; // 누적 시간
        long cnt = food_times.length;

        while (!pq.isEmpty()) {
            Node node = pq.peek();

            long minTime = node.time;
            long timeGap = minTime - prev;

            if (timeGap == 0) { // 이미 제거 대상
                pq.poll();
                cnt--;
                continue;
            }

            long needTime = timeGap * cnt;

            if (k >= needTime) {
                k -= needTime;
                prev = minTime;
            } else {
                break;
            }
        }

        List<Node> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }

        list.sort(Comparator.comparingInt(node -> node.num));
        int idx = (int) (k % cnt);

        return list.get(idx).num + 1;
    }
}