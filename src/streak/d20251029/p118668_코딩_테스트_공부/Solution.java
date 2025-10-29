package streak.d20251029.p118668_코딩_테스트_공부;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = 0;
        int maxCop = 0;

        // max값 구하기
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // 이미 모든 문제를 풀 수 있는 경우
        if (alp >= maxAlp && cop >= maxCop) return 0;

        // 최대 값 제한
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        // dp 초기화 dp[i][j] : alp i이고 cop j가 되기 위해 필요한 시간
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                dp[i][j] = 10000;
            }
        }

        dp[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (dp[i][j] == 10000) continue; // 아직 풀 수 없는 상태면

                // 알고력 향상
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                // 코딩력 향상
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 풀 수 있는 문제 확인
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        int nextAlp = Math.min(maxAlp, i + problem[2]);
                        int nextCop = Math.min(maxCop, j + problem[3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}