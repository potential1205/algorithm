package streak.d20250710.p42897_도둑질;

class Solution {
    public int solution(int[] money) {
        int answer = 0;

        int n = money.length;

        int[] dp = new int[n];
        dp[0] = money[0];
        dp[1] = Math.max(dp[0], money[1]);

        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        dp = new int[n];
        dp[0] = 0;
        dp[1] = money[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}