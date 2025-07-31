package streak.d20250731.p81302_거리두기_확인하기;

import java.util.*;

class Solution {

	static boolean check(String[] place) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, - 1, 1};

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				List<int[]> pointList = new ArrayList<>();

				if (place[i].charAt(j) == 'P') {
					// 거리가 1인 지점 기준 탐색
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];

						if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5 || place[ny].charAt(nx) == 'X') {
							continue;
						}

						if (place[ny].charAt(nx) == 'P') {
							return false;
						} else if (place[ny].charAt(nx) == 'O') {
							pointList.add(new int[]{ny, nx});
						}
					}

					// 거리가 2인 지점 기준 탐색
					for (int[] point : pointList) {
						for (int k = 0; k < 4; k++) {
							int ny = point[0] + dy[k];
							int nx = point[1] + dx[k];

							if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5 || place[ny].charAt(nx) == 'X' || (ny == i && nx == j)) {
								continue;
							}

							if (place[ny].charAt(nx) == 'P') {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	public int[] solution(String[][] places) {
		int[] answer = new int[5];

		for (int i = 0; i < 5; i++) {
			if (check(places[i])) {
				answer[i] = 1;
			}
		}

		return answer;
	}
}