package daily.y2025.m07.d30.p42890_후보키;

import java.util.*;

class Solution {
	static boolean[] isActive;
	static List<boolean[]> uniqueCaseList = new ArrayList<>();
	static List<boolean[]> minimalList = new ArrayList<>();

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
				uniqueCaseList.add(isActive.clone());
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

		// 유일성을 가지는 집합 구하기
		dfs(n, m, 0, relation);

		// 최소성을 가지는 집합만 걸러내기
		for (boolean[] candidate : uniqueCaseList) {
			boolean minimal = true;

			for (boolean[] minimalCase : minimalList) {
				boolean isSubset = true;

				for (int i = 0; i < m; i++) {
					if (minimalCase[i] && !candidate[i]) { // minimalCase[i] 가 true일 때 단 한번이라도 candidate[i]와 다르면 subset아님
						isSubset = false;
						break;
					}
				}

				if (isSubset) { // subset인 경우 최소성을 보장할 수 없음
					minimal = false;
					break;
				}
			}

			if (minimal) {
				minimalList.add(candidate.clone());
			}
		}

		return minimalList.size();
	}
}