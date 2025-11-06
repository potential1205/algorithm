package streak.d20251106.b13459_구슬_탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static int ry, rx, by, bx;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class State {
        int ry, rx, by, bx;
        int depth;

        public State(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }

    static class Result {
        int y, x, cnt;
        boolean inHole;

        public Result(int y, int x, int cnt, boolean inHole) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.inHole = inHole;
        }
    }

    static Result roll(int sy, int sx, int d) {
        int y = sy;
        int x = sx;
        int cnt = 0;

        while (true) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (board[ny][nx] == '#') {
                break;
            }

            if (board[ny][nx] == 'O') {
                return new Result(ny, nx, cnt + 1, true);
            }

            y = ny;
            x = nx;
            cnt++;
        }

        return new Result(y, x, cnt,false);
    }

    static int bfs() {

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(ry, rx, by, bx, 0));

        boolean[][][][] visit = new boolean[n][m][n][m];

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.depth >= 10) return 0;

            for (int i = 0; i < 4; i++) {
                Result redResult = roll(cur.ry, cur.rx, i);
                Result blueResult = roll(cur.by, cur.bx, i);

                if (blueResult.inHole) continue;
                if (redResult.inHole) return 1;

                if (redResult.y == blueResult.y && redResult.x == blueResult.x) {
                    if (redResult.cnt > blueResult.cnt) {
                        redResult.y -= dy[i];
                        redResult.x -= dx[i];
                    } else {
                        blueResult.y -= dy[i];
                        blueResult.x -= dx[i];
                    }
                }

                if (!visit[redResult.y][redResult.x][blueResult.y][blueResult.x]) {
                    visit[redResult.y][redResult.x][blueResult.y][blueResult.x] = true;
                    queue.offer(new State(redResult.y, redResult.x, blueResult.y, blueResult.x, cur.depth + 1));
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == 'R') {
                    ry = i;
                    rx = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    by = i;
                    bx = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs());
    }
}
