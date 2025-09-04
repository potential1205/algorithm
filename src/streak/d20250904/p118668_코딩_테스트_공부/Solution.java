package streak.d20250904.p118668_코딩_테스트_공부;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        int n = problems.length;

        for (int i = 0; i < n; i++) {
            int[] problem = problems[i];
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        if (alp >= maxAlp && cop >= maxCop) return 0;

        alp = Math.min(maxAlp, alp);
        cop = Math.min(maxCop, cop);

        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                dp[i][j] = 100000000;
            }
        }

        dp[alp][cop] = 0;

        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                if (dp[a][c] == 100000000) continue;

                // 알고 공부
                if (a + 1 <= maxAlp) {
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                }

                // 코딩 공부
                if (c + 1 <= maxCop) {
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                }

                // 문제 풀기
                for (int i = 0; i < n; i++) {
                    int[] problem = problems[i];

                    if (a >= problem[0] && c >= problem[1]) {
                        int nextA = Math.min(maxAlp, a + problem[2]); // 넘쳐도 maxAlp로 매핑
                        int nextC = Math.min(maxCop, c + problem[3]); // 넘쳐도 maxCop로 매핑
                        dp[nextA][nextC] = Math.min(dp[nextA][nextC], dp[a][c] + problem[4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}