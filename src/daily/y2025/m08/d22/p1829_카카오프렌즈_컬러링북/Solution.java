package daily.y2025.m08.d22.p1829_카카오프렌즈_컬러링북;

import java.util.*;

class Solution {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visit;

    static int bfs(int sy, int sx, int n, int m, int[][] picture, int value) {
        int size = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sy, sx});
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            size++;

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] || picture[ny][nx] != value) continue;

                queue.offer(new int[]{ny, nx});
                visit[ny][nx] = true;
            }

        }

        return size;
    }

    public int[] solution(int n, int m, int[][] picture) {

        int answer = 0;
        int cnt = 0;
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (picture[i][j] == 0) continue;
                if (visit[i][j]) continue;

                int size = bfs(i, j, n, m, picture, picture[i][j]);
                answer = Math.max(answer, size);
                cnt++;
            }
        }

        return new int[]{cnt, answer};
    }
}