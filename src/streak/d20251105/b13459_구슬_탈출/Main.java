package streak.d20251105.b13459_구슬_탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] directions = new int[10];
    static char[][] board;
    static int n, m;
    static int answer;
    static int rsy, rsx;
    static int bsy, bsx;

    static void dfs(int depth) {
        if (depth == 10) {

            // 원본 배열 복사
            char[][] copyBoard = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            // 구슬 이동시켜보기
            int ry = rsy;
            int rx = rsx;
            int by = bsy;
            int bx = bsx;
            boolean redIsEnd = false;
            boolean blueIsEnd = false;

            for (int d : directions) {
                if (d == 0) { // 북쪽
                    if (ry < by) {

                        // 빨간공 이동
                        while (true) {
                            int ny = ry - 1;
                            if (copyBoard[ny][rx] == '#') break;
                            if (copyBoard[ny][rx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ny][rx] = 'R';
                            copyBoard[ry][rx] = '.';
                            ry = ny;
                        }

                        // 파란공 이동
                        while (true) {
                            int ny = by - 1;
                            if (copyBoard[ny][bx] == '#' || copyBoard[ny][bx] == 'R') break;
                            if (copyBoard[ny][bx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[ny][bx] = 'B';
                            copyBoard[by][bx] = '.';
                            by = ny;
                        }

                    } else {

                        // 파란공 이동
                        while (true) {
                            int ny = by - 1;
                            if (copyBoard[ny][bx] == '#') break;
                            if (copyBoard[ny][bx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[ny][bx] = 'B';
                            copyBoard[by][bx] = '.';
                            by = ny;
                        }

                        // 빨간공 이동
                        while (true) {
                            int ny = ry - 1;
                            if (copyBoard[ny][rx] == '#' || copyBoard[ny][rx] == 'B') break;
                            if (copyBoard[ny][rx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ny][rx] = 'R';
                            copyBoard[ry][rx] = '.';
                            ry = ny;
                        }
                    }
                } else if (d == 1) { // 동쪽
                    if (rx > bx) {

                        // 빨간공 이동
                        while (true) {
                            int nx = rx + 1;
                            if (copyBoard[ry][nx] == '#') break;
                            if (copyBoard[ry][nx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ry][nx] = 'R';
                            copyBoard[ry][rx] = '.';
                            rx = nx;
                        }

                        // 파란공 이동
                        while (true) {
                            int nx = bx + 1;
                            if (copyBoard[by][nx] == '#' || copyBoard[by][nx] == 'R') break;
                            if (copyBoard[by][nx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[by][nx] = 'B';
                            copyBoard[by][bx] = '.';
                            bx = nx;
                        }

                    } else {

                        // 파란공 이동
                        while (true) {
                            int nx = bx + 1;
                            if (copyBoard[by][nx] == '#') break;
                            if (copyBoard[by][nx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[by][nx] = 'B';
                            copyBoard[by][bx] = '.';
                            bx = nx;
                        }

                        // 빨간공 이동
                        while (true) {
                            int nx = rx + 1;
                            if (copyBoard[ry][nx] == '#' || copyBoard[ry][nx] == 'B') break;
                            if (copyBoard[ry][nx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ry][nx] = 'R';
                            copyBoard[ry][rx] = '.';
                            rx = nx;
                        }
                    }

                } else if (d == 2) {
                    if (ry > by) {

                        // 빨간공 이동
                        while (true) {
                            int ny = ry + 1;
                            if (copyBoard[ny][rx] == '#') break;
                            if (copyBoard[ny][rx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ny][rx] = 'R';
                            copyBoard[ry][rx] = '.';
                            ry = ny;
                        }

                        // 파란공 이동
                        while (true) {
                            int ny = by + 1;
                            if (copyBoard[ny][bx] == '#' || copyBoard[ny][bx] == 'R') break;
                            if (copyBoard[ny][bx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[ny][bx] = 'B';
                            copyBoard[by][bx] = '.';
                            by = ny;
                        }

                    } else {

                        // 파란공 이동
                        while (true) {
                            int ny = by + 1;
                            if (copyBoard[ny][bx] == '#') break;
                            if (copyBoard[ny][bx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[ny][bx] = 'B';
                            copyBoard[by][bx] = '.';
                            by = ny;
                        }

                        // 빨간공 이동
                        while (true) {
                            int ny = ry + 1;
                            if (copyBoard[ny][rx] == '#' || copyBoard[ny][rx] == 'B') break;
                            if (copyBoard[ny][rx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ny][rx] = 'R';
                            copyBoard[ry][rx] = '.';
                            ry = ny;
                        }
                    }

                } else if (d == 3) {
                    if (rx < bx) {

                        // 빨간공 이동
                        while (true) {
                            int nx = rx - 1;
                            if (copyBoard[ry][nx] == '#') break;
                            if (copyBoard[ry][nx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ry][nx] = 'R';
                            copyBoard[ry][rx] = '.';
                            rx = nx;
                        }

                        // 파란공 이동
                        while (true) {
                            int nx = bx - 1;
                            if (copyBoard[by][nx] == '#' || copyBoard[by][nx] == 'R') break;
                            if (copyBoard[by][nx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[by][nx] = 'B';
                            copyBoard[by][bx] = '.';
                            bx = nx;
                        }

                    } else {

                        // 파란공 이동
                        while (true) {
                            int nx = bx - 1;
                            if (copyBoard[by][nx] == '#') break;
                            if (copyBoard[by][nx] == 'O') {
                                copyBoard[by][bx] = '.';
                                blueIsEnd = true;
                                break;
                            }
                            copyBoard[by][nx] = 'B';
                            copyBoard[by][bx] = '.';
                            bx = nx;
                        }

                        // 빨간공 이동
                        while (true) {
                            int nx = rx - 1;
                            if (copyBoard[ry][nx] == '#' || copyBoard[ry][nx] == 'B') break;
                            if (copyBoard[ry][nx] == 'O') {
                                copyBoard[ry][rx] = '.';
                                redIsEnd = true;
                                break;
                            }
                            copyBoard[ry][nx] = 'R';
                            copyBoard[ry][rx] = '.';
                            rx = nx;
                        }
                    }
                }

                // 종료 조건 확인
                if (redIsEnd && !blueIsEnd) {
                    answer = 1;
                    return;
                } else if (redIsEnd && blueIsEnd) {
                    return;
                }
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (depth > 0 && directions[depth - 1] == i) continue;
            directions[depth] = i;
            dfs(depth + 1);
        }
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
                    rsy = i;
                    rsx = j;
                } else if (board[i][j] == 'B') {
                    bsy = i;
                    bsx = j;
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }
}
