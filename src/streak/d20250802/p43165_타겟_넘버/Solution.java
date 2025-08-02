package streak.d20250802.p43165_타겟_넘버;

class Solution {

	static int answer;

	static void dfs(int depth, int n, int[] numbers, int val, int target) {
		if (depth == n) {
			if (val == target) {
				answer++;
			}

			return;
		}

		dfs(depth + 1, n, numbers, val + numbers[depth], target);
		dfs(depth + 1, n, numbers, val - numbers[depth], target);
	}

	public int solution(int[] numbers, int target) {
		dfs(0, numbers.length, numbers, 0, target);

		return answer;
	}
}