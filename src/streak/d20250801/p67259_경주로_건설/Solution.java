package streak.d20250801.p67259_경주로_건설;

import java.util.*;

class Solution {

	static class Node {
		int y;
		int x;
		int d;
		int cost;
		int cnt1;
		int cnt2;

		Node(int y, int x, int d, int cost, int cnt1, int cnt2) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.cost = cost;
			this.cnt1 = cnt1;
			this.cnt2 = cnt2;
		}
	}

	public int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 1, 0, 0, 0));
		queue.offer(new Node(0, 0, 3, 0, 0, 0));

		int n = board.length;
		int m = board[0].length;

		int[][][] visit = new int[n][m][4];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < 4; k++) {
					visit[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.y == (n - 1) && cur.x == (m - 1)) {
				answer = Math.min(answer, cur.cnt1 * 100 + cur.cnt2 * 500);
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m || board[ny][nx] == 1) continue;

				if (i == cur.d) {
					if (visit[ny][nx][i] > (cur.cost + 100)) {
						queue.offer(new Node(ny, nx, i, cur.cost + 100, cur.cnt1 + 1, cur.cnt2));
						visit[ny][nx][i] = (cur.cost + 100);
					}
				} else {
					if (visit[ny][nx][i] > (cur.cost + 600)) {
						queue.offer(new Node(ny, nx, i, cur.cost + 600, cur.cnt1 + 1, cur.cnt2 + 1));
						visit[ny][nx][i] = (cur.cost + 600);
					}
				}
			}
		}

		return answer;
	}
}