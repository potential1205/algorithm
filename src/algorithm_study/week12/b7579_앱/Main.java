package algorithm_study.week12.b7579_앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int totalCost;
    static int[] memoryArr, costArr;
    static int[][] dp;

    static void DP() {
        dp = new int[N+1][totalCost+1];

        // 첫번째까지 입력된 앱을 활용해 확보가능한 최대 메모리 크기 설정
        // 즉, 첫번째 앱이 주어진 비용을 초과하지 않고 비활성화 될 수 있는지 판단
        // 된다면 확보하개되는 메모리 크기 저장
        // => 하나의 앱이 비용을 만족할 때 확보되는 최대 메모리 크기는 자신의 메모리 크기와 동일함
        for (int j = 0; j <= totalCost; j++) {
            if (costArr[1] <= j) {
                dp[1][j] = memoryArr[1];
            }
        }

        for (int i = 1; i <= N; i++) { // 앱
            int memory = memoryArr[i];
            int cost = costArr[i];

            for (int j = 0; j <= totalCost; j++) { // 비용
                // 현재 선택한 앱을 비활성할 때 발생하는 비용이 한계 비용을 초과한다면
                // 이전 값(i-1번째까지 입력된 앱을 활용해 확보가능한 최대 메모리 크기) 그대로 적용
                if (j < cost) {
                    dp[i][j] = dp[i-1][j];
                } else { // 그렇지 않다면 이전 값과 i번째 앱을 비활성화할 때 확보가능한 메모리 크기를 비교하여 큰 값을 추가
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost] + memory);
                }
            }

            for (int j = 0; j <= totalCost; j++) {
                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                    break;
                }
            }
        }
    }

    static void solution() {
        answer = Integer.MAX_VALUE;
        DP();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memoryArr = new int[N+1];
        costArr = new int[N+1];

        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            memoryArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());
            totalCost += costArr[i];
        }

        solution();
    }
}
