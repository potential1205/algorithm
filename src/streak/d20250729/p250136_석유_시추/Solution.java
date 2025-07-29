package streak.d20250729.p250136_석유_시추;

import java.util.*;

class Solution {
	static int n, m;
	static boolean[][] visit;
	static List<Oil> oilList = new ArrayList<>();

	static class Oil {
		int size;
		Set<Integer> colmns;

		Oil(int size, Set<Integer> colmns) {
			this.size = size;
			this.colmns = colmns;
		}
	}

	static void bfs(int sy, int sx, int[][] land) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{sy, sx});
		visit[sy][sx] = true;
		int size = 1;

		Set<Integer> colmns = new HashSet<>();
		colmns.add(sx);

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m || visit[ny][nx] || land[ny][nx] == 0) {
					continue;
				}

				queue.offer(new int[]{ny, nx});
				visit[ny][nx] = true;
				colmns.add(nx);
				size++;
			}
		}

		oilList.add(new Oil(size, colmns));
	}

	public int solution(int[][] land) {
		int answer = 0;
		n = land.length;
		m = land[0].length;
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && land[i][j] == 1) {
					bfs(i, j, land);
				}
			}
		}

		int[] oilSize = new int[m];

		for (Oil oil : oilList) {
			for (int col : oil.colmns) {
				oilSize[col] += oil.size;
			}
		}

		for (int i = 0; i < m; i++) {
			answer = Math.max(answer, oilSize[i]);
		}

		return answer;
	}
}