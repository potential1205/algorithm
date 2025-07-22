package streak.d20250722.p64062_징검다리_건너기;

class Solution {

	static int answer;

	static void binarySearch(int[] stones, int n, int k) {

		int left = 1;
		int right = 200000000;

		while (left <= right) {
			int mid =  (left + right) / 2;

			int maxCnt = 0;
			int cnt = 0;

			for (int i = 0; i < n; i++) {
				if (stones[i] < mid) {
					cnt++;
					maxCnt = Math.max(maxCnt, cnt);
				} else {
					cnt = 0;
				}
			}

			if (maxCnt >= k) {
				answer = mid - 1;
				right = mid - 1;
			} else {
				answer = mid;
				left = mid + 1;
			}
		}
	}

	public int solution(int[] stones, int k) {
		int n = stones.length;
		binarySearch(stones, n, k);

		return answer;
	}
}