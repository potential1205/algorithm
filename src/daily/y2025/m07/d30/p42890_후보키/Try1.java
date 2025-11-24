package daily.y2025.m07.d30.p42890_후보키;

import java.util.*;

class Try1 {
	static int answer;
	static boolean[] isActive;
	static List<boolean[]> isActiveList = new ArrayList<>();
	static boolean[] visit;

	static void dfs(int n, int m, int depth, String[][] relation) {
		if (depth == m) {

			Set<String> set = new HashSet<>();

			for (int i = 0; i < n; i++) {

				String value = "";
				for (int j = 0; j < m; j++) {
					if (isActive[j]) {
						value += (relation[i][j] + "|");
					}
				}

				if (!set.contains(value)) {
					set.add(value);
				}
			}

			if (set.size() == n) {
				isActiveList.add(isActive.clone());
			}
			return;
		}

		isActive[depth] = false;
		dfs(n, m, depth + 1, relation);

		isActive[depth] = true;
		dfs(n, m, depth + 1, relation);
	}

	public int solution(String[][] relation) {
		int n = relation.length;
		int m = relation[0].length;

		isActive = new boolean[m];

		dfs(n, m, 0, relation);

		visit = new boolean[m];

		// // 후보 거르기
		for (boolean[] active : isActiveList) {

			int cnt = 0;

			for (int i = 0; i < m; i++) {
				if (!active[i]) {
					cnt++;
				} else if (active[i] && visit[i]) {
					break;
				} else if (active[i] && !visit[i]) {
					cnt++;
				}
			}

			if (cnt == m) {
				answer++;

				for (int i = 0; i < m; i++) {
					if (active[i]) {
						visit[i] = true;
					}
					System.out.print(active[i] + " ");
				}
				System.out.println();
			}
		}

		return answer;
	}
}