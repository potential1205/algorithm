package streak.d20250922.p388352_비밀_코드_해독;

import java.util.*;

class Solution {
    static int size;
    static int answer;
    static int[] qMask;
    static int[] value = new int[5];

    static void dfs(int n, int depth, int start, int[] ans) {
        if (depth == 5) {
            int mask = 0;
            for (int val : value) {
                mask |= (1 << (val - 1));
            }

            int cnt = 0;
            for (int i = 0; i < size; i++) {
                if (Integer.bitCount(mask & qMask[i]) == ans[i]) {
                    cnt++;
                }
            }

            if (cnt == size) {
                answer++;
            }

            return;
        }

        for (int i = start;  i <= n; i++) {
            value[depth] = i;
            dfs(n, depth + 1, i + 1, ans);
        }
    }

    public int solution(int n, int[][] q, int[] ans) {
        size = q.length;
        qMask = new int[size];

        for (int i = 0; i < size; i++) {
            int mask = 0;

            for (int val : q[i]) {
                mask |= 1 << (val - 1);
            }

            qMask[i] = mask;
        }

        dfs(n, 0, 1, ans);

        return answer;
    }
}