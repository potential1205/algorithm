package streak.d20250721.p92342_양궁대회;

class Solution {

	static int maxScoreGap;
	static int[] peachCount, lionCount;
	static int[] answer;
	static boolean[] active;

	static void dfs(int depth, int n) {
		if (depth == 11) {
			int needCount = 0;

			for (int i = 0; i <= 10; i++) {
				if (active[i]) {
					needCount += (peachCount[i] + 1);
				}
			}

			if (n < needCount) return;

			int restCount = n - needCount;
			int peachScore = 0;
			int lionScore = 0;

			for (int i = 0; i <= 10; i++) {
				if (active[i]) {
					lionScore += (10 - i);
				} else {
					if (peachCount[i] > 0) {
						peachScore += (10 - i);
					}
				}
			}

			int scoreGap = lionScore - peachScore;

			// 후보 배열 구하기
			int[] temp = new int[11];
			for (int i = 0; i <= 10; i++) {
				if (active[i]) {
					temp[i] = (peachCount[i] + 1);
				}
			}
			temp[10] = restCount;

			if (maxScoreGap < scoreGap) { // 점수 차이가 기존보다 더 큰 경우는 후보 배열로 정답 갱신
				maxScoreGap = scoreGap;
				answer = temp;
			} else if (maxScoreGap == scoreGap) { // 점수 차이가 기존과 같은 경우 기존 정답 배열과 후보 배열 중 누가 낮은 값이 더 많은지 비교
				for (int i = 10; i >= 0; i--) {
					if (temp[i] < answer[i]) break;

					if (temp[i] > answer[i]) {
						answer = temp;
						break;
					}
				}
			}

			return;
		}

		// 부분 집합
		active[depth] = false;
		dfs(depth + 1, n);

		active[depth] = true;
		dfs(depth + 1, n);
	}

	public int[] solution(int n, int[] info) {
		answer = new int[11];
		active = new boolean[11];
		lionCount = new int[11];
		peachCount = info;

		dfs(0, n);

		if (maxScoreGap == 0) {
			return new int[]{-1};
		}

		return answer;
	}
}