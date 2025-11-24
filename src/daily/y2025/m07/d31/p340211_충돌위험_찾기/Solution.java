package daily.y2025.m07.d31.p340211_충돌위험_찾기;

// 시간초과는 아니지만 비효율적임, 불필요하게 메모리 사용
class Solution {

	static int[][][] board = new int[20000][101][101];

	static int move(int time, int sy, int sx, int ty, int tx, boolean isLast) {
		int y = sy;
		int x = sx;

		// y 이동
		if (ty > sy) {
			for (int i = 0; i < (ty - sy); i++) {
				board[time][y][x]++;
				y++;
				time++;
			}
		} else if (ty < sy) {
			for (int i = 0; i < (sy - ty); i++) {
				board[time][y][x]++;
				y--;
				time++;
			}
		}

		// x 이동
		if (tx > sx) {
			for (int i = 0; i < (tx - sx); i++) {
				board[time][y][x]++;
				x++;
				time++;
			}
		} else if (tx < sx) {
			for (int i = 0; i < (sx - tx); i++) {
				board[time][y][x]++;
				x--;
				time++;
			}
		}

		if (isLast) {
			board[time][y][x]++;
		}

		return Math.abs(ty - sy) + Math.abs(tx - sx);
	}

	public int solution(int[][] points, int[][] routes) {
		int answer = 0;

		for (int i = 0; i < routes.length; i++) {
			int[] moves = routes[i]; // i 번째 로봇의 이동 경로
			int time = 0;

			for (int j = 0; j < moves.length - 1; j++) {
				int startNum = moves[j];
				int targetNum = moves[j + 1];

				int sy = points[startNum - 1][0] - 1;
				int sx = points[startNum - 1][1] - 1;

				int ty = points[targetNum - 1][0] - 1;
				int tx = points[targetNum - 1][1] - 1;

				if (j == (moves.length - 2)) {
					time += move(time, sy, sx, ty, tx, true);
				} else {
					time += move(time, sy , sx, ty, tx, false);
				}
			}
		}


		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				for (int t = 0; t < 20000; t++) {
					if (board[t][i][j] >= 2) {
						answer++;
					}
				}
			}
		}

		return answer;
	}
}