package streak.d20250822.p389480_완전범죄;

class Solution {

    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        int INF = Integer.MAX_VALUE;

        int[][] dp = new int[size + 1][m]; // i번째까지 물건을 훔쳤고, B가 남긴 흔적이 b일 때의 A가 남긴 흔적의 최소값
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;

        for (int i = 0; i < size; i++) {
            for (int b = 0; b < m; b++) {
                if (dp[i][b] == INF) continue;

                int nextA = dp[i][b] + info[i][0];
                int nextB = b + info[i][1];

                // A가 i번째 물건을 훔칠경우
                if (nextA < n) {
                    dp[i + 1][b] = Math.min(dp[i + 1][b], nextA);
                }

                // B가 i번째 물건을 훔칠경우
                if (nextB < m) {
                    dp[i + 1][nextB] = Math.min(dp[i + 1][nextB], dp[i][b]);
                }
            }
        }

        int answer = INF;
        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[size][i]);
        }

        if (answer == INF) {
            answer = -1;
        }

        return answer;
    }
}
