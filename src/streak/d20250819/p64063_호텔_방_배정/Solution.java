package streak.d20250819.p64063_호텔_방_배정;

import java.util.*;

class Solution {
    static Map<Long, Long> visit = new HashMap<>();

    static long find(long num) {
        if (!visit.containsKey(num)) {
            visit.put(num, num + 1);
            return num;
        }

        long next = find(visit.get(num));
        visit.put(num, next);
        return next;
    }

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long num = room_number[i];

            if (visit.containsKey(num)) {
                long next = find(num);
                answer[i] = next;
            } else {
                visit.put(num, num + 1);
                answer[i] = num;
            }
        }

        return answer;
    }
}