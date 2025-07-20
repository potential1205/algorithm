package streak.d20250720.p42747_H_Index;

import java.util.*;

class Solution1 {
	public int solution(int[] citations) {
		int answer = 0;
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < citations.length; i++) {
			list.add(citations[i]);
		}

		Collections.sort(list, Comparator.reverseOrder());

		for (int i = list.size(); i >= 0; i--) {
			int cnt = 0;

			for (int j = 0; j < list.size(); j++) {
				if (i <= list.get(j)) {
					cnt++;
				} else {
					break;
				}
			}

			if (i <= cnt) {
				return i;
			}
		}

		return 0;
	}
}
