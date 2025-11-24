package daily.y2025.m11.d05.b13459_구슬_탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main3 {
    static char[][] board;
    static int n, m;
    static int ry, rx, by, bx;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class State {
        int ry, rx;
        int by, bx;
        int depth;

        State(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }

    static class Result {
        int y, x;
        int moveCnt;
        boolean inHole;

        public Result(int y, int x, int moveCnt, boolean inHole) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
            this.inHole = inHole;
        }
    }

    static Result move(int sy, int sx, int d) {
        int y = sy;
        int x = sx;
        int moveCnt = 0;

        while (true) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            char ch = board[ny][nx];

            if (ch == '#') break;
            if (ch == 'O') {
                return new Result(ny, nx, moveCnt + 1, true);
            }

            y = ny;
            x = nx;
            moveCnt++;
        }

        return new Result(y, x, moveCnt,false);
    }

    static int bfs() {
        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(ry, rx, by, bx, 0));

        boolean[][][][] visit = new boolean[n][m][n][m];
        visit[ry][rx][by][bx] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.depth == 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                Result resultRed = move(cur.ry, cur.rx, i);
                Result resultBlue = move(cur.by, cur.bx, i);

                if (resultBlue.inHole) continue;
                if (resultRed.inHole) return 1;

                // 같은 위치에 겹쳤다면
                if (resultRed.y == resultBlue.y && resultRed.x == resultBlue.x) {
                    if (resultRed.moveCnt > resultBlue.moveCnt) {
                        resultRed.y -= dy[i];
                        resultRed.x -= dx[i];
                    } else {
                        resultBlue.y -= dy[i];
                        resultBlue.x -= dx[i];
                    }
                }

                // 방문하지 않은 상태라면
                if (!visit[resultRed.y][resultRed.x][resultBlue.y][resultBlue.x]) {
                    visit[resultRed.y][resultRed.x][resultBlue.y][resultBlue.x] = true;
                    queue.offer(new State(resultRed.y, resultRed.x, resultBlue.y, resultBlue.x, cur.depth + 1));
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
