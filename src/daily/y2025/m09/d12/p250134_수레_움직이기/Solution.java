package daily.y2025.m09.d12.p250134_수레_움직이기;

class Solution {
    static int n, m, answer;
    static int sry, srx, ery, erx;
    static int sby, sbx, eby, ebx;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void dfs(int depth, int ry, int rx, int by, int bx, boolean[][] visitRed, boolean[][] visitBlue, int[][] maze) {
        // 종료 조건 만족한 경우
        if (ry == ery && rx == erx && by == eby && bx == ebx) {
            answer = Math.min(answer, depth);
            return;
        }

        // 이미 최선이 아닌 경우
        if (depth >= answer) return;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int nry = ry;
                int nrx = rx;
                int nby = by;
                int nbx = bx;

                if (!(ry == ery && rx == erx)) {
                    nry = ry + dy[i];
                    nrx = rx + dx[i];
                }

                if (!(by == eby && bx == ebx)) {
                    nby = by + dy[j];
                    nbx = bx + dx[j];
                }

                if (nry < 0 || nrx < 0 || nry >= n || nrx >= m) continue;
                if (nby < 0 || nbx < 0 || nby >= n || nbx >= m) continue;
                if (maze[nry][nrx] == 5 || maze[nby][nbx] == 5) continue;
                if (nry == nby && nrx == nbx) continue;
                if (ry == nby && rx == nbx && by == nry && bx == nrx) continue;

                boolean rStay = (nry == ry && nrx == rx);
                boolean bStay = (nby == by && nbx == bx);

                if (!rStay && visitRed[nry][nrx]) continue;
                if (!bStay && visitBlue[nby][nbx]) continue;

                if (!rStay) visitRed[nry][nrx] = true;
                if (!bStay) visitBlue[nby][nbx] = true;
                dfs(depth + 1, nry, nrx, nby, nbx, visitRed, visitBlue, maze);
                if (!rStay) visitRed[nry][nrx] = false;
                if (!bStay) visitBlue[nby][nbx] = false;
            }
        }
    }

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    sry = i;
                    srx = j;
                } else if (maze[i][j] == 2) {
                    sby = i;
                    sbx = j;
                } else if (maze[i][j] == 3) {
                    ery = i;
                    erx = j;
                } else if (maze[i][j] == 4) {
                    eby = i;
                    ebx = j;
                }
            }
        }

        boolean[][] visitRed = new boolean[n][m];
        boolean[][] visitBlue = new boolean[n][m];
        visitRed[sry][srx] = true;
        visitBlue[sby][sbx] = true;
        dfs(0, sry, srx, sby, sbx, visitRed, visitBlue, maze);

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        return answer;
    }
}