package daily.y2025.m12.d09.code_tree_배터리_효율_최적화_하기;

import java.util.*;

public class Main {

    static int n, m;
    static int answer;
    static int[][] board;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static ArrayList<Integer> modules = new ArrayList<>(); // 유효한 모듈의 비트마스크 저장
    static ArrayList<Integer> scores = new ArrayList<>(); // 각 모듈의 점수 저장

    static boolean isConnected(int mask) {
        // 시작점
        int start = -1;
        for (int i = 0; i < (n * m); i++) {
            if ((mask & (1 << i)) != 0) {
                start = i;
                break;
            }
        }

        if (start == -1) return false;

        // bfs로 연결확인
        int cnt = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] visit = new boolean[n * m];
        visit[start] = true;

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            cnt++;

            int y = idx / m;
            int x = idx % m;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                int nextIdx = ny * m + nx;
                if (visit[nextIdx] || ((mask & (1 << nextIdx)) == 0)) continue;
                queue.offer(nextIdx);
                visit[nextIdx] = true;
            }
        }

        return cnt == 5;
    }

    static int cal(int mask) {
        int sum = 0;
        for (int i = 0; i < (n * m); i++) {
            if ((mask & (1 << i)) != 0) {
                int y = i / m;
                int x = i % m;
                sum += board[y][x];
            }
        }

        return sum;
    }

    static void combinations(int depth, int idx, int mask) {
        if (depth == 5) {
            if (isConnected(mask)) {
                modules.add(mask);
                scores.add(cal(mask));
            }

            return;
        }

        if (idx >= (n * m)) return;

        // 모듈 추가
        combinations(depth + 1, idx + 1, mask | (1 << idx));

        // 모듈 스킵
        combinations(depth, idx + 1, mask);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        answer = Integer.MIN_VALUE;

        // 모든 경우의 수 구하기
        combinations(0, 0, 0);

        // 두 모듈 합 구하기
        int size = modules.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int mask1 = modules.get(i);
                int mask2 = modules.get(j);
                int score1 = scores.get(i);
                int score2 = scores.get(j);
                if (Integer.bitCount(mask1 & mask2) == 2) {
                    answer = Math.max(answer, score1 + score2);
                }
            }
        }

        System.out.println(answer);
    }
}