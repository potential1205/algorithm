package daily.y2025.m07.d08.p42626_더_맵게;

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int cnt = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);

            if (scoville[i] >= K) {
                cnt++;
            }
        }

        while (!pq.isEmpty()) {
            if (cnt == pq.size()) {
                break;
            }

            if (pq.size() == 1) {
                answer = - 1;
                break;
            }

            int val1 = pq.poll();
            if (val1 >= K) {
                cnt--;
            }

            int val2 = pq.poll();
            if (val2 >= K) {
                cnt--;
            }

            int newScoville = val1 + val2 * 2;
            pq.offer(newScoville);

            if (newScoville >= K) {
                cnt++;
            }

            answer++;
        }

        return answer;
    }
}