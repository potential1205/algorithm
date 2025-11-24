package daily.y2025.m09.d09.p258709_주사위_고르기;

import java.util.*;

class Solution {
    static int n, k;
    static int[] value;
    static List<int[]> candidates = new ArrayList<>();

    static void combinations(int depth, int start) {
        if (depth == k) {
            candidates.add(value.clone());
            return;
        }

        for (int i = start; i < n; i++) {
            value[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }

    static void dfs(List<Integer> sumList, int depth, int len, int cum, int[] candidate, int[][] dice) {
        if (depth == len) {
            sumList.add(cum);
            return;
        }

        for (int val : dice[candidate[depth]]) {
            dfs(sumList, depth + 1, len, cum + val, candidate, dice);
        }
    }

    static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public int[] solution(int[][] dice) {
        int[] answer = {};
        n = dice.length;
        k = n / 2;
        value = new int[k];
        combinations(0, 0);

        long bestWin = -1;
        long bestTie = -1;
        int[] bestPick = new int[k];

        for (int[] candidate : candidates) {
            List<Integer> sumListA = new ArrayList<>();
            dfs(sumListA, 0, k, 0, candidate, dice);

            boolean[] choice = new boolean[n];
            for (int idx : candidate) {
                choice[idx] = true;
            }

            int idx = 0;
            int[] other = new int[k];
            for (int i = 0; i < n; i++) {
                if (!choice[i]) {
                    other[idx++] = i;
                }
            }

            List<Integer> sumListB = new ArrayList<>();
            dfs(sumListB, 0, k, 0, other, dice);
            Collections.sort(sumListB);

            long win = 0;
            long tie = 0;
            for (int a : sumListA) {
                int lo = lowerBound(sumListB, a);
                int hi = upperBound(sumListB, a);
                win += lo;
                tie += (hi - lo);
            }

            if (win > bestWin || (win == bestWin && tie > bestTie)) {
                bestWin = win;
                bestTie = tie;
                bestPick = candidate.clone();
            }
        }

        for (int i = 0; i < k; i++) {
            bestPick[i]++;
        }

        return bestPick;
    }
}