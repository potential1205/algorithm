package daily.y2025.m11.d17.b14722_우유_도시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    처음에는 bfs + dp 로 접근하려고 했으나 N이 최악의 경우 1000이므로 1000 x 1000 배열을 탐색하는 경우 시간초과가 발생한다고 판단함
    생각해보니 우유는 총 3종류이고 0, 1, 2 중 하나의 값이 결정되어 있고 순서대로 방문해야 하므로 %를 이용해서 마실 수 있는 우유인지 판단할 수 있다고 생각함
    예를 들어 [i - 1][j] -> [i][j]로 이동한다고 하면
    dp[i - 1][j]의 값이 2라는 것은 dp[i - 1][j]까지 방문하면서 최대로 마신 우유가 2개라는 뜻이고
    이제 바나나 우유를 마실 차례라는 의미임 (arr[i][j] % 3 == 2 인 경우)
    따라서 dp[i][j]를 갱신할 때는 max(dp[i][j], dp[i - 1][j]) 중 하나를 결정 한 후 arr[i][j] % 3 == 2 인 경우 dp[i][j] + 1을 더해주면됨
    이동은 남쪽 또는 동쪽으로만 가능하므로 dp[i][j] = max(dp[i][j], dp[i - 1][j], dp[i][j - 1]) 을 결정하면서 진행하면 자연스러움
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ...
 */

public class Main {

    static int n;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }

                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                if (dp[i][j] % 3 == arr[i][j]) {
                    dp[i][j]++;
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}
