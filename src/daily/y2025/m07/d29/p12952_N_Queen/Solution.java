package daily.y2025.m07.d29.p12952_N_Queen;

class Solution {
	static int N;
	static int answer;
	static int[][] board;

	static boolean checkUp(int y, int x) {
		for (int i = 0; i <= y; i++) {
			if (board[y - i][x] == 1) {
				return false;
			}
		}

		return true;
	}

	static boolean checkLeftUp(int y, int x) {
		for (int i = 0; i <= y; i++) {
			if (x - i < 0) break;

			if (board[y - i][x - i] == 1) {
				return false;
			}
		}

		return true;
	}

	static boolean checkRightUp(int y, int x) {
		for (int i = 0; i <= y; i++) {
			if (x + i >= N) break;

			if (board[y - i][x + i] == 1) {
				return false;
			}
		}

		return true;
	}

	static void dfs(int row) {
		if (row == N) {
			answer++;
			return;
		}

		for (int col = 0; col < N; col++) {
			// board[row][col]에 퀸 놓기 전 체크

			// 위쪽
			if (!checkUp(row, col)) {
				continue;
			}

			// 대각선 좌측 위
			if (!checkLeftUp(row, col)) {
				continue;
			}

			// 대각선 우측 위
			if (!checkRightUp(row, col)) {
				continue;
			}

			board[row][col] = 1;
			dfs(row + 1);
			board[row][col] = 0;
		}
	}

	public int solution(int n) {
		N = n;
		board = new int[N][N];
		dfs(0);

		return answer;
	}
}