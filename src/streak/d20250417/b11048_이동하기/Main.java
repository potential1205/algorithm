package streak.d20250417.b11048_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] dp;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = board[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int k = 0; k < 3; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (0 <= ny && ny < n && 0 <= nx && nx < m) {
                        if (dp[ny][nx] < dp[i][j] + board[ny][nx]) {
                            dp[ny][nx] = dp[i][j] +  board[ny][nx];
                        }
                    }
                }
            }
        }

        System.out.println(dp[n-1][m-1]);
    }
}
