package daily.y2025.m12.d09.code_tree_배터리_효율_최적화_하기;

import java.util.Scanner;
public class Try1 {

    static int n, m;
    static int answer;
    static int[][] board;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void dfs(int depth, int sy1, int sx1, int sy2, int sx2, int val1, int val2) {
        if (depth == 3) {
            System.out.println(depth + " " + val1 + " " + val2 + " " + " " + sy1 + ":" + sx1 + " " + sy2 + ":" + sx2);
            answer = Math.max(answer, (val1 + val2));
            return;
        }

        System.out.println(depth + " " + val1 + " " + val2 + " " + " " + sy1 + ":" + sx1 + " " + sy2 + ":" + sx2);

        for (int i = 0; i < 4; i++) {
            int ny1 = sy1 + dy[i];
            int nx1 = sx1 + dx[i];
            if (ny1 < 0 || nx1 < 0 || ny1 >= n || nx1 >= m || visit[ny1][nx1]) continue;

            visit[ny1][nx1] = true;

            for (int j = 0; j < 4; j++) {
                int ny2 = sy2 + dy[j];
                int nx2 = sx2 + dx[j];
                if (ny2 < 0 || nx2 < 0 || ny2 >= n || nx2 >= m || visit[ny2][nx2]) continue;
                visit[ny2][nx2] = true;
                dfs(depth + 1, ny1, nx1, ny2, nx2, val1 + board[ny1][nx1], val2 + board[ny2][nx2]);
                visit[ny2][nx2] = false;
            }

            visit[ny1][nx1] = false;
        }

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

        // 시작점 경우의 수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int a = i; a < n; a++) {
                    for (int b = j; b < m; b++) {
                        if (i == a && j == b) continue;
                        System.out.println("시작점" + " " + i + ":" + j + " " + a + ":" + b);
                        visit = new boolean[n][m];
                        visit[i][j] = true;
                        visit[a][b] = true;
                        int val = (board[i][j] + board[a][b]);
                        dfs(0, i, j, a, b, val, val);
                        visit[i][j] = false;
                        visit[a][b] = false;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}