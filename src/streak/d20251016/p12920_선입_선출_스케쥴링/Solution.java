package streak.d20251016.p12920_선입_선출_스케쥴링;

import java.util.*;

class Solution {

    public int solution(int n, int[] cores) {

        int coreCnt = cores.length;
        long left = 0;
        long right = 50000 * 10000;
        long endTime = 0;

        while (left <= right) {
            long mid = (left + right) / 2; // 작업을 다 처리할 수 있는 시간

            long total = 0; // mid 시간동안 처리할 수 있는 작업의 개수
            for (int i = 0; i < cores.length; i++) {
                total += (mid / cores[i]);
            }

            if (total >= (n - coreCnt)) {
                endTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int workCnt = 0;
        for (int i = 0; i < cores.length; i++) {
            workCnt += (endTime - 1) / cores[i];
        }

        int rest = n - coreCnt - workCnt;
        int cnt = 0;

        for (int i = 0; i < cores.length; i++) {
            if (endTime % cores[i] == 0) {
                cnt++;

                if (cnt == rest) {
                    return i + 1;
                }
            }
        }


        return -1;
    }
}