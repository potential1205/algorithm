package streak.d20251105.b13459_구슬_탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int[] directions = new int[10];
    static char[][] board;
    static int n, m;
    static int answer;
    static int rsy, rsx;
    static int bsy, bsx;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Ball {
        int y;
        int x;

        Ball(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean moveY(char[][] copyBoard, Ball ball, int d, char color) {
        while (true) {
            int ny = ball.y + dy[d];
            char nb = copyBoard[ny][ball.x];

            if (nb == 'O') {
                copyBoard[ball.y][ball.x] = '.';
                return true;
            }

            if (nb != '.') break;

            copyBoard[ny][ball.x] = color;
            copyBoard[ball.y][ball.x] = '.';
            ball.y = ny;
        }

        return false;
    }

    static boolean moveX(char[][] copyBoard, Ball ball, int d, char color) {
        while (true) {
            int nx = ball.x + dx[d];
            char nb = copyBoard[ball.y][nx];

            if (nb == 'O') {
                copyBoard[ball.y][ball.x] = '.';
                return true;
            }

            if (nb != '.') break;

            copyBoard[ball.y][nx] = color;
            copyBoard[ball.y][ball.x] = '.';
            ball.x = nx;
        }

        return false;
    }

    static void dfs(int depth) {
        if (answer == 1) return;
        if (depth == 10) {
            // 원본 배열 복사
            char[][] copyBoard = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            // 구슬 이동시켜보기
            Ball redBall = new Ball(rsy, rsx);
            Ball blueBall = new Ball(bsy, bsx);

            boolean redIsEnd = false;
            boolean blueIsEnd = false;

            for (int d : directions) {
                if (d == 0) { // 북쪽
                    if (redBall.y < blueBall.y) {
                        redIsEnd = moveY(copyBoard, redBall, d, 'R');
                        blueIsEnd = moveY(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                    } else {
                        blueIsEnd = moveY(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                        redIsEnd = moveY(copyBoard, redBall, d, 'R');
                    }
                } else if (d == 1) { // 동쪽
                    if (redBall.x > blueBall.x) {
                        redIsEnd = moveX(copyBoard, redBall, d, 'R');
                        blueIsEnd = moveX(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                    } else {
                        blueIsEnd = moveX(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                        redIsEnd = moveX(copyBoard, redBall, d, 'R');
                    }
                } else if (d == 2) {
                    if (redBall.y > blueBall.y) {
                        redIsEnd = moveY(copyBoard, redBall, d, 'R');
                        blueIsEnd = moveY(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                    } else {
                        blueIsEnd = moveY(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                        redIsEnd = moveY(copyBoard, redBall, d, 'R');
                    }
                } else if (d == 3) {
                    if (redBall.x < blueBall.x) {
                        redIsEnd = moveX(copyBoard, redBall, d, 'R');
                        blueIsEnd = moveX(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                    } else {
                        blueIsEnd = moveX(copyBoard, blueBall, d, 'B');
                        if (blueIsEnd) return;
                        redIsEnd = moveX(copyBoard, redBall, d, 'R');
                    }
                }

                // 종료 조건 확인
                if (redIsEnd && !blueIsEnd) {
                    answer = 1;
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
