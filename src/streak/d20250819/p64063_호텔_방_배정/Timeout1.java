package streak.d20250819.p64063_호텔_방_배정;

import java.util.*;

class Timeout1 {

    static long lower(long target) {
        long left = 0;
        long right = (int) Math.pow(10, 12);
        long result = (int) Math.pow(10, 12);

        while (left <= right) {
            long mid = (left + right) / 2;

            if (mid > target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        Set<Long> visit = new HashSet<>();
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long num = room_number[i];

            if (visit.contains(num)) {
                long result = num;
                while (true) {
                    result = lower(result);

                    if (visit.contains(result)) {
                        continue;
                    } else {
                        break;
                    }
                }

                visit.add(result);
                answer[i] = result;
            } else {
                visit.add(num);
                answer[i] = num;
            }
        }

        return answer;
    }
}