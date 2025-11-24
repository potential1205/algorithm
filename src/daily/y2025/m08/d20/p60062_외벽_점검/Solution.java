package daily.y2025.m08.d20.p60062_외벽_점검;

import java.util.*;

class Solution {

    public int solution(int n, int[] weak, int[] dist) {
        int weakLen = weak.length;
        int distLen = dist.length;

        // 원형 배열 평탄화
        int[] flatWeak = new int[weakLen * 2];
        for (int i = 0; i < weakLen; i++) {
            flatWeak[i] = weak[i];
            flatWeak[weakLen + i] = weak[i] + n;
        }

        // dist 내림차순 (최소 보장을 위해 필수)
        Arrays.sort(dist);
        for (int i = 0; i < distLen / 2; i++) {
            int temp = dist[i];
            dist[i] = dist[distLen - 1 - i];
            dist[distLen - 1 - i] = temp;
        }

        // 비트마스크 (id번째 작업자가 i번째 취약점부터 점검하는 경우 확인할 수 있는 취약점의 비트마스크 값)
        int[][] cover = new int[distLen][weakLen];
        for (int id = 0; id < distLen; id++) {
            for (int i = 0; i < weakLen; i++) {
                int idx = i;
                int x = flatWeak[i] + dist[id];
                while (idx < i + weakLen && flatWeak[idx] <= x) {
                    idx++;
                }

                int mask = 0;
                for (int j = i; j < idx; j++) {
                    mask |= (1 << (j % weakLen));
                }
                cover[id][i] = mask;
            }
        }

        // 작업자를 늘려가며 모든 취약점을 방문할 수 있는 경우를 탐색
        // 작업자 수가 결정되면 가능한 모든 경우의 수(mask)를 탐색해 다음 마스크(nextMask) 업데이트
        int FULL = (1 << weakLen) - 1;
        boolean[] isPossible = new boolean[FULL + 1];
        isPossible[0] = true;

        for (int id = 0; id < distLen; id++) {
            boolean[] next = Arrays.copyOf(isPossible, FULL + 1);

            for (int mask = 0; mask <= FULL; mask++) {
                // id - 1 명일 때 mask가 불가능한 상태라면 무시
                if (!isPossible[mask]) continue;

                // id - 1 명일 때 mask가 가능한 상태였다면 해당 상태에 현재 상태를 추가
                for (int i = 0; i < weakLen; i++) {
                    int nextMask = mask | cover[id][i];
                    next[nextMask] = true;
                }
            }

            isPossible = next;
            if (isPossible[FULL]) return id + 1;
        }

        return -1;
    }
}
