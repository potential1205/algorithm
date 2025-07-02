package streak.d20250702.p43105_정수_삼각형;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;

        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i - 1][j] + triangle[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[n - 1][i]);
        }

        return answer;
    }
}