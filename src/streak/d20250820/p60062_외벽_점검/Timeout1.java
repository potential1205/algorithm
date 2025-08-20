package streak.d20250820.p60062_외벽_점검;

import java.util.*;

class Timeout1 {
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
                play(i, startPoint[i], visit[i], dist);
            }

            Set<Integer> temp = new HashSet<>(weakSet);
            temp.retainAll(visitSet);

            if (temp.equals(weakSet)) {
                if (idCnt != 0) {
                    answer = Math.min(answer, idCnt);
                }
            }

            return;
        }

        for (int start : weakSet) {
            if (visit[depth] == 0) {
                startPoint[depth] = -1;
            } else {
                startPoint[depth] = start;
            }
            powerSet2(depth + 1, dist);
        }
    }

    static void powerSet(int depth, int[] dist) {
        if (depth == M) {
            powerSet2(0, dist);
            return;
        }

        visit[depth] = 0; // 참여하지 않음
        powerSet(depth + 1, dist);

        visit[depth] = 1; // 시계방향 이동
        powerSet(depth + 1, dist);

        visit[depth] = 2; // 반시계방향 이동
        powerSet(depth + 1, dist);
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

        powerSet(0, dist);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }
}