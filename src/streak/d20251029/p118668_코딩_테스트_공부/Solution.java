package streak.d20251029.p118668_코딩_테스트_공부;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        // 문제에서 요구하는 최대 알고력, 코딩력 저장
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // 만약 이미 내가 가진 알고력과 코딩력이 문제에서 요구하는 최대 알고력과 코딩력 이상이라면 모든 문제 해결 가능한 상태
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        // 내가 가진 알고력 또는 코딩력이 문제에서 요구하는 최대치보다 크다면 문제에서 요구하는 최대치로 한계 설정, 그 이상을 고려할 필요가 없기 때문
        alp = Math.min(maxAlp, alp);
        cop = Math.min(maxCop, cop);

        // [알고력][코딩력] 을 갖추는데 필요한 최단 시간을 저장할 dp 배열
        // 아직 달성할 수 없는 경우 100000으로 초기화 (최악의 경우에도 100000시간 미만으로 해결가능하므로)
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j] = 100000;
            }
        }
        dp[alp][cop] = 0;

        // 각 (코딩력, 알고력) 상태에 대해 dp 업데이트 진행
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (dp[i][j] == 100000) continue;

                // 현재 상태에서 알고력을 1만큼 향상하는 경우
                // [현재 알고력 + 1][현재 코딩력] 상태에 도달하기 위해 필요한 시간과 [현재 알고력][현재 코딩력] 상태에서 1만큼 시간 투자 하는 경우와 비교
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                // 알고력과 동일한 로직
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 문제 풀어보기
                for (int[] problem : problems) {

                    // [현재 알고력][현재 코딩력]으로 풀 수 있는 문제인지 확인
                    if (i >= problem[0] && j >= problem[1]) {

                        // [현재 알고력 + 문제를 풀어 얻을 알고력][현재 코딩력 + 문제를 풀어 얻을 코딩력]에 도달하는데 걸리는 시간을 기존 값과 비교
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