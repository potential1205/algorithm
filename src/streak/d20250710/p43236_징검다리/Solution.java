package streak.d20250710.p43236_징검다리;

import java.util.*;

class Solution {

    static int getCount(int[] rocks, int mid, int distance) {
        int before = 0;
        int cnt = 0;

        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - before < mid) {
                cnt++;
                continue;
            }

            before = rocks[i];
        }

        if (distance - before < mid) cnt++;

        return cnt;
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int answer = 0;
        int start = 1;
        int end = distance;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (getCount(rocks, mid, distance) <= n) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return answer;
    }
}