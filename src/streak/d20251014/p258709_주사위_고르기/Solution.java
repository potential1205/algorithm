package streak.d20251014.p258709_주사위_고르기;

import java.util.*;

class Solution {

    static int n, k;
    static int[] values;
    static List<int[]> candidates = new ArrayList<>();

    static void combinations(int depth, int start) {
        if (depth == k) {
            candidates.add(values.clone());
            return;
        }

        for (int i = start; i < n; i++) {
            values[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }

    static void dfs(int depth, int cum, int[][] dice, List<Integer> sumListA, int[] candidate) {
        if (depth == k) {
            sumListA.add(cum);
            return;
        }

        for (int val : dice[candidate[depth]]) {
            dfs(depth + 1, cum + val, dice, sumListA, candidate);
        }
    }

    static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
                result = mid;
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
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public int[] solution(int[][] dice) {
        n = dice.length;
        k = n / 2;
        values = new int[k];
        int[] bestPick = new int[k];
        int bestWin = 0;
        int bestTie = 0;

        // 주사위 선택 경우의 수 구하기
        combinations(0, 0);

        for (int[] candidateA : candidates) {
            List<Integer> sumListA = new ArrayList<>();
            dfs(0, 0, dice, sumListA, candidateA);

            boolean[] selA = new boolean[n];
            for (int idx : candidateA) {
                selA[idx] = true;
            }

            int idx = 0;
            int[] candidateB = new int[k];
            for (int i = 0; i < n; i++) {
                if (!selA[i]) {
                    candidateB[idx++] = i;
                }
            }

            List<Integer> sumListB = new ArrayList<>();
            dfs(0, 0, dice, sumListB, candidateB);

            Collections.sort(sumListA);
            Collections.sort(sumListB);

            int win = 0;
            int tie = 0;

            for (int valA : sumListA) {
                int lo = lowerBound(sumListB, valA);
                int hi = upperBound(sumListB, valA);
                win += lo;
                tie += (hi - lo);
            }

            if (bestWin < win || (bestWin == win && bestTie < tie)) {
                bestWin = win;
                bestTie = tie;
                bestPick = candidateA.clone();
            }
        }

        for (int i = 0; i < k; i++) {
            bestPick[i]++;
        }

        return bestPick;
    }
}