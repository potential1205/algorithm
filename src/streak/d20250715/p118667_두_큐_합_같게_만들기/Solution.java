package streak.d20250715.p118667_두_큐_합_같게_만들기;

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long total1 = 0;
        long total2 = 0;
        int size1 = queue1.length;
        int size2 = queue2.length;

        Deque<Integer> list1 = new ArrayDeque<>();
        Deque<Integer> list2 = new ArrayDeque<>();

        for (int i = 0; i < size1; i++) {
            total1 += queue1[i];
            list1.offerLast(queue1[i]);
        }

        for (int i = 0; i < size2; i++) {
            total2 += queue2[i];
            list2.offerLast(queue2[i]);
        }

        while (true) {
            if (total1 == total2) {
                break;
            } else if ((size1 + size2) * 3 <= answer) {
                return -1;
            }

            if (total1 > total2) {
                int val = list1.pollFirst();
                list2.offerLast(val);
                total1 -= val;
                total2 += val;
            } else {
                int val = list2.pollFirst();
                list1.offerLast(val);
                total1 += val;
                total2 -= val;
            }

            answer++;
        }

        return answer;
    }
}