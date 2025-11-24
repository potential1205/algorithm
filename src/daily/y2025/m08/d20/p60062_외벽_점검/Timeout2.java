package daily.y2025.m08.d20.p60062_외벽_점검;

import java.util.*;

class Timeout2 {
    static int N, M, K;
    static int answer = Integer.MAX_VALUE;
    static int[] visit;
    static int[] startPoint;

    static Set<Integer> visitSet;
    static Set<Integer> weakSet = new HashSet<>();

    static void play(int id, int start, int d, int[] dist) {

        if (d == 1) { // 시계방향
            int end = start + dist[id];

            for (int i = start; i <= end; i++) {
                visitSet.add(i % N);
            }

        } else if (d == 2) { // 반시계방향
            int end = start - dist[id];

            for (int i = start; i >= end; i--) {
                if (i >= 0) {
                    visitSet.add(i);
                } else {
                    visitSet.add(N + i);
                }
            }
        }

        return;
    }

    static void powerSet2(int depth, int[] dist) {
        if (depth == M) {

            visitSet = new HashSet<>();

            int idCnt = 0;
            for (int i = 0; i < M; i++) {
                if (visit[i] == 0) continue;
                idCnt++;
                if (answer != Integer.MAX_VALUE && idCnt >= answer) return;
                play(i, startPoint[i], visit[i], dist);
            }

            if (visitSet.containsAll(weakSet)) {
                if (idCnt != 0) {
                    answer = Math.min(answer, idCnt);
                }
            }

            return;
        }

        if (visit[depth] == 0) {
            startPoint[depth] = -1;
            powerSet2(depth + 1, dist);
            return;
        }

        for (int start : weakSet) {
            startPoint[depth] = start;
            powerSet2(depth + 1, dist);
        }
    }

    static void powerSet(int depth, int[] dist, int idCnt) {
        if (depth == M) {
            if (idCnt >= answer) return;
            powerSet2(0, dist);
            return;
        }

        visit[depth] = 0; // 참여하지 않음
        powerSet(depth + 1, dist, idCnt);

        visit[depth] = 1; // 시계방향 이동
        powerSet(depth + 1, dist, idCnt + 1);

        visit[depth] = 2; // 반시계방향 이동
        powerSet(depth + 1, dist, idCnt + 1);
    }

    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        M = dist.length;
        K = weak.length;
        visit = new int[M];
        startPoint = new int[M];

        for (int i = 0; i < K; i++) {
            weakSet.add(weak[i]);
        }

        Arrays.sort(dist);
        for (int i = 0, j = dist.length - 1; i < j; i++, j--) {
            int tmp = dist[i];
            dist[i] = dist[j];
            dist[j] = tmp;
        }

        powerSet(0, dist, 0);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }
}