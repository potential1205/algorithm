package streak.d20250721.p92342_양궁대회;

class Try1 {

	static int maxVal;
	static int cnt;
	static int[] lionCount, peachCount;
	static int[] answer;
	static boolean[] active;

	static void dfs(int depth, int n) {
		if (depth == 11) {

			int leftCount = n;
			int needCount = 0;

			for (int i = 0; i <= 10; i++) {
				if (active[i]) {
					needCount += (peachCount[i] + 1);
				}
			}

			if (leftCount < needCount) {
				return;
			}

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

			if (maxVal < (lionScore - peachScore)) {
				maxVal = lionScore - peachScore;

				answer = new int[11];
				for (int i = 0; i <= 10; i++) {
					if (active[i]) {
						answer[i] = (peachCount[i] + 1);
						answer[10] = leftCount - needCount;
					}
				}
			}

			return;
		}

		// 조건 분기
		active[depth] = false;
		dfs(depth + 1, n);

		active[depth] = true;
		dfs(depth + 1, n);
	}

	public int[] solution(int n, int[] info) {
		lionCount = new int[11];
		answer = new int[11];
		peachCount = info;
		active = new boolean[11];

		dfs(0, n);

		if (maxVal == 0) {
			return new int[]{-1};
		}

		System.out.println(maxVal);

		return answer;
	}
}