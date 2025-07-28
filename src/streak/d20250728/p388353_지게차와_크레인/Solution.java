package streak.d20250728.p388353_지게차와_크레인;

import java.util.*;

class Solution {

	static char[][] board;
	static int n, m;

	static int removeAll(char ch) {
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (board[i][j] == ch) {
					board[i][j] = ' ';
					cnt++;
				}
			}
		}

		return cnt;
	}

	static int removeBorder(char ch) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visit = new boolean[n + 2][m + 2];

		queue.offer(new int[]{0, 0});
		visit[0][0] = true;
		int cnt = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];

				if (ny < 0 || nx < 0 || ny >= (n + 2) || nx >= (m + 2) || visit[ny][nx]) {
					continue;
				}

				if (board[ny][nx] == ' ') {
					queue.offer(new int[]{ny, nx});
					visit[ny][nx] = true;
				} else if (board[ny][nx] == ch) {
					board[ny][nx] = ' ';
					visit[ny][nx] = true;
					cnt++;
				}
			}
		}

		return cnt;
	}

	public int solution(String[] storage, String[] requests) {
		n = storage.length;
		m = storage[0].length();
		int answer = n * m;

		board = new char[n + 2][m + 2];

		for (int i = 0; i < n + 2; i++) {
			Arrays.fill(board[i], ' ');
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i + 1][j + 1] = storage[i].charAt(j);
			}
		}

		for (String request : requests) {
			char ch = request.charAt(0);
			int len = request.length();

			if (len == 1) {
				answer -= removeBorder(ch);
			} else if (len == 2) {
				answer -= removeAll(ch);
			}
		}

		return answer;
	}
}