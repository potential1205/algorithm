package streak.d20250805.p388352_비밀_코드_해독;

class Solution {
	static int answer;
	static int[] qMask;
	static int qSize;

	static void check(int[] sel, int[] ans) {
		int mask = 0;

		for (int val : sel) {
			mask |= 1 << (val - 1);
		}

		int cnt = 0;
		for (int i = 0; i < qSize; i++) {
			if (Integer.bitCount(mask & qMask[i]) == ans[i]) {
				cnt++;
			}
		}

		if (cnt == qSize) {
			answer++;
		}
	}

	static void dfs(int start, int depth, int[] sel, int n, int[] ans) {
		if (depth == 5) {
			check(sel, ans);
			return;
		}

		for (int i = start; i <= n; i++) {
			sel[depth] = i;
			dfs(i + 1, depth + 1, sel, n, ans);
		}
	}

	public int solution(int n, int[][] q, int[] ans) {
		qSize = q.length;
		qMask = new int[qSize];
		int[] sel = new int[5];

		for (int i = 0; i < qSize; i++) {
			int mask = 0;

			for (int val : q[i]) {
				mask |= 1 << (val - 1);
			}

			qMask[i] = mask;
		}

		dfs(1, 0, sel, n, ans);

		return answer;
	}
}