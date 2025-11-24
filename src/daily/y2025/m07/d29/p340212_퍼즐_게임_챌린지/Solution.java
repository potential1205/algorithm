package daily.y2025.m07.d29.p340212_퍼즐_게임_챌린지;

class Solution {

	public int solution(int[] diffs, int[] times, long limit) {
		int answer = Integer.MAX_VALUE;

		int left = 1;
		int right = 100000;
		int n = diffs.length;

		while (left <= right) {
			int mid = (left + right) / 2;

			long time = 0;

			for (int i = 0; i < n; i++) {
				int levelGap = mid - diffs[i];

				if (levelGap >= 0) {
					time += times[i];
				} else {
					if (i > 0) {
						time += times[i];
						time += (Math.abs(levelGap) * (times[i] + times[i - 1]));
					} else {
						time += times[i];
						time += (Math.abs(levelGap) * (times[i]));
					}
				}
			}

			if (time <= limit) {
				answer = Math.min(answer, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}
}