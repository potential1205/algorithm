package streak.d20250722.p64062_징검다리_건너기;

// 시간초과
class Try1 {

	static boolean isEnd(int[] stones, int n, int k) {

		int idx = 0;

		for (int i = idx; i < n - k; i++) {

			int cnt = 0;

			for (int j = 0; j < k; j++) {
				if (stones[i + j] == 0) {
					cnt++;
				}
			}

			if (cnt == k) {
				return true;
			}
		}

		return false;

	}

	public int solution(int[] stones, int k) {
		int answer = 0;
		int n = stones.length;

		while (true) {
			if (isEnd(stones, n, k)) {
				break;
			}

			for (int i = 0; i < n; i++) {
				if (stones[i] > 0) {
					stones[i]--;
				}
			}

			answer++;
		}

		return answer;
	}
}