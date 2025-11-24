package daily.y2025.m07.d25.p154540_무인도_여행;

import java.util.*;

class Solution {

	static boolean[][] visit;

	static int bfs(String[] maps, int n, int m, int sy, int sx) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offerFirst(new int[]{sy, sx});
		visit[sy][sx] = true;

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		int size = 0;

		while(!queue.isEmpty()) {
			int[] cur = queue.pollLast();
			char ch = maps[cur[0]].charAt(cur[1]);
			size += Integer.parseInt(ch + "");

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m || visit[ny][nx] || maps[ny].charAt(nx) == 'X') continue;

				queue.offer(new int[]{ny, nx});
				visit[ny][nx] = true;
			}
		}

		return size;
	}

	public int[] solution(String[] maps) {
		int n = maps.length;
		int m = maps[0].length();
		visit = new boolean[n][m];

		List<Integer> answer = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visit[i][j] && maps[i].charAt(j) != 'X') {
					int size = bfs(maps, n, m, i, j);
					answer.add(size);
				}
			}
		}

		Collections.sort(answer);
		if (answer.isEmpty()) {
			answer.add(-1);
		}

		int[] answerArr = new int[answer.size()];

		for (int i = 0; i < answerArr.length; i++) {
			answerArr[i] = answer.get(i);
		}

		return answerArr;
	}
}