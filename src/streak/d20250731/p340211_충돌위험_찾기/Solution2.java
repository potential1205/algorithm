package streak.d20250731.p340211_충돌위험_찾기;

import java.util.*;

class Solution2 {
	static Map<String, Integer> map = new HashMap<>();

	static int move(int time, int sy, int sx, int ty, int tx, boolean isLast) {
		int y = sy;
		int x = sx;

		// y 이동
		if (ty > sy) {
			for (int i = 0; i < (ty - sy); i++) {
				String key = y + ":" + x + ":" + time;
				map.put(key, map.getOrDefault(key, 0) + 1);
				y++;
				time++;
			}
		} else if (ty < sy) {
			for (int i = 0; i < (sy - ty); i++) {
				String key = y + ":" + x + ":" + time;
				map.put(key, map.getOrDefault(key, 0) + 1);
				y--;
				time++;
			}
		}

		// x 이동
		if (tx > sx) {
			for (int i = 0; i < (tx - sx); i++) {
				String key = y + ":" + x + ":" + time;
				map.put(key, map.getOrDefault(key, 0) + 1);
				x++;
				time++;
			}
		} else if (tx < sx) {
			for (int i = 0; i < (sx - tx); i++) {
				String key = y + ":" + x + ":" + time;
				map.put(key, map.getOrDefault(key, 0) + 1);
				x--;
				time++;
			}
		}

		if (isLast) {
			String key = y + ":" + x + ":" + time;
			map.put(key, map.getOrDefault(key, 0) + 1);
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

		for (String key : map.keySet()) {
			if (map.get(key) >= 2) {
				answer++;
			}
		}

		return answer;
	}
}