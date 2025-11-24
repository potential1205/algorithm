package daily.y2025.m06.d18.p42628_이중우선순위큐;

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(); // 최소힙
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙
        int pq1Max = 0;
        int pq2Min = 0;

        for (String ope : operations) {
            String[] info = ope.split(" ");
            int val = Integer.parseInt(info[1]);

            if (info[0].equals("I")) {
                if (val <= pq1Max) {
                    pq1.offer(val);

                    if (pq1Max <= val) {
                        pq1Max = val;
                    }

                } else if (val >= pq2Min) {
                    pq2.offer(val);

                    if (pq2Min >= val) {
                        pq2Min = val;
                    }
                } else {
                    if (pq1.size() <= pq2.size()) {
                        pq1.offer(val);
                        pq1Max = val;
                    } else {
                        pq2.offer(val);
                        pq2Min = val;
                    }
                }
            } else if (info[0].equals("D") && info[1].equals("-1")) {
                pq1.poll();
            } else if (info[0].equals("D") && info[1].equals("1")) {
                pq2.poll();
            }

        }

        int[] answer = new int[2];


        if (pq1.isEmpty() && pq2.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else if (!pq1.isEmpty() && pq2.isEmpty()) {
            int val = pq1.poll();
            answer[0] = val;
            answer[1] = val;
        } else if (pq1.isEmpty() && !pq2.isEmpty()) {
            int val = pq2.poll();
            answer[0] = val;
            answer[1] = val;
        } else {
            answer[1] = pq1.poll();
            answer[0] = pq2.poll();
        }

        return answer;
    }
}