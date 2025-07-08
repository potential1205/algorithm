package streak.d20250708.p42862_체육복;

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        Set<Integer> lostSet = new HashSet<>();
        for (int num : lost) {
            lostSet.add(num);
        }

        Set<Integer> reserveSet = new HashSet<>();
        for (int num : reserve) {
            if (lostSet.contains(num)) {
                lostSet.remove(num);
            } else {
                reserveSet.add(num);
            }
        }

        for (int val : reserveSet) {
            if (lostSet.contains(val - 1)) {
                lostSet.remove(val - 1);
                answer++;
                continue;
            }

            if (lostSet.contains(val + 1)) {
                lostSet.remove(val + 1);
                answer++;
                continue;
            }
        }

        if (lostSet.size() == 0) {
            return n;
        } else {
            return n - lostSet.size();
        }
    }
}