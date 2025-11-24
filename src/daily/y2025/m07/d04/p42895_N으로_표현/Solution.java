package daily.y2025.m07.d04.p42895_N으로_표현;

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(new HashSet<>());

        for (int i = 1; i <= 8; i++) {
            Set<Integer> newSet = new HashSet<>();
            int num = Integer.parseInt(String.valueOf(N).repeat(i));
            newSet.add(num);

            for (int j = 1; j < i; j++) {
                for (int val1 : dp.get(j)) {
                    for (int val2 : dp.get(i - j)) {
                        newSet.add(val1 + val2);
                        newSet.add(val1 - val2);
                        newSet.add(val1 * val2);
                        if (val2 != 0) newSet.add(val1 / val2);
                    }
                }
            }

            dp.add(i, newSet);
        }

        for (int i = 1; i <= 8; i++) {
            Set<Integer> set = dp.get(i);

            for (int val : set) {
                if (val == number) {
                    return i;
                }
            }
        }

        return -1;
    }
}