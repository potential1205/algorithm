package daily.y2025.m07.d27.p42860_조이스틱;

import java.util.*;

class Try1 {

	static int answer;

	public int solution(String name) {
		int answer = 0;
		int n = name.length();
		String str = "";

		for (int i = 0; i < n; i++) {
			str += "A";
		}

		int x = 0;

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (name.charAt(i) == str.charAt(i)) {
				continue;
			}

			// 문자 차이만큼 이동
			int gap = (int) name.charAt(i) - (int) str.charAt(i);
			gap = Math.min(gap, 26 - gap);
			answer += gap;
			list.add(i);
		}

		int move = n - 1;

		for (int i = 0; i < n; i++) {
			int next = i + 1;

			while (next < n && name.charAt(next) == 'A') {
				next++;
			}

			move = Math.min(move, i + i + (n - next));
			move = Math.min(move, (n - next) + 2 * i);
		}

		return answer + move;
	}
}