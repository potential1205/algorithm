package streak.d20250912.p250134_수레_움직이기;

import java.util.*;

class MemoryLimit {
    static int n, m;
    static int sry, srx, ery, erx;
    static int sby, sbx, eby, ebx;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class State {
        int ry, rx;
        int by, bx;
        boolean[][] visitRed = new boolean[n][m];
        boolean[][] visitBlue = new boolean[n][m];

        State(){};
        State(State nextState) {
            this.ry = nextState.ry;
            this.rx = nextState.rx;
            this.by = nextState.by;
            this.bx = nextState.bx;
        }
        State(int ry, int rx, int by, int bx) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
        }

        public void visitRed(int y, int x) {
            this.visitRed[y][x] = true;
        }

        public void visitBlue(int y, int x) {
            this.visitBlue[y][x] = true;
        }
    }

    public int solution(int[][] maze) {
        int answer = 0;
        n = maze.length;
        m = maze[0].length;

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

        ArrayDeque<State> queue = new ArrayDeque<>();
        State initState = new State(sry, srx, sby, sbx);
        initState.visitRed(sry, srx);
        initState.visitBlue(sby, sbx);
        queue.offer(new State(sry, srx, sby, sbx));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            // red 먼저 움직이기
            for (int i = 0; i < 4; i++) {
                int nry = cur.ry + dy[i];
                int nrx = cur.rx + dx[i];

                if (nry < 0 || nrx < 0 || nry >= n || nrx >= m || maze[nry][nrx] == 0) continue;
                //if (cur.by == nry && cur.bx == nrx) continue;
                if (cur.visitRed[nry][nrx]) continue;

                for (int j = 0; j < 4; j++) {
                    int nby = cur.by + dy[i];
                    int nbx = cur.bx + dx[i];

                    if (nby < 0 || nbx < 0 || nby >= n || nbx >= m || maze[nby][nbx] == 0) continue;
                    if (nby == nry && nbx == nrx) continue;
                    if (cur.visitBlue[nby][nbx]) continue;

                    State nextState = new State();
                    nextState.ry = nry;
                    nextState.rx = nrx;
                    nextState.by = nby;
                    nextState.bx = nbx;
                    nextState.visitRed(nry, nrx);
                    nextState.visitBlue(nby, nbx);
                    queue.offer(nextState);
                }
            }



            // blue 먼저 움직이기
        }


        return answer;
    }
}