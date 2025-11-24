package daily.y2025.m10.d30.b2169_로봇_조종하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*



 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫번째 행의 dp 값은 이미 결정되어 있음 (왼 -> 오)
        int[][] dp = new int[n][m];
        dp[0][0] = board[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + board[0][i];
        }

        // 두번째 행부터 dp 값 업데이트 진행
        for (int i = 1; i < n; i++) {

            // 핵심은 왼 -> 오, 오 -> 왼 두 경우를 모두 구해주는 것
            int[] left = new int[m];
            int[] right = new int[m];

            // 해당 행의 가장 왼쪽과 오른쪽 값은 바로 계산할 수 있음 (임시 값)
            // left : 왼쪽에서 오른쪽으로 이동하며 갱신하는 값
            // right : 오른쪽에서 왼쪽으로 이동하며 갱신하는 값
            left[0] = dp[i - 1][0] + board[i][0];
            right[m - 1] = dp[i - 1][m - 1] + board[i][m - 1];

            // (i, j) 기준 위쪽(i-1, j)과 왼쪽(i,j-1)에서 오는 값 중 큰 값으로 결정
            // 이 때 왼쪽에서 오는 값은 dp[i][j - 1] 이 아니라 left[j - 1]이어야 함
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(left[j - 1] + board[i][j], dp[i - 1][j] + board[i][j]);
            }

            // (i, j) 기준 위쪽(i-1, j)과 오른쪽(i,j+1)에서 오는 값 중 큰 값으로 결정
            // 마찬가지로 오른쪽에서 오는 값은 dp[i][j + 1]이 아니라 right[j + 1]이어야 함
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] + board[i][j], dp[i - 1][j] + board[i][j]);
            }

            // 그래서 어떤 방향에서 진행한 dp 값이 최대인지 결정
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
